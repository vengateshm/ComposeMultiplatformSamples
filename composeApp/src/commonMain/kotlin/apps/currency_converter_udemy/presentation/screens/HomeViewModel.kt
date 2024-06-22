package apps.currency_converter_udemy.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.RateStatus
import apps.currency_converter_udemy.domain.model.RequestState
import apps.currency_converter_udemy.domain.repository.CurrencyApiService
import apps.currency_converter_udemy.domain.repository.LocalDbRepository
import apps.currency_converter_udemy.domain.repository.PreferencesRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

sealed class HomeUiEvent {
    data object RefreshRates : HomeUiEvent()
    data object SwitchCurrencies : HomeUiEvent()
    data class SaveSourceCurrencyCode(val code: String) : HomeUiEvent()
    data class SaveTargetCurrencyCode(val code: String) : HomeUiEvent()
}

class HomeViewModel(
    private val preferencesRepository: PreferencesRepository,
    private val localDbRepository: LocalDbRepository,
    private val currencyApiService: CurrencyApiService
) : ScreenModel {

    private var _rateStatus: MutableState<RateStatus> = mutableStateOf(RateStatus.Idle)
    val rateStatus: State<RateStatus> = _rateStatus

    private var _sourceCurrency: MutableState<RequestState<Currency>> =
        mutableStateOf(RequestState.Idle)
    val sourceCurrency: State<RequestState<Currency>> = _sourceCurrency

    private var _targetCurrency: MutableState<RequestState<Currency>> =
        mutableStateOf(RequestState.Idle)
    val targetCurrency: State<RequestState<Currency>> = _targetCurrency

    private val _allCurrencies = mutableListOf<Currency>()
    val allCurrencies: List<Currency> = _allCurrencies

    init {
        screenModelScope.launch {
            fetchNewRates()
            readSourceCurrency()
            readTargetCurrency()
        }
    }

    private fun readSourceCurrency() {
        screenModelScope.launch(Dispatchers.Main) {
            preferencesRepository.readSourceCurrencyCode()
                .collectLatest { currencyCode ->
                    val selectedCurrency = _allCurrencies.find { it.code == currencyCode.name }
                    if (selectedCurrency != null) {
                        _sourceCurrency.value = RequestState.Success(data = selectedCurrency)
                    } else {
                        _sourceCurrency.value =
                            RequestState.Error(message = "Couldn't find the selected currency.")
                    }
                }
        }
    }

    private fun readTargetCurrency() {
        screenModelScope.launch(Dispatchers.Main) {
            preferencesRepository.readTargetCurrencyCode()
                .collectLatest { currencyCode ->
                    val selectedCurrency = _allCurrencies.find { it.code == currencyCode.name }
                    if (selectedCurrency != null) {
                        _targetCurrency.value = RequestState.Success(data = selectedCurrency)
                    } else {
                        _targetCurrency.value =
                            RequestState.Error(message = "Couldn't find the selected currency.")
                    }
                }
        }
    }

    private suspend fun fetchNewRates() {
        try {
            val localDbData = localDbRepository.readCurrencyData().first()
            if (localDbData.isSuccess()) {
                if (localDbData.getSuccessData().isNotEmpty()) {
                    println("HomeViewModel : Database is full")
                    _allCurrencies.clear()
                    _allCurrencies.addAll(localDbData.getSuccessData())
                    if (!preferencesRepository.isDataFresh(
                            Clock.System.now().toEpochMilliseconds()
                        )
                    ) {
                        println("HomeViewModel : Data is not fresh")
                        cacheData(localDbData)
                    } else {
                        println("HomeViewModel : Data is fresh")
                    }
                } else {
                    println("HomeViewModel : Database needs data")
                    cacheData(localDbData)
                }
            } else if (localDbData.isError()) {
                println("HomeViewModel : Error reading local database ${localDbData.getErrorMessage()}")
            }
            getRateStatus()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private suspend fun cacheData(localDbData: RequestState<List<Currency>>) {
        val fetchedData = currencyApiService.getLatestExchangeRates()
        if (fetchedData.isSuccess()) {
            localDbRepository.cleanUp()
            fetchedData.getSuccessData().forEach {
                println("HomeViewModel : Adding currency $it")
                localDbRepository.insertCurrencyData(it)
            }
            println("HomeViewModel : Updating _allCurrencies")
            _allCurrencies.clear()
            _allCurrencies.addAll(localDbData.getSuccessData())
        } else if (fetchedData.isError()) {
            println("HomeViewModel : Fetching failed ${fetchedData.getErrorMessage()}")
        }
    }

    private suspend fun getRateStatus() {
        _rateStatus.value = if (preferencesRepository.isDataFresh(
                currentTimestamp = Clock.System.now().toEpochMilliseconds()
            )
        ) RateStatus.Fresh else RateStatus.Stale
    }

    fun sendEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.RefreshRates -> {
                screenModelScope.launch {
                    fetchNewRates()
                }
            }

            is HomeUiEvent.SwitchCurrencies -> {
                switchCurrencies()
            }

            is HomeUiEvent.SaveSourceCurrencyCode -> {
                saveSourceCurrencyCode(uiEvent.code)
            }

            is HomeUiEvent.SaveTargetCurrencyCode -> {
                saveTargetCurrencyCode(uiEvent.code)
            }
        }
    }

    private fun switchCurrencies() {
        val source = _sourceCurrency.value
        val target = _targetCurrency.value
        _sourceCurrency.value = target
        _targetCurrency.value = source
    }

    private fun saveSourceCurrencyCode(code: String) {
        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.saveSourceCurrencyCode(code)
        }
    }

    private fun saveTargetCurrencyCode(code: String) {
        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.saveTargetCurrencyCode(code)
        }
    }
}
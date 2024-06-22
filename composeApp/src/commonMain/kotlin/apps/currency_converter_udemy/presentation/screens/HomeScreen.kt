package apps.currency_converter_udemy.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import apps.currency_converter_udemy.domain.model.CurrencyType
import apps.currency_converter_udemy.presentation.components.CurrencyPickerDialog
import apps.currency_converter_udemy.presentation.components.HomeBody
import apps.currency_converter_udemy.presentation.components.HomeHeader
import apps.currency_converter_udemy.ui.theme.surfaceColor
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<HomeViewModel>()
        val rateStatus by viewModel.rateStatus
        val sourceCurrency by viewModel.sourceCurrency
        val targetCurrency by viewModel.targetCurrency
        val allCurrencies = viewModel.allCurrencies

        var amount by rememberSaveable { mutableStateOf(0.0) }

        var selectedCurrencyType: CurrencyType by remember { mutableStateOf(CurrencyType.None) }
        var dialogOpened by remember { mutableStateOf(false) }

        if (dialogOpened && selectedCurrencyType != CurrencyType.None) {
            CurrencyPickerDialog(
                currencies = allCurrencies,
                currencyType = selectedCurrencyType,
                onPositiveClick = { currencyCode ->
                    if (selectedCurrencyType is CurrencyType.Source) {
                        viewModel.sendEvent(HomeUiEvent.SaveSourceCurrencyCode(code = currencyCode.name))
                    } else if (selectedCurrencyType is CurrencyType.Target) {
                        viewModel.sendEvent(HomeUiEvent.SaveTargetCurrencyCode(code = currencyCode.name))
                    }
                    selectedCurrencyType = CurrencyType.None
                    dialogOpened = false
                },
                onDismiss = {
                    selectedCurrencyType = CurrencyType.None
                    dialogOpened = false
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = surfaceColor)
        ) {
            HomeHeader(
                rateStatus = rateStatus,
                source = sourceCurrency,
                target = targetCurrency,
                amount = amount,
                onRatesRefresh = {
                    viewModel.sendEvent(HomeUiEvent.RefreshRates)
                },
                onSwitchClick = {
                    viewModel.sendEvent(HomeUiEvent.SwitchCurrencies)
                },
                onAmountChange = {
                    amount = it
                },
                onCurrencyTypeSelect = { currencyType ->
                    selectedCurrencyType = currencyType
                    dialogOpened = true
                }
            )
            HomeBody(
                source = targetCurrency,
                target = sourceCurrency,
                amount = amount
            )
        }
    }
}
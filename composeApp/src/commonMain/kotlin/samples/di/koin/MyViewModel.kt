package samples.di.koin

import androidx.lifecycle.ViewModel

class MyViewModel(
    private val myRepository: MyRepository
) : ViewModel() {
    fun getMessage(): String {
        return myRepository.getMessage()
    }
}
package tech.dobrobot.apps.ui.main.translate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.dobrobot.apps.data.processing.TranslationPipeline
import tech.dobrobot.apps.utils.remote.RemoteResult
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(private val dataProcessingPipeline: TranslationPipeline): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastError = MutableLiveData<String>()
    val toastError: LiveData<String> = _toastError

    private val _translated = MutableLiveData<String>()
    val translated: LiveData<String> = _translated

    fun loadTranslation(originalText: String, to: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            when (val result = dataProcessingPipeline.loadTranslation(originalText, to)) {
                is RemoteResult.Success -> _translated.postValue(result.data.toString())
                is RemoteResult.Error -> _toastError.postValue(result.message)
            }
            _isLoading.postValue(false)
        }
    }
}
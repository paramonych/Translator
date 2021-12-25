package tech.dobrobot.apps.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.data.processing.HistoryPipeline
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val dataProcessingPipeline: HistoryPipeline): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastError = MutableLiveData<String>()
    val toastError: LiveData<String> = _toastError

    private val _historyEmpty = MutableLiveData<Boolean>()
    val historyEmpty: LiveData<Boolean> = _historyEmpty

    val historyRecordsList: LiveData<List<TranslationRecord>> = dataProcessingPipeline.historyRecords

    fun removeHistoryRecord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataProcessingPipeline.removeHistoryRecord(id)
        }
    }
}
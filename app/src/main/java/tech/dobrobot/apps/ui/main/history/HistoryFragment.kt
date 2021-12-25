package tech.dobrobot.apps.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_history.*
import tech.dobrobot.apps.adapters.OnRemoveClickCallback
import tech.dobrobot.apps.adapters.RecordsListAdapter
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.databinding.FragmentHistoryBinding
import tech.dobrobot.apps.ui.MainNavigationFragment
import tech.dobrobot.apps.utils.extensions.doOnChange

@AndroidEntryPoint
class HistoryFragment: MainNavigationFragment(), OnRemoveClickCallback {
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var binding: FragmentHistoryBinding
    private var recordsListAdapter = RecordsListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@HistoryFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    override fun initializeViews() {
        historyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recordsListAdapter
        }
    }

    override fun observeViewModel() {

        viewModel.historyRecordsList.doOnChange(this) {
            recordsListAdapter.updateList(it)

            showToast("Record removed")
        }

        viewModel.toastError.doOnChange(this) {
            showToast(it)
        }

    }

    override fun onRemoveClick(record: TranslationRecord) {
        viewModel.removeHistoryRecord(record)
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}
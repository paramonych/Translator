package tech.dobrobot.apps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_records_list.view.*
import tech.dobrobot.apps.R
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.utils.extensions.emptyIfNull

interface OnRemoveClickCallback {
    fun onRemoveClick(record: TranslationRecord)
}

class RecordsListAdapter (private val onRemoveClickCallback: OnRemoveClickCallback) : RecyclerView.Adapter<RecordsListAdapter.HistoryListViewHolder>() {
    private val recordsList: MutableList<TranslationRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        return HistoryListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_records_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.bind(recordsList[position], onRemoveClickCallback)
    }

    override fun getItemCount(): Int = recordsList.size

    fun updateList(list: List<TranslationRecord>) {
        this.recordsList.clear()
        this.recordsList.addAll(list)
        notifyDataSetChanged()
    }

    class HistoryListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: TranslationRecord, onRemoveClickCallback: OnRemoveClickCallback) {
            itemView.originalTextView.text = model.original.emptyIfNull()
            itemView.translatedTextView.text = model.translated.emptyIfNull()
            itemView.recordDateTextView.text = model.date.emptyIfNull()

            itemView.removeRecordImageView.setOnClickListener {
                onRemoveClickCallback.onRemoveClick(model)
            }
        }
    }
}
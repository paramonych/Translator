package tech.dobrobot.apps.ui.main.translate

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_translate.*
import tech.dobrobot.apps.R
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.ui.MainNavigationFragment
import tech.dobrobot.apps.utils.extensions.doOnChange
import tech.dobrobot.apps.utils.remote.RemoteResult

@AndroidEntryPoint
class TranslateFragment: MainNavigationFragment() {
    private val viewModel: TranslateViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        observeViewModel()
        val view: View = inflater.inflate(R.layout.fragment_translate, container, false)
        view?.let {
            val input: EditText = it.findViewById(R.id.originalText)

            it.findViewById<Button>(R.id.submitTranslation).setOnClickListener {
                hideKeyboard(input)

                val textToTranslate = input.text.toString()

                if(textToTranslate.isNotEmpty()) {
                    viewModel.loadTranslation(textToTranslate)
                } else {
                    showToast(getString(R.string.empty_text_submitted))
                }
            }

            it.findViewById<Button>(R.id.clearText).setOnClickListener {
                hideKeyboard(input)

                if(input.text.toString().isNotEmpty()) {
                    input.text.clear()
                    showToast(getString(R.string.Ok))
                }
            }
        }

        return view
    }

    override fun initializeViews() {
    }

    fun hideKeyboard(input: EditText) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }

    override fun observeViewModel() {
        viewModel.isLoading.doOnChange(this) { loading ->
            translationLoading.visibility = if (loading) View.VISIBLE else View.GONE
            translationTextView.visibility = if (loading) View.GONE else View.VISIBLE
        }

        viewModel.translated.doOnChange(this) { translated ->
            translationTextView.text = translated
        }

        viewModel.toastError.doOnChange(this) {
            showToast(it)
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}
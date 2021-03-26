package ru.mancomapp.presentation.global.selectitem

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_select_item.view.*
import ru.mancomapp.R

class SelectItemDialogFragment(
    private val callback: Callback,
    private val title: String,
    private val items: List<SelectItem>
) : DialogFragment() {

    companion object {
        private const val TAG = "SelectItemDialogFragment"

        fun show(
            fragmentManager: FragmentManager,
            callback: Callback,
            title: String,
            items: List<SelectItem>
        ) {
            SelectItemDialogFragment(
                callback,
                title,
                items
            ).show(fragmentManager, TAG)
        }
    }

    interface Callback {
        fun onSelectItem(item: SelectItem)
    }

    private lateinit var selectItemAdapter: SelectItemAdapter

    private val selectItemCallback = object : SelectItemAdapter.Callback {
        override fun onItemClick(item: SelectItem) {
            dialog?.dismiss()
            callback.onSelectItem(item)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout: View = LayoutInflater.from(it).inflate(R.layout.dialog_select_item, null, false)
            initLayout(layout)
            builder
                .setView(layout)
                .setNegativeButton(R.string.close) { dialog, id -> /* Do nothing */ }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun initLayout(layout: View) {
        with(layout) {
            select_item_dialog_title.text = title

            select_item_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            selectItemAdapter = SelectItemAdapter(selectItemCallback)
            select_item_list.adapter = selectItemAdapter
            selectItemAdapter.submitList(items)
        }
    }
}

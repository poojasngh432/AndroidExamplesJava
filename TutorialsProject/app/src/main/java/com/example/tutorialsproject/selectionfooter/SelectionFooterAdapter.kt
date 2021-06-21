package com.onemg.uilib.widgets.bottomsheets.footer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.onemg.uilib.databinding.ItemAddedTestsBinding
import com.onemg.uilib.models.AddedTest
import com.onemg.uilib.utility.setTextInTextViewWithVisibility

class SelectionFooterAdapter (
    private val addedtests: List<AddedTest>,
    private val callback: SelectionFooterAddedTestsCallback
) : RecyclerView.Adapter<SelectionFooterAdapter.SelectionFooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionFooterViewHolder {
        val binding =
            ItemAddedTestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val testViewHolder = SelectionFooterViewHolder(binding)
        binding.root.setOnClickListener { onAddedTestClicked(testViewHolder.adapterPosition, parent.context) }
        binding.deleteIcon.setOnClickListener { onDeleteTestClicked(testViewHolder.adapterPosition) }
        binding.knowMoreText.setOnClickListener { onKnowMoreClicked(testViewHolder.adapterPosition) }
        return testViewHolder
    }

    private fun onKnowMoreClicked(adapterPosition: Int) {
        if (RecyclerView.NO_POSITION != adapterPosition) {
            val test = addedtests[adapterPosition]
            callback.onKnowMoreClick(test)
        }
    }

    private fun onDeleteTestClicked(adapterPosition: Int) {
        if (RecyclerView.NO_POSITION != adapterPosition) {
            val test = addedtests[adapterPosition]
            callback.onTestDelete(test)
        }
    }

    override fun onBindViewHolder(holder: SelectionFooterViewHolder, position: Int) {
        if (RecyclerView.NO_POSITION != position) {
            holder.bind(addedtests[position])
        }
    }

    override fun getItemCount(): Int = addedtests.size

    private fun onAddedTestClicked(adapterPosition: Int, context: Context) {
        if (RecyclerView.NO_POSITION != adapterPosition) {
            val test = addedtests[adapterPosition]
            if (test.selected.not()) {

            }
            Toast.makeText(context,"add test clicked", Toast.LENGTH_LONG).show()
        }
    }

    inner class SelectionFooterViewHolder(val binding: ItemAddedTestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(test: AddedTest) {
            binding.name.setTextInTextViewWithVisibility(test.name)
            binding.label.setTextInTextViewWithVisibility(test.label)
            binding.knowMoreText.setTextInTextViewWithVisibility("know more")
        }
    }

    interface SelectionFooterAddedTestsCallback {
        fun onTestDelete(test: AddedTest)
        fun onKnowMoreClick(test: AddedTest)
    }
}
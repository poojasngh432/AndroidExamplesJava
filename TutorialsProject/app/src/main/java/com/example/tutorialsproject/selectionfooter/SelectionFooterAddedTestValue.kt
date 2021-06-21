package com.onemg.uilib.widgets.bottomsheets.footer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.onemg.uilib.components.button.CtaActionCallback
import com.onemg.uilib.databinding.LayoutSelectionFooterAddedTestBinding
import com.onemg.uilib.models.Cta
import com.onemg.uilib.models.SelectionFooterValue

class SelectionFooterAddedTestValue @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
 ConstraintLayout(context, attrs, defStyleAttr){
  
  private val binding = LayoutSelectionFooterAddedTestBinding.inflate(LayoutInflater.from(context), this, true)

 fun setData(value: SelectionFooterValue, callback: CtaActionCallback) {
  configureHeader(value.header)
  binding.itemsCount.text = "1"
  configureCta(value.cta, callback)
 }

 private fun configureHeader(header: String?) {
  if (!header.isNullOrEmpty()) {
   binding.header.text = "Items selected"
   binding.header.visibility = View.VISIBLE
  } else {
   binding.header.visibility = View.GONE
  }
 }

 private fun configureCta(cta: Cta?, callback: CtaActionCallback) {
  if (cta == null) {
   binding.chooseLab.visibility = View.GONE
  } else {
   binding.chooseLab.text = "Choose lab"
   binding.chooseLab.visibility = View.VISIBLE
   binding.chooseLab.setOnClickListener {
    binding.chooseLab.onClick(callback, cta.action, cta.details)
   }
  }
 }
}
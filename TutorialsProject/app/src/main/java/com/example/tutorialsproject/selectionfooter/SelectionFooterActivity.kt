package com.example.tutorialsproject.selectionfooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.onemg.uilib.components.button.CtaActionCallback
import com.onemg.uilib.databinding.ActivitySelectionFooterBinding
import com.onemg.uilib.models.*
import com.onemg.uilib.widgets.bottomsheets.BottomsheetHeaderCallback
import com.onemg.uilib.widgets.bottomsheets.footer.SelectionFooterAdapter

class SelectionFooterActivity : AppCompatActivity(), CtaActionCallback, BottomsheetHeaderCallback,
    SelectionFooterAdapter.SelectionFooterAddedTestsCallback {

    private lateinit var binding : ActivitySelectionFooterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectionFooterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureAddedTestsData()
        configureSelectionFooterValues()
    }

    private fun configureSelectionFooterValues() {
        val value = SelectionFooterValue("Combo Price", 0,
            Cta("Add to Cart", CtaActionType.ADD_SKU)
        )
        binding.header.setData("Added Tests", false, this)
        binding.selectionFooter.setData(value, this)
    }

    private fun configureAddedTestsData() {
        var data = mutableListOf<AddedTest>()
        data.add(AddedTest("0",false,"Test 1", "5 tests"))
        data.add(AddedTest("1",false,"Test 2", "4 tests"))

        binding.testsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.testsList.adapter = SelectionFooterAdapter(data, this)
    }

    override fun onCtaClicked(ctaActionType: String?, ctaDetails: CtaDetails?) {
        Toast.makeText(this,"cta clicked",Toast.LENGTH_LONG).show()
    }

    override fun onBackClicked() {
        Toast.makeText(this,"back clicked",Toast.LENGTH_LONG).show()
    }

    override fun onCrossClicked() {
        Toast.makeText(this,"cross clicked",Toast.LENGTH_LONG).show()
    }

    override fun onTestDelete(test: AddedTest) {
        Toast.makeText(this,"delete clicked",Toast.LENGTH_LONG).show()
    }

    override fun onKnowMoreClick(test: AddedTest) {
        Toast.makeText(this,"know more",Toast.LENGTH_LONG).show()
    }
}
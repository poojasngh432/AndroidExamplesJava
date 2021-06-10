package com.example.tutorialsproject.mvp1mg

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorialsproject.R

class DiagnosticsHomeActivity : AppCompatActivity(), Callback, DiagnosticsHomeView {
    lateinit var button : Button
    lateinit var diagnosticsHomePresenter: DiagnosticsHomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostics_home)

        button = findViewById(R.id.button)
        diagnosticsHomePresenter = DiagnosticsHomePresenterImpl(this)

        button.setOnClickListener {
            onAddToCartClick("Hi")
        }
    }

    override fun onAddToCartClick(
        heading: String?
    ) {
       Toast.makeText(this,heading,Toast.LENGTH_LONG).show()
        diagnosticsHomePresenter.onTestAddClick("Full Body test")
    }

    override fun onPopularTestItemClick(
        adapterPosition: Int,
        heading: String?
    ) {
        Toast.makeText(this,heading,Toast.LENGTH_LONG).show()
    }

    override fun addTestToCart(labId: Int) {
        Toast.makeText(this,"addTestToCart",Toast.LENGTH_LONG).show()
    }
}

interface Callback {
    fun onAddToCartClick(eading: String?)
    fun onPopularTestItemClick(
        adapterPosition: Int,
        heading: String?
    )
}
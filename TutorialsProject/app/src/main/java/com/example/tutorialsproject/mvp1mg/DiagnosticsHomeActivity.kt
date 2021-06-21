package com.example.tutorialsproject.mvp1mg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorialsproject.R

class DiagnosticsHomeActivity : AppCompatActivity(), Callback, DiagnosticsHomeView {
    lateinit var button : Button
    lateinit var progress: ProgressBar
    lateinit var diagnosticsHomePresenter: DiagnosticsHomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostics_home)

        button = findViewById(R.id.button)
        progress = findViewById(R.id.progress)
        diagnosticsHomePresenter = DiagnosticsHomePresenterImpl(this)
        diagnosticsHomePresenter.onScreenCreated()
        button.setOnClickListener {
            onAddToCartClick("button clicked")
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

    override fun showProgress() {
        if (!progress.isShown()) {
            progress.setVisibility(View.VISIBLE)
        }
    }

    override fun hideProgress() {
        if (progress.isShown()) {
            progress.setVisibility(View.GONE)
        }
    }
}

interface Callback {
    fun onAddToCartClick(eading: String?)
    fun onPopularTestItemClick(
        adapterPosition: Int,
        heading: String?
    )
}
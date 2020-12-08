package com.assignment.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class MvpActivity<V : MvpView, out P : Presenter<V>> : AppCompatActivity(), MvpView {

    protected abstract val presenter: P

    override fun onResume() {
        super.onResume()
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }
}
package com.assignment.recipeapp

interface MainView : MvpView {

    fun showErrorMessage(error: String)

    fun show(list: List<Recipe>)

    fun emptyItems()

    fun showProgress()

    fun showDetail(model: RecipeModel)
}
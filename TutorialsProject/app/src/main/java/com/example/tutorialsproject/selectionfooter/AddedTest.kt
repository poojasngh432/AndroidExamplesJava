package com.example.tutorialsproject.selectionfooter

import androidx.annotation.Keep

@Keep
data class AddedTest(
    val id: String? = null,
    val selected: Boolean = false,
    val name: String? = null,
    val label: String? = null,
)
package com.rezapour.dogsbreeds.util


import java.util.Locale

fun String.capitalize() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }
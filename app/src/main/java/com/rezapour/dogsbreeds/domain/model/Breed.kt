package com.rezapour.dogsbreeds.domain.model

import android.icu.text.CaseMap.Title

data class Breed(
    val name: String,
    val type: String? = null
) {
    val title: String
        get() = if (type != null) "$type  $name" else name
}

data class BreedDomain(
    val title: String,
    val name: String,
    val type: String? = null,
    val favorite: Boolean
)

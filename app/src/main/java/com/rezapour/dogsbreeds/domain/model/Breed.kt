package com.rezapour.dogsbreeds.domain.model

data class Breed(
    val name: String,
    val type: String? = null
) {
    val title: String
        get() = type + name
}

data class BreedDomain(
    val name: String,
    val type: String? = null,
    val favorite: Boolean
) {
    val title: String
        get() = if (type != null) "$type  $name" else name
}

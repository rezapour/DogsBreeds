package com.rezapour.dogsbreeds.util.sampels

import com.google.gson.JsonParser
import com.rezapour.dogsbreeds.data.network.models.BreedsImageNetworkEntity
import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity

object NetworkSampleFactory {

    fun getBreedNetworkEntity(): BreedsNetworkEntity {
        return BreedsNetworkEntity(
            message = JsonParser().parse(getMessageString()),
            status = "success"
        )
    }

    private fun getMessageString() = "{\n" +
            "    \"affenpinscher\": [],\n" +
            "    \"african\": [],\n" +
            "    \"bulldog\": [\n" +
            "      \"boston\",\n" +
            "      \"english\",\n" +
            "      \"french\"\n" +
            "    ]\n" +
            "  }"

    fun getBreedsImageNetworkEntity() =
        BreedsImageNetworkEntity(listOf("https://images.dog.ceo/breeds/affenpinscher/n02110627_10185.jpg"), "success")
}
package com.rezapour.dogsbreeds.data.network.mapper

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.rezapour.dogsbreeds.data.network.models.BreedsNetworkEntity
import com.rezapour.dogsbreeds.domain.model.Breed
import javax.inject.Inject

class NetworkMapper @Inject constructor() {
    fun breedNetworkEntityToBreed(breedsNetworkEntity: BreedsNetworkEntity): List<Breed> {
        val breedList: ArrayList<Breed> = ArrayList()
        val element: JsonElement = breedsNetworkEntity.message
        val obj = element.asJsonObject //since you know it's a JsonObject

        val entries = obj.entrySet() //will return members of your object
        for ((key, value) in entries) {
            if (value.asJsonArray.size() == 0) {
                val bread = Breed(name = key)
                breedList.add(bread)
            } else
                for (breed in value.asJsonArray) {
                    val bread = Breed(name = key, type = breed.asString)
                    breedList.add(bread)
                }

        }
        return breedList
    }
}
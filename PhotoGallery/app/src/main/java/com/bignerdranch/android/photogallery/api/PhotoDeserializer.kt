package com.bignerdranch.android.photogallery.api

import com.bignerdranch.android.photogallery.GalleryItem
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class PhotoDeserializer : JsonDeserializer<PhotoResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): PhotoResponse {
        return PhotoResponse().apply {
            galleryItems = (json as JsonObject)["photos"]
                .asJsonObject["photo"]
                .asJsonArray
                .map { photo ->
                    photo as JsonObject
                    GalleryItem(photo["title"].asString, photo["id"].asString, photo["url_s"].asString)
                }
        }
    }
}
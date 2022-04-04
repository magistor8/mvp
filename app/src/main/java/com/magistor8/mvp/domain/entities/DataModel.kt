package com.magistor8.mvp.domain.entities

import com.google.gson.annotations.SerializedName

class DataModel(
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?
)
class Meanings(
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imageUrl: String?
)
class Translation(@SerializedName("text") val translation: String?)

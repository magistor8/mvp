package com.magistor8.translator.domain.entities

import com.google.gson.annotations.SerializedName

private const val TEXT = "text"
private const val MEANINGS = "meanings"
private const val IMAGE_URL = "imageUrl"
private const val TRANSLATION = "translation"

class DataModel(
    @SerializedName(TEXT) val text: String?,
    @SerializedName(MEANINGS) val meanings: List<Meanings>?
)

class Meanings(
    @SerializedName(TRANSLATION) val translation: Translation?,
    @SerializedName(IMAGE_URL) val imageUrl: String?
)

class Translation(@SerializedName(TEXT) val translation: String?)

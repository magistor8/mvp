package com.magistor8.core.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

private const val TEXT = "text"
private const val MEANINGS = "meanings"
private const val IMAGE_URL = "imageUrl"
private const val SOUND_URL = "soundUrl"
private const val TRANSLATION = "translation"

@Parcelize
class DataModel(
    @SerializedName(TEXT) val text: String?,
    @SerializedName(MEANINGS) val meanings: List<Meanings>?
) : Parcelable

@Parcelize
class Meanings(
    @SerializedName(TRANSLATION) val translation: Translation?,
    @SerializedName(IMAGE_URL) val imageUrl: String?,
    @SerializedName(SOUND_URL) val soundUrl: String?
): Parcelable

@Parcelize
class Translation(
    @SerializedName(TEXT) val translation: String?
): Parcelable

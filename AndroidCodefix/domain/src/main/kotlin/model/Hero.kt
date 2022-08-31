package model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
): Parcelable
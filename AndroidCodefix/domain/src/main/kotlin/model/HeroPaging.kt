package model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroPaging(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val results: List<Hero>
): Parcelable
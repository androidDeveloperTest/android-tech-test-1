package com.example.presentation.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import model.Hero

@Parcelize
data class HeroVo(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
) : Parcelable

fun Hero.toVo() = HeroVo(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail
)
package com.ligasportquiz.freeforyou.bestplayer.menu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val id: Int,
    val image: Int,
    val rightAnswer: String
): Parcelable
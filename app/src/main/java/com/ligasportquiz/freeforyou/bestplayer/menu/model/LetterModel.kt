package com.ligasportquiz.freeforyou.bestplayer.menu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LetterModel(
    var id: Int,
    var letter: String
): Parcelable
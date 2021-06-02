package com.ddev.nestedrecyclerview.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val id: Int,
    val name: String,
    val customer: List<DataChild>
) : Parcelable
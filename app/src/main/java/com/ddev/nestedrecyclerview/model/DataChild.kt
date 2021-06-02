package com.ddev.nestedrecyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataChild (
    val childId: String,
    val collectCustomer: String,
    val collectAmount: Int,
    val clickCount: Int
): Parcelable
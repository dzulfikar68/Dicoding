package com.digitcreativestudio.dzulfikar68.kotlinexample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (val id: Int, val name: String?, val image: Int, val description: String?) : Parcelable
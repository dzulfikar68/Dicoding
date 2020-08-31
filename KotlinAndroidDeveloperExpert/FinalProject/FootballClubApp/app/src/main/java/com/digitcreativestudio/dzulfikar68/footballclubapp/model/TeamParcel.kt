package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamParcel(
    var teamId: String?,
    var teamName: String?,
    var teamBadge: String?,
    var teamJersey: String?,
    var teamCountry: String?,
    var teamStadium: String?,
    var teamFormedYear: String?,
    var teamWebsite: String?,
    var teamDescription: String?
) : Parcelable
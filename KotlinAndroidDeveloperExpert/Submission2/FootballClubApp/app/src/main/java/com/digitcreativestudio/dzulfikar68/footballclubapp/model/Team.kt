package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    var imageTeam: String? = null
)
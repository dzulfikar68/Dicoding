package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeamJersey")
    var teamJersey: String? = null,

    @SerializedName("strCountry")
    var teamCountry: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strWebsite")
    var teamWebsite: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
)
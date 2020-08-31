package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Table(
    @SerializedName("teamid")
    var idTeam: String? = null,

    @SerializedName("name")
    var nameTeam: String? = null,

    @SerializedName("played")
    var playedTeam: String? = null,

    @SerializedName("win")
    var winTeam: String? = null,

    @SerializedName("draw")
    var drawTeam: String? = null,

    @SerializedName("loss")
    var lossTeam: String? = null,

    @SerializedName("total")
    var totalTeam: String? = null
)
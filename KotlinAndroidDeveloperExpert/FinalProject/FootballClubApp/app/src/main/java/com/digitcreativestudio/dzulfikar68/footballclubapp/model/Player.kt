package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strThumb")
    var playerImage: String? = null,

    @SerializedName("strNumber")
    var playerNumber: String? = null,

    @SerializedName("dateBorn")
    var playerBorn: String? = null,

    @SerializedName("strNationality")
    var playerNationality: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDescriptionEN: String? = null
)
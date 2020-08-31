package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null,

    @SerializedName("strCountry")
    var leagueCountry: String? = null,

    @SerializedName("strWebsite")
    var linkWebsite: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionInEnglish: String? = null
)
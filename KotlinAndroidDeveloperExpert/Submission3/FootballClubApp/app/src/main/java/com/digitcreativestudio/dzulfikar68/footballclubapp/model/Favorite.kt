package com.digitcreativestudio.dzulfikar68.footballclubapp.model

data class Favorite(val id: Long?,
                    val eventId: String?,
                    val matchDate: String?,
                    val homeTeam: String?,
                    val homeScore: String?,
                    val awayTeam: String?,
                    val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"

//        Favorite.EVENT_ID to TEXT + UNIQUE,
//        Favorite.MATCH_DATE to TEXT,
//        Favorite.HOME_TEAM to TEXT,
//        Favorite.HOME_SCORE to TEXT,
//        Favorite.AWAY_TEAM to TEXT,
//        Favorite.AWAY_SCORE to TEXT
    }

//    @SerializedName("idEvent")
//    var eventId: String? = null,
//
//    @SerializedName("strDate")
//    var matchDate: String? = null,

//    @SerializedName("strHomeTeam")
//    var homeTeam: String? = null,
//
//    @SerializedName("intHomeScore")
//    var homeScore: String? = null,

//    @SerializedName("strAwayTeam")
//    var awayTeam: String? = null,
//
//    @SerializedName("intAwayScore")
//    var awayScore: String? = null,

}
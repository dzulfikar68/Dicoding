package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strDate")
    var matchDate: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    //===========================================

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var matchTime: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("intRound")
    var leagueRound: String? = null,

    //HomeTeam
    @SerializedName("idHomeTeam")
    var homeId: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strHomeRedCards")
    var homeRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeYellowCards: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var homeLineupSubstitutes: String? = null,

    //AwayTeam
    @SerializedName("idAwayTeam")
    var awayId: String? = null,

    @SerializedName("intAwayShots")
    var awayShots: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("strAwayRedCards")
    var awayRedCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayYellowCards: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayLineupMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayLineupForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var awayLineupSubstitutes: String? = null
)

//idHomeTeam
//"intHomeShots": "2",
//"strHomeGoalDetails": "",
//"strHomeRedCards": "",
//"strHomeYellowCards": "59':Joe Bryan;",
//"strHomeLineupGoalkeeper": "Sergio Rico; ",
//"strHomeLineupDefense": "Cyrus Christie; Maxime Le Marchand; Tim Ream; ",
//"strHomeLineupMidfield": "Ryan Sessegnon; Tom Cairney; Calum Chambers; Floyd Ayite; Joe Bryan; ",
//"strHomeLineupForward": "Ryan Babel; Aleksandar Mitrovic; ",
//"strHomeLineupSubstitutes": "Fabri; Tim Ream; Steven Sessegnon; Luca de la Torre; Neeskens Kebano; Ryan Babel; Harvey Elliott; ",

//idAwayTeam
//"intAwayShots": "6",
//"strAwayRedCards": "",
//"strAwayYellowCards": "",
//"strAwayGoalDetails": "10':Jonjo Shelvey;11':Ayoze Perez;62':Fabian Schaer;89':Jose Salomon Rondon;",
//"strAwayLineupGoalkeeper": "Martin Dubravka; ",
//"strAwayLineupDefense": "Fabian Schaer; Jamaal Lascelles; Paul Dummett; ",
//"strAwayLineupMidfield": "Javier Manquillo; Jonjo Shelvey; Isaac Hayden; Matt Ritchie; ",
//"strAwayLineupForward": "Ayoze Perez; Jose Salomon Rondon; Christian Atsu; ",
//"strAwayLineupSubstitutes": "Karl Darlow; Federico Fernandez; Ciaran Clark; Kenedy; Yoshinori Muto; Kelland Watts; Lewis Cass; ",
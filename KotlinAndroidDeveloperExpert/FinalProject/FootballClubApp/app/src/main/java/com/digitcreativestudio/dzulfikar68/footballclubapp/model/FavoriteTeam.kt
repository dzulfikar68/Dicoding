package com.digitcreativestudio.dzulfikar68.footballclubapp.model

class FavoriteTeam(val id: Long?,
                   val teamId: String?,
                   val teamName: String?,
                   val teamBadge: String?,
                   val teamJersey: String?,
                   val teamCountry: String?,
                   val teamStadium: String?,
                   val teamFormedYear: String?,
                   val teamWebsite: String?,
                   val teamDescription: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_JERSEY: String = "TEAM_JERSEY"
        const val TEAM_COUNTRY: String = "TEAM_COUNTRY"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_WEBSITE: String = "TEAM_WEBSITE"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}
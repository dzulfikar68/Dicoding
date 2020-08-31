package com.digitcreativestudio.dzulfikar68.footballclubapp.api

import android.net.Uri
import com.digitcreativestudio.dzulfikar68.footballclubapp.BuildConfig

object TheSportDBApi{

    fun getDetailLeague(league: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupleague.php?id=" + league
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.TSDB_API_KEY)
//            .appendPath("lookupleague.php")
//            .appendQueryParameter("id", league)
//            .build()
//            .toString()
    }

    fun getDetailMatch(event: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupevent.php?id=" + event
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.TSDB_API_KEY)
//            .appendPath("lookupevent.php")
//            .appendQueryParameter("id", event)
//            .build()
//            .toString()
    }

    fun getPreviousMatch(league: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" + league
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.TSDB_API_KEY)
//            .appendPath("eventspastleague.php")
//            .appendQueryParameter("id", league)
//            .build()
//            .toString()
    }

    fun getNextMatch(league: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=" + league
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.TSDB_API_KEY)
//            .appendPath("eventsnextleague.php")
//            .appendQueryParameter("id", league)
//            .build()
//            .toString()
    }

    fun getSearchMatch(keyword: String?): String{
        val search = keyword?.replace(" ", "%20")
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchevents.php?e=" + search
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//            .appendPath("api")
//            .appendPath("v1")
//            .appendPath("json")
//            .appendPath(BuildConfig.TSDB_API_KEY)
//            .appendPath("searchevents.php")
//            .appendQueryParameter("e", keyword)
////            .appendQueryParameter("s", "1819")
//            .build()
//            .toString()
    }

    fun getImageTeam(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", id)
//            .appendQueryParameter("s", "1819")
            .build()
            .toString()
    }
}
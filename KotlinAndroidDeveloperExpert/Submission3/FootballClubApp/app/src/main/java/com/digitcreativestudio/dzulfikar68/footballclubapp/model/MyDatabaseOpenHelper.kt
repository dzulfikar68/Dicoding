package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if(instance==null){
                instance = MyDatabaseOpenHelper(ctx)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.MATCH_DATE to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.AWAY_SCORE to TEXT
        )

//        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
//        const val ID: String = "ID_"
//        const val EVENT_ID: String = "EVENT_ID"
//        const val MATCH_DATE: String = "MATCH_DATE"
//        const val HOME_TEAM: String = "HOME_TEAM"
//        const val HOME_SCORE: String = "HOME_SCORE"
//        const val AWAY_TEAM: String = "AWAY_TEAM"
//        const val AWAY_SCORE: String = "AWAY_SCORE"
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }

    //akses properti -> konteks
    val Context.database : MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(applicationContext)

}
package com.digitcreativestudio.dzulfikar68.footballclubapp.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelperTeam(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelperTeam? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelperTeam{
            if(instance==null){
                instance = MyDatabaseOpenHelperTeam(ctx)
            }
            return instance as MyDatabaseOpenHelperTeam
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT,
            FavoriteTeam.TEAM_JERSEY to TEXT,
            FavoriteTeam.TEAM_COUNTRY to TEXT,
            FavoriteTeam.TEAM_STADIUM to TEXT,
            FavoriteTeam.TEAM_FORMED_YEAR to TEXT,
            FavoriteTeam.TEAM_WEBSITE to TEXT,
            FavoriteTeam.TEAM_DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }

    //akses properti -> konteks
    val Context.database_team : MyDatabaseOpenHelperTeam get() = MyDatabaseOpenHelperTeam.getInstance(applicationContext)

}
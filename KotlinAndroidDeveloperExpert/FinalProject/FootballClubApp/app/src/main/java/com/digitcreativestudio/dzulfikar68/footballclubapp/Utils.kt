package com.digitcreativestudio.dzulfikar68.footballclubapp

import android.content.Context
import android.view.View
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.MyDatabaseOpenHelper
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.MyDatabaseOpenHelperTeam

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.GONE
}

//akses properti -> konteks
val Context.database : MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(applicationContext)
val Context.database_team : MyDatabaseOpenHelperTeam get() = MyDatabaseOpenHelperTeam.getInstance(applicationContext)
package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.FavoritesFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.LeaguesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {
                item -> when (item.itemId){
            R.id.leagues -> {
                loadTeamsFragment(savedInstanceState)
            }
            R.id.favorites -> {
                loadFavoriteTeamsFragment(savedInstanceState)
            }
        }
            true
        }
        bottom_navigation.selectedItemId = R.id.leagues
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LeaguesFragment(), LeaguesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteTeamsFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                .commit()
        }
    }
}

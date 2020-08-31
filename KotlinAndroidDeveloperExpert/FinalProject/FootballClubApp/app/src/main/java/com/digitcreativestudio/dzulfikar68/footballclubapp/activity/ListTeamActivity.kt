package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.LeaguePagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.TeamPagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import kotlinx.android.synthetic.main.activity_list_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ListTeamActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
//            back button
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_team)

        val intent = intent
//        val data: String? = intent.getStringExtra("id_league")
        val data = intent.getParcelableExtra<Item>("item")
//        toast("id_league?: "+data.id)

        supportActionBar?.title = data.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //viewpagger
        viewpager_detail.adapter =
            TeamPagerAdapter(supportFragmentManager, data.id)
        tabs_main.setupWithViewPager(viewpager_detail)
    }
}

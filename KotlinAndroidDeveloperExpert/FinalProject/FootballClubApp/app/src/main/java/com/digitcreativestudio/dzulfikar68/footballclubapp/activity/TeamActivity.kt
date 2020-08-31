package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.digitcreativestudio.dzulfikar68.footballclubapp.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.PlayerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.api.ApiRepository
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.presenter.PlayerPresenter
import com.digitcreativestudio.dzulfikar68.footballclubapp.view.PlayerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class TeamActivity : AppCompatActivity(), PlayerView {

    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var data: TeamParcel
    private var menuItem: Menu? = null
    private lateinit var loading_player: ProgressBar
    private lateinit var alert_not_found: TextView
    private lateinit var self: Context
    private lateinit var team: Team
    private lateinit var id: String
    private var isFavorite: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

//            back button
            android.R.id.home -> {
                finish()
                true
            }

//            detailes button
            R.id.add_to_favorite -> {
                //team favorite
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database_team.use{
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }
    }

    private fun addToFavorite(){
        try{
            database_team.use{
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge,
                    FavoriteTeam.TEAM_JERSEY to team.teamJersey,
                    FavoriteTeam.TEAM_COUNTRY to team.teamCountry,
                    FavoriteTeam.TEAM_STADIUM to team.teamStadium,
                    FavoriteTeam.TEAM_FORMED_YEAR to team.teamFormedYear,
                    FavoriteTeam.TEAM_WEBSITE to team.teamWebsite,
                    FavoriteTeam.TEAM_DESCRIPTION to team.teamDescription
                )
            }
            toast("Added to favorite team")
//            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
//            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try{
            database_team.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})", "id" to id)
            }
            toast("Removed to favorite team")
//            snackbar(swipeRefresh, "Removed to favorite").show()
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_team)
        self = this

        val intent = intent
//        val data: String? = intent.getStringExtra("id_league")
        data = intent.getParcelableExtra<TeamParcel>("item")
        id = data.teamId.orEmpty()
        team = Team(data.teamId,
            data.teamName,
            data.teamBadge,
            data.teamJersey,
            data.teamCountry,
            data.teamStadium,
            data.teamFormedYear,
            data.teamWebsite,
            data.teamDescription)
//        toast("id_league?: "+data.id)

        favoriteState()
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loading_player = findViewById(R.id.loading)
        alert_not_found = findViewById(R.id.alert)

        name_team.text = data.teamName
        data.teamBadge.let {
            Picasso.get().load(it).into(badge_team)
        }
        data.teamJersey.let {
            Picasso.get().load(it).into(jersey_team)
        }
        description_team.text = data.teamDescription
        national_team.text = "@"+data.teamStadium+", "+data.teamCountry
        formed_team.text = "Since "+data.teamFormedYear
        website_team.text = data.teamWebsite

        description_team.onClick {
            self.alert(data.teamDescription.toString()) {
                noButton {}
            }.show()
        }

        list_player_team.layoutManager = GridLayoutManager(this, 3)
        adapter = PlayerAdapter(players)
        list_player_team.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getListPlayer(data.teamId)
    }

    override fun showLoading() {
        loading_player.visible()
    }

    override fun hideLoading() {
        loading_player.invisible()
    }

    override fun showNotFound() {
        alert_not_found.visible()
        alert_not_found.text = "data tidak tersedia"
    }

    override fun showListPlayer(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : PlayerAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                toast(players[pos].playerName.toString())
                self.alert(
                    "Name          : " + players[pos].playerName + "\n" +
                            "Number       : " + players[pos].playerNumber + "\n" +
                            "Nationality  : " + players[pos].playerNationality + "\n" +
                            "Born             : " + players[pos].playerBorn + "\n\n" +
                            players[pos].playerDescriptionEN
                ) {
                    noButton {}
                }.show()
            }
        })
    }
}

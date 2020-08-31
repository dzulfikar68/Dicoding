package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.DetailActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.TeamActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.FavoritesAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.FavoritesTeamAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.database
import com.digitcreativestudio.dzulfikar68.footballclubapp.database_team
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Favorite
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.FavoriteTeam
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.TeamParcel
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavTeamFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoritesTeamAdapter

    override fun onResume() {
        super.onResume()
        favorites.clear()
        showFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //adapter
        adapter = FavoritesTeamAdapter(favorites){
            val parcel = TeamParcel(
                it.teamId,
                it.teamName,
                it.teamBadge,
                it.teamJersey,
                it.teamCountry,
                it.teamStadium,
                it.teamFormedYear,
                it.teamWebsite,
                it.teamDescription
            )
            context?.startActivity<TeamActivity>("item" to parcel)
        }
        listTeam.adapter = adapter

        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite(){
        context?.database_team?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView{
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        }


    }
}
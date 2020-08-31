package com.digitcreativestudio.dzulfikar68.footballclubapp.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Favorite
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoritesTeamAdapter(private val favorites: List<FavoriteTeam>,
                           private val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<FavoritesTeamAdapter.FavoritesTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTeamViewHolder {
        return FavoritesTeamViewHolder(FavoritesTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoritesTeamViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

    class FavoritesTeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val teamName: TextView = view.find(R.id.team_name)
        private val teamBadge: ImageView = view.find(R.id.team_badge)

        fun bindItem(team: FavoriteTeam, listener: (FavoriteTeam) -> Unit){
            teamName.text = team.teamName
            Picasso.get().load(team.teamBadge).into(teamBadge)

            itemView.onClick {
                listener(team)
            }
        }
    }

    class FavoritesTeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(10)
                    orientation = LinearLayout.HORIZONTAL
//                    gravity = Gravity.CENTER

                    imageView{
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView{
                        id = R.id.team_name
                        textSize = 16f
                        textColor = Color.BLACK
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        gravity = Gravity.CENTER
                        leftMargin = dip(10)
                    }
                }
            }
        }

    }
}
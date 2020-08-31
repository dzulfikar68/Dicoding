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
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoritesAdapter(private val favorites: List<Favorite>,
                       private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(FavoritesUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val homeTeamName: TextView = view.find(R.id.home_team_name)
        private val awayTeamName: TextView = view.find(R.id.away_team_name)
        private val homeTeamScore: TextView = view.find(R.id.home_team_score)
        private val awayTeamScore: TextView = view.find(R.id.away_team_score)
        private val matchDate: TextView = view.find(R.id.match_date)

        fun bindItem(event: Favorite, listener: (Favorite) -> Unit){
            homeTeamName.text = event.homeTeam
            awayTeamName.text = event.awayTeam
            homeTeamScore.text = event.homeScore
            awayTeamScore.text = event.awayScore
            matchDate.text = event.matchDate

            itemView.onClick {
                listener(event)
            }
        }
    }

    class FavoritesUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 2f)
                        orientation = LinearLayout.VERTICAL

                        textView{
                            id = R.id.home_team_name
                            textSize = 16f
                            textColor = Color.BLACK
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams{
                            margin = dip(5)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        textView{
                            id = R.id.home_team_score
                            text = "0"
                            textSize = 12f
                            textColor = Color.BLUE
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 1f)
                        orientation = LinearLayout.VERTICAL

                        textView{
                            text = "VS"
                            textSize = 16f
                            textColor = Color.RED
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        textView{
                            id = R.id.match_date
                            text = "-"
                            textSize = 12f
                            textColor = Color.GRAY
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                    }.lparams{
                        leftMargin = dip(20)
                        rightMargin = dip(20)
                    }

                    linearLayout {
                        lparams(width = 0, height = wrapContent, weight = 2f)
                        orientation = LinearLayout.VERTICAL

                        textView{
                            id = R.id.away_team_name
                            textSize = 16f
                            textColor = Color.BLACK
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams{
                            margin = dip(5)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        textView{
                            id = R.id.away_team_score
                            text = "0"
                            textSize = 12f
                            textColor = Color.BLUE
                        }.lparams{
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
            }
        }

    }
}
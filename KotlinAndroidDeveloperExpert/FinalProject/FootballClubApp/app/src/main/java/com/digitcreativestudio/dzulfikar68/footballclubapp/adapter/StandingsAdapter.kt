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
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class StandingsAdapter(private val lists: List<Table>) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        return StandingsViewHolder(StandingsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.bindItem(lists[position])
    }

    inner class StandingsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val teamName: TextView = view.find(R.id.team_name)
        private val teamPlayed: TextView = view.find(R.id.team_played)
        private val teamWin: TextView = view.find(R.id.team_win)
        private val teamDraw: TextView = view.find(R.id.team_draw)
        private val teamLoss: TextView = view.find(R.id.team_loss)
        private val teamTotal: TextView = view.find(R.id.team_total)

        fun bindItem(team: Table){
//            <item name="team_played" type="id"/>
            teamName.text = team.nameTeam
//            <item name="team_played" type="id"/>
            teamPlayed.text = team.playedTeam
//            <item name="team_win" type="id"/>
            teamWin.text = team.winTeam
//            <item name="team_draw" type="id"/>
            teamDraw.text = team.drawTeam
//            <item name="team_loss" type="id"/>
            teamLoss.text = team.lossTeam
//            <item name="team_total" type="id"/>
            teamTotal.text = team.totalTeam
        }
    }

    class StandingsUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.HORIZONTAL

                    textView{
                        id = R.id.team_name
                        textSize = 14f
                        textColor = Color.BLACK
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        leftMargin = dip(5)
                        width = dip(100)
                    }

                    textView{
                        id = R.id.team_played
                        textSize = 14f
                    }.lparams {
                        verticalGravity = Gravity.CENTER_HORIZONTAL
                        width = dip(50)
                    }

                    textView{
                        id = R.id.team_win
                        textSize = 14f
                    }.lparams {
                        verticalGravity = Gravity.CENTER_HORIZONTAL
                        width = dip(50)
                    }

                    textView{
                        id = R.id.team_draw
                        textSize = 14f
                    }.lparams {
                        verticalGravity = Gravity.CENTER_HORIZONTAL
                        width = dip(50)
                    }

                    textView{
                        id = R.id.team_loss
                        textSize = 14f
                    }.lparams {
                        verticalGravity = Gravity.CENTER_HORIZONTAL
                        width = dip(50)
                    }

                    textView{
                        id = R.id.team_total
                        textSize = 14f
                    }.lparams {
                        verticalGravity = Gravity.CENTER_HORIZONTAL
                        width = dip(50)
                    }
                }
            }
        }

    }
}
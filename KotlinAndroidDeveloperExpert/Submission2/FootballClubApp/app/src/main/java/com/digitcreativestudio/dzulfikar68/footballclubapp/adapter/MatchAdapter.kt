package com.digitcreativestudio.dzulfikar68.footballclubapp.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Event
import org.jetbrains.anko.*

class MatchAdapter(private val events: List<Event>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

    inner class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
//        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val homeTeamName: TextView = view.find(R.id.home_team_name)
        private val awayTeamName: TextView = view.find(R.id.away_team_name)
        private val homeTeamScore: TextView = view.find(R.id.home_team_score)
        private val awayTeamScore: TextView = view.find(R.id.away_team_score)
        private val matchDate: TextView = view.find(R.id.match_date)

        fun bindItem(event: Event){
//            Picasso.get().load(event.teamBadge).into(teamBadge)
            homeTeamName.text = event.homeTeam
            awayTeamName.text = event.awayTeam
            homeTeamScore.text = event.homeScore
            awayTeamScore.text = event.awayScore
            matchDate.text = event.matchDate
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    class MatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

//                    imageView{
//                        id = R.id.team_badge
//                    }.lparams{
//                        height = dip(50)
//                        width = dip(50)
//                    }

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
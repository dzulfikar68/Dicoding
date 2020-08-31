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
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Table
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamsAdapter(private val lists: List<Team>) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(TeamsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(lists[position])
    }

    inner class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        //        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)
        private val teamBadge: ImageView = view.find(R.id.team_badge)

        fun bindItem(team: Team){
            teamName.text = team.teamName
            Picasso.get().load(team.teamBadge).into(teamBadge)
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    class TeamsUI : AnkoComponent<ViewGroup> {
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
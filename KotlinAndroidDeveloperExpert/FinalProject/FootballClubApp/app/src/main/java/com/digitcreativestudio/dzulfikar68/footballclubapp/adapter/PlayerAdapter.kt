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
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Player
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerAdapter(private val lists: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(lists[position])
    }

    inner class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        //        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val playerName: TextView = view.find(R.id.player_name)
        private val playerImage: ImageView = view.find(R.id.player_image)

        fun bindItem(player: Player){
            playerName.text = player.playerName
            Picasso.get().load(player.playerImage).into(playerImage)
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    class PlayerUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(5)
                    orientation = LinearLayout.VERTICAL
//                    gravity = Gravity.CENTER

                    imageView{
                        id = R.id.player_image
                    }.lparams{
                        height = 175
                        width = matchParent
                    }

                    textView{
                        id = R.id.player_name
                        textSize = 16f
                        textColor = Color.WHITE
                        backgroundColor = Color.BLACK
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams{
                        height = wrapContent
                        width = wrapContent
//                        alignParentTop()
//                        alignParentLeft()
//                        rightMargin = dip(5)
                    }
                }
            }
        }

    }
}
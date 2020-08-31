package com.digitcreativestudio.dzulfikar68.footballclubapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MainAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    //LayoutContainer menghilangkan itemView
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
//        private val name = view.findViewById<TextView>(R.id.name)
//        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(item: Item, listener: (Item) -> Unit){
            name_league.text = item.name
            item.image.let {
                Picasso.get().load(it).into(image_league)
            }

            containerView.setOnClickListener{
                listener(item)
            }
        }
    }
}
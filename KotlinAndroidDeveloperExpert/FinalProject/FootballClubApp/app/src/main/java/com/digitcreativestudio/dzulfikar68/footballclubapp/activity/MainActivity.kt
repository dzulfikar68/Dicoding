package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MainAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

// Created by @dzulfikar68
// Copyright by @dicoding

class MainActivity : AppCompatActivity() {

    private lateinit var listLeague: RecyclerView
    private lateinit var search: EditText

    private var leagues: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            linearLayout {
                orientation = LinearLayout.HORIZONTAL

                search = editText {
                    hint = "find event by keywords"
                    singleLine = true
                }.lparams{
                    height = wrapContent
                    width = dip(0)
                    weight = 4f
                }

                button("search"){
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
//                    textSize = 10f
                    setBackgroundColor(Color.GRAY)
                    setOnClickListener {
//                        toast(search.text)
                        startActivity<SearchActivity>("item" to "${search.text}")
                    }
                }.lparams{
                    height = wrapContent
                    width = dip(0)
                    weight = 1f
                }

            }.lparams {
                width = matchParent
                height = wrapContent
                bottomMargin = dip(10)
            }

            listLeague = recyclerView{
                lparams(width = matchParent, height = wrapContent)
//                layoutManager = LinearLayoutManager(context)
                id = R.id.list_league
            }
        }

        initData()

        listLeague.layoutManager = GridLayoutManager(this, 2)
        listLeague.adapter =
            MainAdapter(this, leagues) {
                startActivity<LeagueActivity>("item" to it)
            }
    }

    private fun initData(){
        val id_league = resources.getIntArray(R.array.id_league)
        val name_league = resources.getStringArray(R.array.name_league)
        val image_league = resources.obtainTypedArray(R.array.image_league)
        leagues.clear()
        for(i in id_league.indices){
            leagues.add(
                Item(
                    id_league[i].toString(),
                    name_league[i],
                    image_league.getResourceId(i, 0)
                )
            )
        }
    }
}

package com.digitcreativestudio.dzulfikar68.footballclubapp.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.LeagueActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.activity.SearchActivity
import com.digitcreativestudio.dzulfikar68.footballclubapp.adapter.MainAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.model.Item
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class LeaguesFragment : Fragment(), AnkoComponent<Context> {
    private lateinit var listLeague: RecyclerView;
    private lateinit var search: EditText;

    private var leagues: MutableList<Item> = mutableListOf()

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
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
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initData()

        listLeague.layoutManager = GridLayoutManager(ctx, 2)
        listLeague.adapter =
            MainAdapter(ctx, leagues) {
                context?.startActivity<LeagueActivity>("item" to it)
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
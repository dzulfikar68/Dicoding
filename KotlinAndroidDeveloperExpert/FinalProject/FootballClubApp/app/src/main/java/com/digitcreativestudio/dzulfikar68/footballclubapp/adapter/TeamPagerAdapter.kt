package com.digitcreativestudio.dzulfikar68.footballclubapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.NextFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.PreviousFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.StandingsFragment
import com.digitcreativestudio.dzulfikar68.footballclubapp.fragment.TeamsFragment

class TeamPagerAdapter(fm: FragmentManager, id: String): FragmentPagerAdapter(fm){
    // Creating the new Fragment with the name passed in.
    val fragment1 = StandingsFragment.newInstance(id)
    val fragment2 = TeamsFragment.newInstance(id)

    // sebuah list yang menampung objek Fragment
    private val pages = listOf(
        fragment1,
        fragment2
    )

    // menentukan fragment yang akan dibuka pada posisi tertentu
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    // judul untuk tabs
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "League Standings"
            else -> "List Team"
        }
    }
}
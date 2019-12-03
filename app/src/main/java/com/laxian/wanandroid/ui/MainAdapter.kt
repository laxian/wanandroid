package com.laxian.wanandroid.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laxian.wanandroid.ui.home.HomeFragment

class MainAdapter(fg: FragmentActivity, val frags: List<String>) : FragmentStateAdapter(fg) {
    override fun getItemCount(): Int = frags.size

    override fun createFragment(position: Int): Fragment {
        return HomeFragment()
    }
}
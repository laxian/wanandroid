package com.laxian.wanandroid.ui

import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.laxian.ktx.base.BaseActivity
import com.laxian.wanandroid.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity() {

    private val titleList = listOf("首页", "广场", "最新项目", "体系", "导航")

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        view_pager.adapter = MainAdapter(this, titleList)
//        TabLayout和ViewPager的绑定
        val tabLayoutMediator = TabLayoutMediator(
            tab_layout,
            view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.setText(titleList[position]) });
        tabLayoutMediator.attach();
    }

    override fun initData() {
    }
}

package com.laxian.wanandroid.ui.home

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.laxian.ktx.base.BaseVMFragment
import com.laxian.ktx.ext.toast
import com.laxian.wanandroid.R
import com.laxian.wanandroid.model.bean.ArticleList
import com.laxian.wanandroid.model.bean.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseVMFragment<HomeViewModel>() {

    private lateinit var articleAdapter: ArticleAdapter
    private val homeViewModel: HomeViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(
                context: Context?,
                path: Any?,
                imageView: ImageView?
            ) {
                imageView?.let { Glide.with(this@HomeFragment).load(path).into(it) }
            }
        })

        rv_articles.layoutManager = LinearLayoutManager(context)
    }

    override fun initData() {
        homeViewModel.getBanner()
        homeViewModel.getHomeArticles(0)
    }

    override fun startObserve() {
        super.startObserve()
        homeViewModel.apply {
            bannerData.observe(this@HomeFragment, Observer {
                setBanner(it)
            })
            articles.observe(this@HomeFragment, Observer {
                setArticles(it)
            })
        }
    }

    private fun setArticles(articles: ArticleList?) {
        if (rv_articles.adapter == null) {
            articleAdapter = ArticleAdapter(context!!, articles!!.datas.toMutableList())
            rv_articles.adapter = articleAdapter
        } else {
            articleAdapter.addArticles(articles?.datas)
        }
    }

    private fun setBanner(banners: List<Banner>?) {
        banner.setImages(banners?.map { it.imagePath })
            .setBannerTitles(banners?.map { it.title })
            .setDelayTime(3000).start()
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        context?.let { toast(it, e.message ?: e.toString()) }
    }

    override fun getVM(): HomeViewModel? = homeViewModel
}

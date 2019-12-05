package com.laxian.wanandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laxian.wanandroid.R
import com.laxian.wanandroid.model.bean.Article

class ArticleAdapter(val context: Context, val articles: MutableList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_articles, parent, false)
        return VH(view)
    }

    fun addArticles(articles: List<Article>?) {
        articles?.let {
            this.articles.addAll(articles)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        articles[position].let {
            holder.apply {
                tv_author.text = it.author.ifEmpty { it.shareUser }
                tv_tag.text = if (it.tags.isNullOrEmpty()) "" else it.tags[0].toString()
                tv_title.text = it.title
                tv_time.text = it.publishTime.toString()
            }
        }
    }

    class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_author: TextView
        var tv_tag: TextView
        var tv_title: TextView
        var tv_time: TextView

        init {
            tv_author = itemView.findViewById(R.id.tv_author)
            tv_tag = itemView.findViewById(R.id.tv_tag)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_time = itemView.findViewById(R.id.tv_time)
        }

    }
}
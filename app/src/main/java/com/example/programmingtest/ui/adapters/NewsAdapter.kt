package com.example.programmingtest.ui.adapters

/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 *
 * adapter class for mapping data in layout

 */


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.programmingtest.databinding.RvNewsBinding
import com.example.programmingtest.model.News
import kotlinx.android.synthetic.main.rv_news.view.*


class NewsAdapter(
    private val context: Context,
    val onClick: (News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    private val differCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }
    var differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder =
        NewsViewHolder(RvNewsBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.itemView.title.text = news.title
        if (!news.media.isNullOrEmpty())
            Glide.with(context).load(
                if (news.media?.get(0)?.mediaMetaData.isNullOrEmpty()) null else news.media?.get(0)?.mediaMetaData?.get(
                    0
                )?.url
            )
                .into(holder.itemView.image)
        holder.itemView.datetime.text = news.publishedDate.orEmpty()
        holder.itemView.byline.text = news.byline.orEmpty()

        holder.itemView.cardview.setOnClickListener { onClick(news) }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // This inner class is mapping recycler view with view holder with layout file

    inner class NewsViewHolder(itemView: RvNewsBinding) : RecyclerView.ViewHolder(itemView.root)

}
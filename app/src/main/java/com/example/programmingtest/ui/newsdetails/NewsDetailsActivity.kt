package com.example.programmingtest.ui.newsdetails

/**
 * @author Abdullah Mansoor
 * @Date 8/13/22
 *
 * This is the detail activity where user can see the news details
 */


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.programmingtest.databinding.ActivityNewsDetailsBinding
import com.example.programmingtest.model.News


class NewsDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsDetailsBinding.inflate(layoutInflater)
    }

    private val newsDetails: News by lazy {
        intent.getSerializableExtra(NEWS_DETAILS) as News
    }

    companion object {
        const val NEWS_DETAILS = "NEWS_DETAILS"
        fun newIntent(context: Context, news: News): Intent {
            return Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(NEWS_DETAILS, news)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            back.setOnClickListener { onBackPressed() }
            title.text = newsDetails.title
            source.text = newsDetails.source
            datetime.text = newsDetails.publishedDate
            description.text = newsDetails.abstract
            byline.text = newsDetails.byline
            Glide.with(this@NewsDetailsActivity)
                .load(newsDetails.media?.get(0)?.mediaMetaData?.get(0)?.url)
                .into(image)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
package com.example.programmingtest.ui

/**
 * @author Abdullah Mansoor
 * @Date 8/12/22
 *
 * This is the view class interacting with viewmodel
 */

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.programmingtest.R
import com.example.programmingtest.databinding.ActivityMainBinding
import com.example.programmingtest.model.News
import com.example.programmingtest.model.Resource
import com.example.programmingtest.repository.NewsRepository
import com.example.programmingtest.ui.adapters.NewsAdapter
import com.example.programmingtest.ui.newsdetails.NewsDetailsActivity
import com.example.programmingtest.viewmodel.NewsViewModel
import com.example.programmingtest.viewmodel.ViewModelProviderFactory


class MainActivity : AppCompatActivity() {
    private var selectedPeriod: Int = 7

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, ViewModelProviderFactory(NewsRepository())).get(
            NewsViewModel::class.java
        )
    }

    companion object {
        fun newsActivity(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        if (!checkInternet()) noInternetDialog()
        loadNews(selectedPeriod)
        onSwipeListener()
        initObserver()
    }


    // Requesting data for news based on period of day
    private fun loadNews(period: Int) {
        selectedPeriod = period
        viewModel.getMostPopularNewsByPeriod(period)
    }
    // Setting the name of the title
    private fun setupToolbar() {
        supportActionBar?.title = getString(R.string.toolbar)
    }

    // This method will show the swiper when the api is getting response from the server
    private fun onSwipeListener() {
        binding.refreshing.setOnRefreshListener {
            loadNews(selectedPeriod)
        }
    }

    // Data observer for news data response using LiveData Observer
    private fun initObserver() {
        viewModel.mostPopularNewsListLiveData.observe(this) {
            it ?: return@observe
            when (it) {
                is Resource.Loading -> {
                    binding.refreshing.isRefreshing = true
                }
                is Resource.Error -> {
                    binding.refreshing.isRefreshing = false
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Success -> {
                    Toast.makeText(
                        this,
                        "News Fetched Completed of $selectedPeriod Day",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.refreshing.isRefreshing = false
                    setupNewsAdatper(it.data?.results!!)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // This method is for click on the menubar icon on the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.periodFilter -> {
                showPeriodFilterDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // This method will show a dialog of the menu from where user can select any time period
    private fun showPeriodFilterDialog() {
        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle(getString(R.string.choose_period))
        val items = arrayOf("1 Day", "7 Day", "30 Days")
        var selected = 0
        when (selectedPeriod) {
            1 -> selected = 0
            7 -> selected = 1
            30 -> selected = 2
        }
        alertDialog.setSingleChoiceItems(
            items, selected
        ) { dialog, which ->
            when (which) {
                0 -> loadNews(1)
                1 -> loadNews(7)
                2 -> loadNews(30)
            }
            dialog.dismiss()
        }
        val alert = alertDialog.create()
        alert.show()
    }

   // Setting list of news articles into News adapter class
    private fun setupNewsAdatper(data: List<News>) {
        var adapter = NewsAdapter(this) {
            startActivity(NewsDetailsActivity.newIntent(this@MainActivity, it))
        }
        adapter.differ.submitList(data)
        binding.rvNewsList.adapter = adapter
    }

  // This dialog will appear if there is no internet connection
    private fun noInternetDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setCancelable(false)
                .setTitle(getString(R.string.no_internet))
                .setMessage(getString(R.string.no_internet_details))
                .setPositiveButton(
                    "OK"
                ) { dialog, p -> finishAffinity() }
                .create()
        }
        alertDialog.show()
    }

   // This method is checking the internet connection on the device
    @SuppressWarnings
    fun checkInternet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
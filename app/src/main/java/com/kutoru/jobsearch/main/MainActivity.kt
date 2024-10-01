package com.kutoru.jobsearch.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kutoru.jobsearch.JSApplication
import com.kutoru.jobsearch.R
import com.kutoru.jobsearch.databinding.ActivityMainBinding
import com.kutoru.jobsearch.empty.EmptyFragment
import com.kutoru.jobsearch.favorite.FavoriteFragment
import com.kutoru.jobsearch.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as JSApplication)
            .appComponent
            .favoriteContainer()
            .favoriteCount
            .observe(this, ::updateFavoriteBadge)

        binding.navContainer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navSearch -> loadFragment(SearchFragment())
                R.id.navFavorite -> loadFragment(FavoriteFragment())
                R.id.navResponses,
                R.id.navMessages,
                R.id.navProfile -> loadFragment(EmptyFragment())
                else -> return@setOnItemSelectedListener false
            }

            return@setOnItemSelectedListener true
        }

        loadFragment(SearchFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentContainer, fragment)
            .commit()
    }

    private fun updateFavoriteBadge(number: Int) {
        if (number < 1) {
            val badge = binding.navContainer.getBadge(R.id.navFavorite)
            badge?.isVisible = false
        } else {
            val badge = binding.navContainer.getOrCreateBadge(R.id.navFavorite)
            badge.number = number
            badge.isVisible = true
        }
    }
}

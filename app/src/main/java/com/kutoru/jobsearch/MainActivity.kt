package com.kutoru.jobsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kutoru.jobsearch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}

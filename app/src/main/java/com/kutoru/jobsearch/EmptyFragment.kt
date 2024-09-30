package com.kutoru.jobsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kutoru.jobsearch.databinding.ActivityMainBinding
import com.kutoru.jobsearch.databinding.FragmentEmptyBinding

class EmptyFragment : Fragment() {

    private lateinit var binding: FragmentEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmptyBinding.inflate(layoutInflater)
        return binding.root
    }
}

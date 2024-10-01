package com.kutoru.jobsearch.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kutoru.jobsearch.JobSearchApplication
import com.kutoru.jobsearch.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as JobSearchApplication)
            .appComponent
            .searchComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val result = searchViewModel.getOffers()
            println("getOffers result: $result")

            val result2 = searchViewModel.getVacancies(1)
            println("getVacancies result: $result2")
        }
    }
}

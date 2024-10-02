package com.kutoru.jobsearch.search

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kutoru.jobsearch.JobSearchApplication
import com.kutoru.jobsearch.R
import com.kutoru.jobsearch.databinding.FragmentSearchBinding
import com.kutoru.jobsearch.views.OfferAdapter
import com.kutoru.jobsearch.views.VacancyAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var offerAdapter: OfferAdapter
    private lateinit var vacancyAdapter: VacancyAdapter

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

        offerAdapter = OfferAdapter(listOf())
        vacancyAdapter = VacancyAdapter(listOf(), ::setVacancyFavorite)

        val margin = resources.getDimension(R.dimen.margin).toInt()
        val marginHalf = resources.getDimension(R.dimen.margin_half).toInt()

        binding.searchOffers.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) > 0) {
                    outRect.left = marginHalf
                }
            }
        })

        binding.searchOffers.adapter = offerAdapter
        val offerLayoutManager = LinearLayoutManager(context)
        offerLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.searchOffers.layoutManager = offerLayoutManager

        binding.searchVacancies.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) > 0) {
                    outRect.top = margin
                }
            }
        })

        binding.searchVacancies.adapter = vacancyAdapter
        binding.searchVacancies.layoutManager = LinearLayoutManager(context)

        binding.searchMoreVacancies.setOnClickListener {
            expandVacancies()
        }

        setupObservers()

        return binding.root
    }

    override fun onResume() {
        lifecycleScope.launch {
            val (offerResult, vacancyResult) = searchViewModel.reloadAll()

            if (offerResult.isFailure) {
                showMessage("Could not get offers", offerResult)
            }

            if (vacancyResult.isFailure) {
                showMessage("Could not get vacancies", vacancyResult)
            }
        }

        super.onResume()
    }

    private fun setupObservers() {
        searchViewModel.offers.observe(viewLifecycleOwner) { offers ->
            offerAdapter.offers = offers
            offerAdapter.notifyDataSetChanged()
        }

        searchViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            vacancyAdapter.vacancies = vacancies
            vacancyAdapter.notifyDataSetChanged()
        }

        searchViewModel.totalVacancies.observe(viewLifecycleOwner) { totalVacancies ->
            val vacanciesLeft = totalVacancies - searchViewModel.vacancies.value!!.size
            binding.searchMoreVacancies.text = resources.getQuantityString(
                R.plurals.more_vacancies, vacanciesLeft, vacanciesLeft,
            )
        }

        searchViewModel.expanded.observe(viewLifecycleOwner) { expanded ->
            println("expanded change: $expanded")
        }
    }

    private fun expandVacancies() {
        searchViewModel.expanded.value = true
    }

    private fun setVacancyFavorite(id: String, isFavorite: Boolean) {
        lifecycleScope.launch {
            val result = searchViewModel.setVacancyFavorite(id, isFavorite)
            if (result.isFailure) {
                showMessage("Could not update vacancy favorite status", result)
            }
        }
    }

    private fun showMessage(message: String, data: Any? = null) {
        if (data != null) {
            Log.w("app", "$message; $data")
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

package com.kutoru.jobsearch.favorite

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
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kutoru.jobsearch.JobSearchApplication
import com.kutoru.jobsearch.R
import com.kutoru.jobsearch.databinding.FragmentFavoriteBinding
import com.kutoru.jobsearch.models.Vacancy
import com.kutoru.jobsearch.views.VacancyAdapterDelegate
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var vacancyAdapter: ListDelegationAdapter<List<Vacancy>>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as JobSearchApplication)
            .appComponent
            .favoriteComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        vacancyAdapter = ListDelegationAdapter(
            VacancyAdapterDelegate.init(::setVacancyFavorite),
        )

        val margin = resources.getDimension(R.dimen.margin).toInt()

        binding.favVacancyList.addItemDecoration(object : RecyclerView.ItemDecoration() {
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

        binding.favVacancyList.adapter = vacancyAdapter
        binding.favVacancyList.layoutManager = LinearLayoutManager(context)

        setupObservers()

        return binding.root
    }

    override fun onResume() {
        lifecycleScope.launch {
            val result = favoriteViewModel.reloadVacancies()
            if (result.isFailure) {
                Log.w("app", "$result")
                Toast.makeText(context, "Could not load vacancies", Toast.LENGTH_LONG).show()
            }
        }

        super.onResume()
    }

    private fun setupObservers() {
        favoriteViewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            vacancyAdapter.items = vacancies
            vacancyAdapter.notifyDataSetChanged()

            binding.favVacancyCount.text = resources.getQuantityString(
                R.plurals.vacancy, vacancies.size, vacancies.size,
            )
        }
    }

    private fun setVacancyFavorite(id: String, isFavorite: Boolean) {
        lifecycleScope.launch {
            val result = favoriteViewModel.setVacancyFavorite(id, isFavorite)
            if (result.isFailure) {
                Log.w("app", "$result")
                Toast.makeText(context, "Could not update vacancy favorite status", Toast.LENGTH_LONG).show()
            }
        }
    }
}

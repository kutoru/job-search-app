package com.kutoru.jobsearch

import android.content.Context
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.kutoru.jobsearch.databinding.CardVacancyBinding
import com.kutoru.jobsearch.models.Vacancy

class VacancyCardView(context: Context) : CardView(context) {
    private val binding: CardVacancyBinding

    init {
        binding = CardVacancyBinding.inflate(
            LayoutInflater.from(context), this, false,
        )

        addView(binding.root)
    }

    fun setVacancy(vacancy: Vacancy) {
        binding.cardVacTitle.text = vacancy.title
        binding.cardVacTown.text = vacancy.address.town
    }
}

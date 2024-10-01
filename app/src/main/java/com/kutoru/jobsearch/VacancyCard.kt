package com.kutoru.jobsearch

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import com.kutoru.jobsearch.databinding.CardVacancyBinding
import com.kutoru.jobsearch.models.Vacancy
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VacancyCardView(context: Context) : CardView(context) {
    private val binding: CardVacancyBinding

    init {
        binding = CardVacancyBinding.inflate(
            LayoutInflater.from(context), this, false,
        )

        addView(binding.root)

        this.background = AppCompatResources.getDrawable(context, android.R.color.transparent)
    }

    fun setVacancy(vacancy: Vacancy, setFavorite: (String, Boolean) -> Unit) {
        if (vacancy.lookingNumber != null) {
            binding.cardVacViewNumber.text = resources.getQuantityString(
                R.plurals.people_currently_viewing, vacancy.lookingNumber, vacancy.lookingNumber,
            )
            binding.cardVacViewNumber.visibility = VISIBLE
        } else {
            binding.cardVacViewNumber.visibility = GONE
        }

        binding.cardVacFavorite.isFavorite = vacancy.isFavorite
        binding.cardVacFavorite.setOnClickListener {
            setFavorite(vacancy.id, !vacancy.isFavorite)
        }

        binding.cardVacTitle.text = vacancy.title
        binding.cardVacTown.text = vacancy.address.town
        binding.cardVacCompany.text = vacancy.company
        binding.cardVacExperience.text = vacancy.experience.previewText

        val date = vacancy.publishedDate.split('-')
        val day = date.getOrNull(2)?.toInt()
        val month = date.getOrNull(1)?.toInt()?.dec()

        var monthFormatted: String? = null
        if (day != null && month != null) {
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_MONTH, day)
            cal.set(Calendar.MONTH, month)
            monthFormatted = SimpleDateFormat("MMMM", Locale("ru")).format(cal.time)
        }

        binding.cardVacPublished.text = "Опубликовано $day $monthFormatted"
    }
}

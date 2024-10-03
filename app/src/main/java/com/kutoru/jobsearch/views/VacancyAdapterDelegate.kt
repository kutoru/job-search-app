package com.kutoru.jobsearch.views

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kutoru.jobsearch.R
import com.kutoru.jobsearch.databinding.CardVacancyBinding
import com.kutoru.jobsearch.models.Vacancy
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VacancyAdapterDelegate {
    companion object {
        fun init(setFavorite: (String, Boolean) -> Unit) = adapterDelegateViewBinding(
            { layoutInflater, parent -> CardVacancyBinding.inflate(layoutInflater, parent, false) }
        ) {

            // if i don't post delayed, AdapterDelegates throws
            // IllegalArgumentException saying that the item hasn't been set yet

            itemView.visibility = View.GONE
            itemView.postDelayed({
                setVacancy(
                    this.binding,
                    this.context,
                    this.item,
                    setFavorite,
                )
                itemView.visibility = View.VISIBLE
            }, 10)
        }

        private fun setVacancy(
            binding: CardVacancyBinding,
            context: Context,
            vacancy: Vacancy,
            setFavorite: (String, Boolean) -> Unit
        ) {
            if (vacancy.lookingNumber != null) {
                binding.cardVacViewNumber.text = context.resources.getQuantityString(
                    R.plurals.people_currently_viewing,
                    vacancy.lookingNumber,
                    vacancy.lookingNumber,
                )
                binding.cardVacViewNumber.visibility = CardView.VISIBLE
            } else {
                binding.cardVacViewNumber.visibility = CardView.GONE
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

            binding.cardVacPublished.text =
                context.resources.getString(R.string.published_date, "$day $monthFormatted")
        }
    }
}
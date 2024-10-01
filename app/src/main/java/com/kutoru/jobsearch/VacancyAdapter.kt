package com.kutoru.jobsearch

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutoru.jobsearch.models.Vacancy

class VacancyAdapter(
//    private val itemMargin: Int,
    var vacancies: List<Vacancy>,
    private val setFavorite: (id: String, isFavorite: Boolean) -> Unit,
) : RecyclerView.Adapter<VacancyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = VacancyCardView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        return ViewHolder(view)
    }

    override fun getItemCount() = vacancies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as VacancyCardView).setVacancy(vacancies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

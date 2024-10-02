package com.kutoru.jobsearch.views

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutoru.jobsearch.models.Offer

class OfferAdapter(
    var offers: List<Offer>,
) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = OfferCardView(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount() = offers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as OfferCardView).setOffer(offers[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

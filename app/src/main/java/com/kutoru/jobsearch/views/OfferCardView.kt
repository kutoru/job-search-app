package com.kutoru.jobsearch.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import com.kutoru.jobsearch.R
import com.kutoru.jobsearch.databinding.CardOfferBinding
import com.kutoru.jobsearch.models.Offer
import com.kutoru.jobsearch.models.OfferId

class OfferCardView(context: Context) : CardView(context) {
    private val binding: CardOfferBinding

    init {
        binding = CardOfferBinding.inflate(
            LayoutInflater.from(context), this, false,
        )

        addView(binding.root)

        this.background = AppCompatResources.getDrawable(context, android.R.color.transparent)
    }

    fun setOffer(offer: Offer) {
        when (offer.id) {
            OfferId.NearVacancies -> {
                binding.offerCardIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_dark_blue, null)
                binding.offerCardIcon.setImageResource(R.drawable.icon_location)
                binding.offerCardIcon.visibility = VISIBLE
            }
            OfferId.LevelUpResume -> {
                binding.offerCardIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_dark_green, null)
                binding.offerCardIcon.setImageResource(R.drawable.icon_star)
                binding.offerCardIcon.visibility = VISIBLE
            }
            OfferId.TemporaryJob -> {
                binding.offerCardIcon.background = ResourcesCompat.getDrawable(resources, R.drawable.shape_circle_dark_green, null)
                binding.offerCardIcon.setImageResource(R.drawable.icon_list)
                binding.offerCardIcon.visibility = VISIBLE
            }
            else -> {
                binding.offerCardIcon.visibility = INVISIBLE
            }
        }

        if (offer.button != null) {
            binding.offerCardTitle.maxLines = 2
            binding.offerCardButtonText.text = offer.button.text
            binding.offerCardButtonText.visibility = VISIBLE
        } else {
            binding.offerCardTitle.maxLines = 3
            binding.offerCardButtonText.visibility = GONE
        }

        binding.offerCardTitle.text = offer.title

        binding.root.setOnClickListener {
            val uri = Uri.parse(offer.link)
            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(context, browserIntent, null)
        }
    }
}

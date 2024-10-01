package com.kutoru.jobsearch.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kutoru.jobsearch.JobSearchApplication
import com.kutoru.jobsearch.databinding.FragmentFavoriteBinding
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding

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
        return binding.root
    }
}

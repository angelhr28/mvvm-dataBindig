package com.example.sadanime.modulo.principal.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.sadanime.R
import com.example.sadanime.databinding.FragmentRecentAnimeBinding
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem
import com.example.sadanime.modulo.principal.view.AnimesAdapter
import com.example.sadanime.modulo.principal.viewModel.PrincipalViewModel

class RecentAnimeFragment : Fragment() {

    private lateinit var binding  : FragmentRecentAnimeBinding
    private lateinit var _viewModel: PrincipalViewModel
    private lateinit var mAdapter  : AnimesAdapter
    private lateinit var skeleton  : RecyclerViewSkeletonScreen

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = // FragmentRecentAnimeBinding.inflate(layoutInflater)
            DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recent_anime,
            container,
            false
        )

        mAdapter = AnimesAdapter()

        binding.apply {
            viewmodel = _viewModel
            _viewModel.getListAnimeEstreno()
            Log.d("TAG", "onCreateView: llego al inflateeeeeeeeeeeeeeeeeee")
            rcvLastAnime.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(false)
            }



            _viewModel.isSkeleton.observe(viewLifecycleOwner, {
                Log.e("TAG", "skeleton: $it")

                if (it) skeleton =  Skeleton.bind(binding.rcvLastAnime)
                    .shimmer(true)
                    .angle(20)
                    .color(R.color.colorShimmerSkeleton)
                    .duration(1200)
                    .load(R.layout.skeleton_item_anime)
                    .show()
                else skeleton.hide()

            })

            _viewModel.isEmpty.observe(viewLifecycleOwner,{
                if (it) ""
                else ""
            })

            return rootRecent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProviders.of(this).get(PrincipalViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel = ViewModelProviders.of(this).get(PrincipalViewModel::class.java)
        _viewModel.latestAnimes.observe(viewLifecycleOwner, {
            Log.e("TAG", "adapter: $it" )
            binding.rcvLastAnime.adapter = mAdapter
            mAdapter.setData(it)
        }
        )

    }

    companion object {
        fun newInstance() = RecentAnimeFragment()
    }

}
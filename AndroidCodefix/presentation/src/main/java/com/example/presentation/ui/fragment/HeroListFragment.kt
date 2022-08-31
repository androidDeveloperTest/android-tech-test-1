package com.example.presentation.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.network.HttpException
import com.google.android.material.snackbar.Snackbar
import com.example.presentation.R
import com.example.presentation.ui.adapter.HeroListRecyclerViewAdapter
import com.example.presentation.ui.listener.HeroListRecyclerViewScrollListener
import com.example.presentation.ui.viewmodel.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.data.errors.Error
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private val presenter: HeroListViewModel by viewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HeroListRecyclerViewAdapter
    lateinit var progressBar: ContentLoadingProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.list)
        adapter = recyclerView.adapter as HeroListRecyclerViewAdapter
        progressBar = view.findViewById(R.id.progressBar)


        presenter.heroList.observeForever {
            adapter.add(it)
        }

        presenter.loading.observe(viewLifecycleOwner) {
            if(it){
                showProgressBar()
            }else{
                hideProgressBar()
            }
        }

        presenter.error.observe(viewLifecycleOwner){

            when (it) {
                is Error.HeroNotFound -> {

                    Snackbar.make(
                        view!!,
                        "¡Oops! Héroe no encontrado",
                                Snackbar.LENGTH_SHORT
                    ).show()

                }
                is HttpException -> {

                    Snackbar.make(
                        view!!,
                        "¡Oops! Error de red",
                                Snackbar.LENGTH_SHORT
                    ).show()

                }
                else ->{

                    Snackbar.make(
                        view!!,
                        (it?.stackTrace.toString() ?: ""),
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }
        }

        lifecycleScope.launch {
            presenter.getHeroes()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapter = HeroListRecyclerViewAdapter { heroId ->
            lifecycleScope.launch {
                presenter.getHeroById(heroId)?.let { heroSelected ->
                    findNavController().navigate(
                        R.id.action_heroListFragment_to_heroDetailFragment,
                        bundleOf("heroSelected" to heroSelected)
                    )
                }
            }
        }

        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(HeroListRecyclerViewScrollListener(
            recyclerView.layoutManager as LinearLayoutManager,
            transform = {
                presenter.incrementPage()
                presenter.getHeroes()
            }
        ))

        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    private fun showProgressBar() {
        progressBar.animate()
            .alpha(1f)
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBar.isVisible = true
                }
            })

    }

    private fun hideProgressBar() {
        progressBar.animate()
            .alpha(0f)
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBar.isVisible = false
                }
            })
    }
}
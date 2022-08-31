package com.example.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.presentation.databinding.FragmentHeroDetailBinding
import com.example.presentation.ui.model.toVo
import model.Hero

class HeroDetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hero = (arguments?.get("heroSelected") as? Hero)?.toVo()

        binding.heroName.text = hero!!.name
        binding.heroImage.load(hero!!.thumbnail)
        binding.heroDescription.text = hero!!.description
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
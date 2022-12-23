package com.agrinis.app.ui.article

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.agrinis.app.R
import com.agrinis.app.databinding.FragmentArticleBinding
import com.agrinis.app.ui.source.SourceActivity
import com.agrinis.app.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding : FragmentArticleBinding? = null
    private val binding get() = _binding

    private lateinit var mAdapter: ArticleAdapter
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar?.materialToolbar)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            setupArticleRecycler()

        }
    }

    private fun setupArticleRecycler() {
        viewModel.getTopNews("id").observe(viewLifecycleOwner){
            mAdapter.submitData(lifecycle, it)
        }

        mAdapter = ArticleAdapter()
        binding?.rvNews?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = mAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    mAdapter.retry()
                }
            )
        }
    }


}
package com.agrinis.app.ui.category

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.agrinis.app.R
import com.agrinis.app.databinding.FragmentCategoryBinding
import com.agrinis.app.ui.source.SourceActivity
import com.agrinis.app.ui.source.SourceActivity.Companion.CATEGORY
import com.agrinis.app.ui.source.SourceActivity.Companion.SEARCH_ARTICLE
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var mAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding?.toolbar?.materialToolbar)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            mAdapter = CategoryAdapter { category ->
                Intent(requireContext(), SourceActivity::class.java).also {
                    it.putExtra(CATEGORY, category)
                    startActivity(it)
                }
            }

            binding?.toolbar?.apply {
                etSearch.isFocusable = false
                ilSearch.isFocusable = false
                etSearch.inputType = InputType.TYPE_NULL
                etSearch.setOnClickListener {
                    Intent(requireContext(), SourceActivity::class.java).also { intent ->
                        intent.putExtra(SEARCH_ARTICLE, SEARCH_ARTICLE)
                        startActivity(intent)
                    }
                }
            }


            mAdapter.submitList(viewModel.getCategory())
            binding?.rvCategory?.apply {
                adapter = mAdapter
                setHasFixedSize(true)
            }
        }
    }
}
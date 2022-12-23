package com.agrinis.app.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.agrinis.app.R
import com.agrinis.app.databinding.FragmentCategoryBinding
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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            mAdapter = CategoryAdapter {

            }

            mAdapter.submitList(viewModel.getCategory())
            binding?.rvCategory?.apply {
                adapter = mAdapter
                setHasFixedSize(true)
            }
        }
    }
}
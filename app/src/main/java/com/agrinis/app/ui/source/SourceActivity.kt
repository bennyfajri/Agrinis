package com.agrinis.app.ui.source

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agrinis.app.R
import com.agrinis.app.databinding.ActivitySourceBinding
import com.agrinis.app.repository.source.SourceAdapter
import com.agrinis.app.util.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    companion object {
        const val CATEGORY = "Category"
    }

    private val binding by viewBinding(ActivitySourceBinding::inflate)
    private val viewModel: SourceViewModel by viewModels()
    private lateinit var mSourceAdapter: SourceAdapter

    private lateinit var sheetLayout: ConstraintLayout
    private lateinit var rvSource: RecyclerView
    private lateinit var bsProgress: ProgressBar
    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mSourceAdapter = SourceAdapter {

        }
        val category = intent.getStringExtra(CATEGORY).toString()
        initializeBottomSheet()
        setSourceByCategory(category)
        setupSourceRecycler()
    }

    private fun setupSourceRecycler() {
        rvSource.apply {
            adapter = mSourceAdapter
            layoutManager = LinearLayoutManager(this@SourceActivity)
            setHasFixedSize(true)
        }
    }

    private fun setSourceByCategory(category: String) {
        viewModel.getSource(category).observe(this@SourceActivity) { result ->
            when (result) {
                is com.agrinis.app.network.Result.Loading -> {
                    bsProgress.isVisible = true
                }
                is com.agrinis.app.network.Result.Success -> {
                    bsProgress.isVisible = false
                    mSourceAdapter.submitList(result.data.sources)
                    sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
                is com.agrinis.app.network.Result.Error -> {
                    bsProgress.isVisible = false
                    Toast.makeText(
                        this@SourceActivity, result.error, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initializeBottomSheet() {
        sheetLayout = findViewById(R.id.bs_source)
        sheetBehavior = BottomSheetBehavior.from(sheetLayout)
        rvSource = sheetLayout.findViewById(R.id.rv_sources)
        bsProgress = sheetLayout.findViewById(R.id.bs_progress_bar)

    }
}
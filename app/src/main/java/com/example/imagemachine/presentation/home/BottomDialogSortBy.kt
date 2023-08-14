package com.example.imagemachine.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.imagemachine.databinding.BottomDialogSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomDialogSortBy(
    context: Context,
    val onClick: (SortMode) -> Unit
) : BottomSheetDialog(context) {

    private val binding = BottomDialogSortBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(true)
        setContentView(binding.root)
        binding.apply {
            btnClose.setOnClickListener {
                hide()
            }
            tvSortByName.setOnClickListener {
                onClick.invoke(SortMode.SORT_BY_NAME)
                hide()
            }
            tvSortByType.setOnClickListener {
                onClick.invoke(SortMode.SORT_BY_TYPE)
                hide()
            }
        }
    }
    fun showDialog() {
        show()
    }
}
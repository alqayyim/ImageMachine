package com.example.imagemachine.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.imagemachine.databinding.BottomDialogDeleteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomDialogDelete(
    context: Context,
    val onClick: (Boolean) -> Unit
) : BottomSheetDialog(context) {

    private val binding = BottomDialogDeleteBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCancelable(true)
        setContentView(binding.root)
        binding.apply {
            btnClose.setOnClickListener {
                hide()
            }
            btnYes.setOnClickListener {
                onClick.invoke(true)
                hide()
            }
            btnNo.setOnClickListener {
                onClick.invoke(false)
                hide()
            }
        }
    }
    fun showDialog() {
        show()
    }
}
package com.example.imagemachine.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.core.data.Resource
import com.example.core.navigateTo
import com.example.core.observeData
import com.example.core.toast
import com.example.imagemachine.R
import com.example.imagemachine.databinding.FragmentQrScannerBinding
import com.example.imagemachine.presentation.home.MachineFragmentDirections
import com.example.imagemachine.utils.delegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QRScannerFragment : Fragment(R.layout.fragment_qr_scanner) {

    private val viewModel: QRScannerViewModel by viewModel()
    private val binding by viewBinding(FragmentQrScannerBinding::bind)
    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSaveMachine()
        observeMachineItem()
        codeScanner = CodeScanner(requireContext(), binding.scannerView)
        codeScanner.scanMode = ScanMode.CONTINUOUS
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                viewModel.checkQRCodeResult(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            requireActivity().runOnUiThread {
                toast("Camera initialization error: ${it.message}")
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun observeSaveMachine() {
        observeData(viewModel.checkResultLiveData) { result ->
            result?.let {
                when (it) {
                    is Resource.Error -> toast("${it.error.message}")
                    else -> {}
                }
            }
        }
    }

    private fun observeMachineItem() {
        observeData(viewModel.machineLiveData) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> it.data?.let {
                        navigateTo(MachineFragmentDirections.actionToDetail(it))
                    }
                    is Resource.Error -> toast("${it.error.message}")
                    else -> {}
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}
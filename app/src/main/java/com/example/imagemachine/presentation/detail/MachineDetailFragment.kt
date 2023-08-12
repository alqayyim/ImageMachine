package com.example.imagemachine.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.core.toast
import android.Manifest
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigateTo
import com.example.core.px
import com.example.domain.model.MachineImage
import com.example.imagemachine.R
import com.example.imagemachine.databinding.FragmentMachineDetailBinding
import com.example.imagemachine.presentation.home.MachineViewModel
import com.example.imagemachine.presentation.home.adapter.MachineAdapter
import com.example.imagemachine.utils.HorizontalSpaceItemDecoration
import com.example.imagemachine.utils.VerticalSpaceItemDecoration
import com.example.imagemachine.utils.delegate.viewBinding
import com.permissionx.guolindev.PermissionX
import gun0912.tedimagepicker.builder.TedImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MachineDetailFragment : Fragment(R.layout.fragment_machine_detail) {

    private val binding by viewBinding(FragmentMachineDetailBinding::bind)
    private val viewModel: MachineViewModel by viewModel()
    private val thumbnailAdapter by lazy {
        ThumbnailAdapter(
            onClick = {
                navigateTo(R.id.action_to_detail)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupThumbnailAdapter()
        binding.layoutAddImage.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        toast("All permissions are granted")
                        TedImagePicker.with(requireContext())
                            .startMultiImage { uriList ->
                                Log.d("machinedetail", "uriLilst: $uriList")
                                val machineImages = uriList.map { MachineImage(it) }
                                thumbnailAdapter.submitList(machineImages)
                                // showMultiImage(uriList)
                            }
                    } else {
                        toast("These permissions are denied: $deniedList")
                    }
                }
        }
    }

    private fun setupThumbnailAdapter() {
        binding.rvThumbnail.run {
            addItemDecoration(VerticalSpaceItemDecoration(2.px))
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = thumbnailAdapter
        }
    }
}
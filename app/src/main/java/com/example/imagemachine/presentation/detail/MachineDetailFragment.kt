package com.example.imagemachine.presentation.detail

import android.Manifest
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.Resource
import com.example.core.goneMultipleViews
import com.example.core.navigateBack
import com.example.core.navigateTo
import com.example.core.observeData
import com.example.core.px
import com.example.core.toast
import com.example.core.visibleMultipleViews
import com.example.domain.model.MachineItem
import com.example.imagemachine.R
import com.example.imagemachine.databinding.FragmentMachineDetailBinding
import com.example.imagemachine.utils.VerticalSpaceItemDecoration
import com.example.imagemachine.utils.delegate.viewBinding
import com.permissionx.guolindev.PermissionX
import gun0912.tedimagepicker.builder.TedImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MachineDetailFragment : Fragment(R.layout.fragment_machine_detail) {

    private val binding by viewBinding(FragmentMachineDetailBinding::bind)
    private val viewModel: MachineDetailViewModel by viewModel()
    private val args: MachineDetailFragmentArgs by navArgs()
    private val thumbnailAdapter by lazy {
        ThumbnailAdapter(
            onClick = { navigateTo(R.id.action_to_detail) },
            onDeleteClick = {
                onDeleteClicked(it)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSaveMachine()
        observeListImage()
        observeDeleteMachine()
        setData()
        setupThumbnailAdapter()
        setupAddImageClickListener()
        setupSaveClickListener()
        setOnDateClickListener()
        setOnDeleteClickListener()
    }

    private fun observeListImage() {
        observeData(viewModel.machineImages) { result ->
            result?.let { thumbnailAdapter.submitList(it) }
        }
    }

    private fun observeSaveMachine() {
        observeData(viewModel.saveMachineLiveData) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> toast("Updated!")
                    is Resource.Error -> toast("${it.error.message}")
                    else -> {}
                }
            }
        }
    }

    private fun observeDeleteMachine() {
        observeData(viewModel.deleteMachineLiveData) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        toast("Deleted!")
                        navigateBack()
                    }
                    is Resource.Error -> toast("${it.error.message}")
                    else -> {}
                }
            }
        }
    }


    private fun setData() {
        binding.apply {
            etId.setText(args.argsMachineDetail.id.toString())
            etName.setText(args.argsMachineDetail.machineName)
            etType.setText(args.argsMachineDetail.machineType)
            etCodeNumber.setText(args.argsMachineDetail.machineQRCodeNumber.toString())
            etDate.setText(args.argsMachineDetail.lastMaintainedDate)
        }
    }

    private fun setupThumbnailAdapter() {
        binding.rvThumbnail.run {
            addItemDecoration(VerticalSpaceItemDecoration(2.px))
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = thumbnailAdapter
        }
        args.argsMachineDetail.images?.let {
            if (it.size >= 10) goneMultipleViews(binding.ivAddImage, binding.tvAddImagesLabel)
            viewModel.machineImages.value = it
        }
    }

    private fun setupSaveClickListener() {
        binding.btnSave.setOnClickListener {
            val data = MachineItem(
                id = args.argsMachineDetail.id,
                machineName = binding.etName.text.toString(),
                machineType = binding.etType.text.toString(),
                machineQRCodeNumber = binding.etCodeNumber.text.toString().toInt(),
                lastMaintainedDate = binding.etDate.text.toString(),
                images = viewModel.getImagesList()
            )
            viewModel.saveMachineItem(data)
        }
    }

    private fun onDeleteClicked(uriImage: String) {
        val data = viewModel.getImagesList()?.toMutableList()
        data?.remove(uriImage)
        viewModel.machineImages.value = data
    }

    private fun setupAddImageClickListener() {
        binding.layoutAddImage.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .request { allGranted, _, deniedList ->
                    if (allGranted) {
                        TedImagePicker.with(requireContext())
                            .max(10, "Only 10 maximum allowed ")
                            .startMultiImage { uriList ->
                                val galleryImage = uriList.map { it.toString() }
                                val existingImage = thumbnailAdapter.currentList
                                val mergeImage = galleryImage.plus(existingImage).distinct()
                                val totalSize = mergeImage.size
                                if (totalSize == 10) {
                                    goneMultipleViews(binding.ivAddImage, binding.tvAddImagesLabel)
                                    viewModel.machineImages.value = mergeImage
                                } else if (totalSize < 10) {
                                    visibleMultipleViews(
                                        binding.ivAddImage,
                                        binding.tvAddImagesLabel
                                    )
                                    viewModel.machineImages.value = mergeImage
                                } else toast("You have more than 10 images")
                            }
                    } else {
                        toast("These permissions are denied: $deniedList")
                    }
                }
        }
    }

    private fun setOnDateClickListener() {
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "MMMM dd yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.etDate.setText(sdf.format(cal.time))
        }

        binding.etDate.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setOnDeleteClickListener() {
        binding.btnDelete.setOnClickListener {
            BottomDialogDelete(requireContext(), onClick = {
                if (it) viewModel.deleteMachineItem(args.argsMachineDetail.id)
            }).showDialog()
        }
    }
}
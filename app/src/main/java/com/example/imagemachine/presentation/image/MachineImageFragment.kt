package com.example.imagemachine.presentation.image

import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.core.loadImage
import com.example.imagemachine.R
import com.example.imagemachine.databinding.FragmentMachineImageBinding
import com.example.imagemachine.utils.delegate.viewBinding

class MachineImageFragment : Fragment(R.layout.fragment_machine_image) {

    private val binding by viewBinding(FragmentMachineImageBinding::bind)
    private val args: MachineImageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivMachine.apply {
            ViewCompat.setTransitionName(binding.ivMachine, args.argsMachineImage)
            loadImage(Uri.parse(args.argsMachineImage))
        }
    }
}
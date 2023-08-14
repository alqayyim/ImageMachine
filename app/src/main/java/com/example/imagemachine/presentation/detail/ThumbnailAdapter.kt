package com.example.imagemachine.presentation.detail

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.loadImage
import com.example.imagemachine.R
import com.example.imagemachine.databinding.ItemThumbnailBinding

class ThumbnailAdapter(
    private val onImageClick: (ImageView, String) -> Unit,
    private val onDeleteClick: (String) -> Unit
) :
    ListAdapter<String, ThumbnailAdapter.MachineViewHolder>(MachineDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail, parent, false)
        val binding = ItemThumbnailBinding.bind(view)
        return MachineViewHolder(onImageClick, onDeleteClick, binding)
    }

    override fun onBindViewHolder(holder: MachineViewHolder, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(data)
        }
    }

    override fun submitList(list: List<String?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class MachineViewHolder(
        private val onImageClick: (ImageView, String) -> Unit,
        private val onDeleteClick: (String) -> Unit,
        private val binding: ItemThumbnailBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            binding.apply {
                ivThumbnail.apply {
                    ViewCompat.setTransitionName(ivThumbnail, data)
                    loadImage(Uri.parse(data))
                }
                ivDelete.setOnClickListener {
                    onDeleteClick.invoke(data)
                }
                itemView.setOnClickListener {
                    onImageClick.invoke(ivThumbnail, data)
                }
            }
        }
    }

    object MachineDiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == newItem
    }
}

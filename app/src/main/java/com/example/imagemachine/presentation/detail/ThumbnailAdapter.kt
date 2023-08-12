package com.example.imagemachine.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MachineImage
import com.example.imagemachine.R
import com.example.imagemachine.databinding.ItemThumbnailBinding

class ThumbnailAdapter(private val onClick: (MachineImage) -> Unit) :
    ListAdapter<MachineImage, ThumbnailAdapter.MachineViewHolder>(MachineDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_thumbnail, parent, false)
        val binding = ItemThumbnailBinding.bind(view)
        return MachineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MachineViewHolder, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onClick.invoke(data)
            }
        }
    }

    override fun submitList(list: List<MachineImage?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class MachineViewHolder(private val binding: ItemThumbnailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MachineImage) {
            binding.apply {
                ivThumbnail.setImageURI(data.uri)
            }
        }
    }

    object MachineDiffCallBack : DiffUtil.ItemCallback<MachineImage>() {
        override fun areItemsTheSame(
            oldItem: MachineImage,
            newItem: MachineImage
        ): Boolean =
            oldItem.uri == newItem.uri

        override fun areContentsTheSame(
            oldItem: MachineImage,
            newItem: MachineImage
        ): Boolean =
            oldItem == newItem
    }
}

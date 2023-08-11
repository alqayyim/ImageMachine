package com.example.imagemachine.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MachineItem
import com.example.imagemachine.R
import com.example.imagemachine.databinding.ItemMachineBinding

class MachineAdapter(private val onClick: (MachineItem) -> Unit) :
    ListAdapter<MachineItem, MachineAdapter.MachineViewHolder>(MachineDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_machine, parent, false)
        val binding = ItemMachineBinding.bind(view)
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

    override fun submitList(list: List<MachineItem?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class MachineViewHolder(private val binding: ItemMachineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MachineItem) {
            binding.apply {
                tvMachineNameValue.text = data.machineName
                tvMachineTypeValue.text = data.machineType
            }
        }
    }

    object MachineDiffCallBack : DiffUtil.ItemCallback<MachineItem>() {
        override fun areItemsTheSame(
            oldItem: MachineItem,
            newItem: MachineItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MachineItem,
            newItem: MachineItem
        ): Boolean =
            oldItem == newItem
    }
}

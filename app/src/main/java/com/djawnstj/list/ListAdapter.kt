package com.djawnstj.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.list.databinding.ItemListBinding

class ListAdapter: ListAdapter<User, com.djawnstj.list.ListAdapter.ViewHolder>(diffUtil), ItemTouchHelperListener {

    override fun onItemMove(from: Int, to: Int) {
        val item: User = currentList[from]
        val newList = ArrayList<User>()
        newList.addAll(currentList)
        newList.removeAt(from)
        newList.add(to, item)
        submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.setItem(getItem(position), position)

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: User, position: Int) {
            binding.holderText.text = "${item.id}. ${item.name}(${item.age})"
        }
    }

    companion object {
        private const val TAG = "ListAdapter"
        private val diffUtil = object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
        }
    }


}
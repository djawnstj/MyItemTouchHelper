package com.djawnstj.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.list.databinding.ItemListBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), ItemTouchHelperListener {

    companion object { private const val TAG = "RecyclerViewAdapter" }

    private val list = ArrayList<String>()

    fun submitList(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.setItem(list[position], position)

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: String, position: Int) {
            binding.holderText.text = item
        }

    }

    override fun onItemMove(from: Int, to: Int) {
        val item: String = list[from]
        list.removeAt(from)
        list.add(to, item)
        notifyItemMoved(from, to)
    }

}
package com.example.sadanime.modulo.principal.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadanime.databinding.ItemAnimesBinding
import com.example.sadanime.modulo.principal.model.pojo.AnimesItem

class AnimesAdapter(private val listener: OnCardClickListener): RecyclerView.Adapter<AnimesAdapter.ViewHolder>() {

    private var anime: List<AnimesItem?>? = mutableListOf()

    fun setData(_anime: List<AnimesItem?>?){
        Log.e("TAG", "setData ANTES: $anime" )
        anime = _anime
        Log.e("TAG", "setData DESPUES: $anime" )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position" )
        anime?.get(position)?.let {
            holder.bindView(it, listener)
        }
    }

    override fun getItemCount(): Int = anime?.count() ?: 0

    class ViewHolder(private val binding: ItemAnimesBinding ) : RecyclerView.ViewHolder(binding.root) {
        private val TAG = this.javaClass.name

        fun bindView(data: AnimesItem, listener: OnCardClickListener) {
            binding.apply {
                Log.e(TAG, "bindView: ${data.poster}" )
                binding.anime = data
                binding.executePendingBindings()
            }
        }
    }

    interface OnCardClickListener {
        fun onAnimeSelect(data: AnimesItem)
    }
}

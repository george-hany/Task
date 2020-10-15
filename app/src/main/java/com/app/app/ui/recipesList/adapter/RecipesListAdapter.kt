package com.app.app.ui.recipesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.app.databinding.RecipeItemBinding
import com.app.app.ui.recipesList.model.RecipesItemUIModel
import com.core.base.BaseViewHolder

class RecipesListAdapter(var recipesList: ArrayList<RecipesItemUIModel>) :
    RecyclerView.Adapter<RecipesListAdapter.RecipeItemHolder>() {
    lateinit var clickListener: ClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemHolder {
        return RecipeItemHolder(
            RecipeItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipeItemHolder, position: Int) {
        holder.onBind(position)
        holder.itemView.setOnClickListener { clickListener.onClick(recipesList[position]) }
    }

    inner class RecipeItemHolder(var binding: RecipeItemBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.model = recipesList[position]
        }
    }

    interface ClickListener {
        fun onClick(model: RecipesItemUIModel)
    }
}
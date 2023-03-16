package com.lyra.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lyra.androidmaster.R

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val  tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val vDivider: View = view.findViewById(R.id.vDivider)
    private val cvCategory: CardView = view.findViewById(R.id.cvCategory)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {


        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        val color = when(taskCategory.isSelected) {
            true -> R.color.todo_background_card
            else -> R.color.todo_background_disabled
        }

        cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, color))


        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                vDivider.setBackgroundColor(ContextCompat.getColor(vDivider.context, R.color.todo_business_category))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                vDivider.setBackgroundColor(ContextCompat.getColor(vDivider.context, R.color.todo_other_category))
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                vDivider.setBackgroundColor(ContextCompat.getColor(vDivider.context, R.color.todo_personal_category))
            }
        }

    }
}
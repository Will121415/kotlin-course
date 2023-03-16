package com.lyra.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lyra.androidmaster.R

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var tvTask = view.findViewById<TextView>(R.id.tvTask)
    private var cbTask = view.findViewById<CheckBox>(R.id.cbTask)

    fun render(task: Task, onTaskSelected: (Int) -> Unit) {

        cbTask.setOnClickListener{
            onTaskSelected(layoutPosition)
        }
        itemView.setOnClickListener {
            onTaskSelected(layoutPosition)
        }


        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }




        val color = when (task.category) {
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )

    }
}
package com.example.androidmaster.todo

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var taskName: TextView = view.findViewById(R.id.tvTaskName)
    private var isCompleted: CheckBox = view.findViewById(R.id.cbIsCompleted)
    fun render(task: Task) {

        if (task.isSelected) {
            taskName.paintFlags = taskName.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskName.paintFlags = taskName.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        taskName.text = task.name
        isCompleted.isChecked = task.isSelected

        //set checkbox color with category
        val color = when (task.category) {
            TaskCategory.Personal -> R.color.personal_category
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Others -> R.color.other_category
        }
        isCompleted.buttonTintList = ContextCompat.getColorStateList(taskName.context, color)
    }

}
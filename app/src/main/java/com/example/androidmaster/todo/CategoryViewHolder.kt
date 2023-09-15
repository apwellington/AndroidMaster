package com.example.androidmaster.todo

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName = view.findViewById<TextView>(R.id.tvCategoryName)
    private val divider = view.findViewById<View>(R.id.divider)
    private val viewCategory = view.findViewById<CardView>(R.id.viewCategory)
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if (taskCategory.isSelected) {
            R.color.background_card
        }else {
            R.color.background_selected
        }

        viewCategory.setCardBackgroundColor(ContextCompat.getColor(viewCategory.context, color))

        itemView.setOnClickListener {
            onItemSelected(layoutPosition)
        }
            when(taskCategory) {
                TaskCategory.Personal -> {
                    tvCategoryName.text = "Personal"
                    divider.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.personal_category))
                }
                TaskCategory.Business -> {
                    tvCategoryName.text = "Business"
                    divider.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.business_category))
                }
                TaskCategory.Others -> {
                    tvCategoryName.text = "Others"
                    divider.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.other_category))
                }
            }

    }

}
package com.example.androidmaster.todo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.todo.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var rvTask: RecyclerView

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var taskAdapter: TaskAdapter
    //fabAddTask
    private lateinit var fabAddTask: FloatingActionButton

    private val categories = listOf(
        Personal,
        Business,
        Others

    )

    private val taskList = mutableListOf(
        Task("Study Android", category = Personal),
        Task("Study Kotlin", category = Others),
        Task("Study Java", category = Personal),
        Task("Study Flutter", category = Business),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListener()

    }

    private fun initListener() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_task)
        //initialize components
        val etTaskName = dialog.findViewById<EditText>(R.id.etAddTaskName)
        val btnAddTask = dialog.findViewById<Button>(R.id.btnAddTask)
        val radioButtonGroup = dialog.findViewById<RadioGroup>(R.id.rgCategory)

        btnAddTask.setOnClickListener {
            val taskName = etTaskName.text.toString()
            if (taskName.isEmpty()) {
                etTaskName.error = "Task name cannot be empty"
                return@setOnClickListener
            }
            val selectedIdCategory = radioButtonGroup.checkedRadioButtonId
            val selectedButtonCategory:RadioButton = radioButtonGroup.findViewById(selectedIdCategory)
            val category:TaskCategory = when(selectedButtonCategory.text) {
                getString(R.string.personal) -> Personal
                getString(R.string.business) -> Business
                else -> Others
            }

            val task = Task(taskName, category)
            taskList.add(task)
            updateTaskList()
            dialog.hide()
        }
        dialog.show()
    }

    private fun updateTaskList() {
        val selectedCategory = categories.filter { it.isSelected }
        val newTasks = taskList.filter { selectedCategory.contains(it.category) }
        taskAdapter.tasks = newTasks
        taskAdapter.notifyDataSetChanged()
    }

    private fun onItemSelected(position:Int){
        taskList[position].isSelected = !taskList[position].isSelected
        updateTaskList()
    }

    private fun initComponent() {
        rvCategory = findViewById(R.id.rvCategory)
        rvTask = findViewById(R.id.rvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        //create adapter
        categoryAdapter = CategoryAdapter(categories) {updateCategoryList(it)}
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = categoryAdapter


        taskAdapter = TaskAdapter(taskList) { onItemSelected(it) }
        rvTask.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTask.adapter = taskAdapter
    }

    private fun updateCategoryList(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoryAdapter.notifyItemChanged(position)
        updateTaskList()
    }


}
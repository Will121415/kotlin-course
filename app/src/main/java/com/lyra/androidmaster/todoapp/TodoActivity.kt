package com.lyra.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lyra.androidmaster.R
import com.lyra.androidmaster.todoapp.TaskCategory.*

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf(
        Task("personal", Personal),
        Task("business", Business),
        Task("other", Other)
    )

    private var filteredTasks = tasks


    private lateinit var rvCategory: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var rvTask: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        rvCategory = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initListeners() {

        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            addNewTask(etTask, rgCategories, dialog)
        }

        dialog.show()
    }

    private fun addNewTask(etTask: EditText, rgCategories: RadioGroup, dialog: Dialog) {

        val newTask = etTask.text.toString()

        if (newTask.isNotEmpty()) {

            val selectedId = rgCategories.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)

            val selectedCategory: TaskCategory = when (selectedRadioButton.text) {

                getString(R.string.todo_dialog_category_business) -> Business
                getString(R.string.todo_dialog_category_personal) -> Personal
                else -> Other
            }

            tasks.add(Task(newTask, selectedCategory))
            updateTasks()
            dialog.hide()
        }
    }

    private fun initUI() {
        categoryAdapter = CategoryAdapter(categories) { position -> updateCategories(position) }
        rvCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategory.adapter = categoryAdapter

        taskAdapter = TaskAdapter(tasks) { position -> onItemSelected(position) }
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = taskAdapter
    }

    private fun onItemSelected(position:Int){
        filteredTasks[position].isSelected = !filteredTasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position: Int)
    {
        categories[position].isSelected = !categories[position].isSelected
        categoryAdapter.notifyItemChanged(position)
        updateTasks()

    }

    private fun updateTasks() {
        val selectedCategories: List<TaskCategory> = categories.filter { !it.isSelected }

        filteredTasks = if (selectedCategories.isEmpty()) {
            tasks
        } else {
            tasks.filter { selectedCategories.contains(it.category) }.toMutableList()
        }

        taskAdapter.tasks = filteredTasks
        taskAdapter.notifyDataSetChanged()
    }

}
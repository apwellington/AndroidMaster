package com.example.androidmaster.todo

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Others : TaskCategory()
}

//Investigar mejor las sealed classes


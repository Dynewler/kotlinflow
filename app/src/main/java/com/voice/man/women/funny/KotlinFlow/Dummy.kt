package com.flow.kotlin.project

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class Dummy {
    fun getNotes(): Flow<Note> {
        val list = listOf(

            Note(1, true, "First", "First Description "),
            Note(2, true, "Second", "Second Description "),
            Note(3, false, "third", "First Description "),
            Note(4, true, "Fourth", "third Description "),
            Note(5, false, "fifth", "fifth Description ")
        )
        return list.asFlow()
    }
}

data class Note(val id: Int, val isActive: Boolean, val title: String, val description: String)
data class FormattedNote(
    val id: Int,
    val isActive: Boolean,
    val title: String,
    val description: String
)
package com.flow.kotlin.project

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NonTerminalOperatorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_terminal_operators)

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                //.map() used to covert data into another form e.g used to map one object to other object

                .map {
                    it * 2
                }
                .filter {
                    it < 8
                }
                .collect{
                    Log.d("TAGNonTerminal", "Output: ${it.toString()}")
                }

        }
    }
    fun producer()= flow<Int>{
        val list =listOf(1,2,3,4,5)
        list.forEach{
            delay(1000)
            emit(it)
        }

    }
}
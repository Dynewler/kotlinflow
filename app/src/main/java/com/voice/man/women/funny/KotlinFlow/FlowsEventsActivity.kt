package com.flow.kotlin.project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FlowsEventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
        producer()
            .onStart {
                //manually emit value
                emit(-1)
                Log.d("TAG", "Starting Out")
            }
            .onCompletion {
                //manually emit value
                emit(6)
                Log.d("TAG", "Completed")

            }
            .onEach {
                Log.d("TAG", "About to emit-$it")

            }
            .collect{
                Log.d("TAG", "about to emit: ${it.toString()} ")
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
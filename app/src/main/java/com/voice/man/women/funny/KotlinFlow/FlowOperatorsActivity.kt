package com.flow.kotlin.project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FlowOperatorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_operators)
        GlobalScope.launch(Dispatchers.Main) {
            //all the terminal operators are suspend functions

             //.first() wil give the first value

            //val result =producer().first()

            //.toList()convert into list
            val result =producer().toList()

            Log.d("TAG", "about to emit: ${result.toString()} ")

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
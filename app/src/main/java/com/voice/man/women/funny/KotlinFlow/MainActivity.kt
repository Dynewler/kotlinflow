package com.flow.kotlin.project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    lateinit var dummy:Dummy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dummy = Dummy()
      GlobalScope.launch (Dispatchers.Main){
           dummy.getNotes()
               .map {
               FormattedNote(it.id,it.isActive,it.title.uppercase(),it.description)
           }
               .filter {
                   it.isActive
               }
               .collect{
                   Log.d("NoteTag", "onCreate: ${it.toString()}")
               }

        }
        //using buffer
        GlobalScope.launch (Dispatchers.Main) {
            val time = measureTimeMillis {
                producer()
                    .buffer(3)
                    .collect {
                        delay(1500)
                        Log.d("TAGBuffer", "onCreate: ${it.toString()}")
                    }
            }
            Log.d("TAGBuffer", "onCreate: ${time.toString()}")

        }
        }

/*        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }*/

        //for multiple consumers
       /* GlobalScope.launch {
            val data:Flow<Int> =producer()
            delay(2500)
            data.collect{
                Log.d("TAG", "consumer 2: ${it.toString()}")
            }
        }*/



    }
    fun producer()= flow<Int>{
        val list =listOf(1,2,3,4,5,6,7,8,9,10)
        list.forEach{
            delay(1000)
            emit(it)
        }
    }

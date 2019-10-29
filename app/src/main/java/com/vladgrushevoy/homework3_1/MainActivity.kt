package com.vladgrushevoy.homework3_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = this.findViewById(R.id.rv_animal)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(generateFakeValues())
    }

    private  fun generateFakeValues():List<String>{
        val values = mutableListOf<String>()
        for(i in 0 .. 25){
            val rnd : Int = Random.nextInt(0,6)
            when (rnd) {
                1 -> values.add("Cat")
                2 -> values.add("Dog")
                3 -> values.add("Cow")
                4 -> values.add("Chupakabra")
                5 -> values.add("Secret animal")
                else -> values.add("Undefined animal")
            }
        }
        return values
    }
}

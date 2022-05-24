package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val bundle:Bundle? = intent.extras
        val key = bundle?.get("key")
        val key2 = bundle?.get("key2")

//      Toast.makeText(applicationContext,key.toString(),Toast.LENGTH_LONG).show()
        val result:TextView=findViewById(R.id.Result)
        val result2:TextView=findViewById(R.id.Result2)
        result.text=key.toString()
        result2.text=key2.toString()



    }

    
}

package com.example.a7marchtictacgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    lateinit var multiplayer: Button
    lateinit var computer: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        multiplayer=findViewById(R.id.multiplayer)
        computer=findViewById(R.id.computer)
        computer.background=resources.getDrawable(R.drawable.gradientu)
        multiplayer.background=resources.getDrawable(R.drawable.gradientu)
        multiplayer.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        computer.setOnClickListener{
            val intent= Intent(this,computerizedGame::class.java)
            startActivity(intent)
        }
    }
}
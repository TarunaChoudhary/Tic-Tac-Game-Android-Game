package com.example.a7marchtictacgame

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.ArrayList
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button
    lateinit var btn6:Button
    lateinit var btn7:Button
    lateinit var btn8:Button
    lateinit var btn9:Button
    lateinit var restart_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Tic Tac Toe"
        restart_btn=findViewById(R.id.restart_btn)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)
        btn6=findViewById(R.id.btn6)
        btn7=findViewById(R.id.btn7)
        btn8=findViewById(R.id.btn8)
        btn9=findViewById(R.id.btn9)


        restart_btn.visibility = View.INVISIBLE
        btn1.setOnClickListener {

            selectedButton(btn1)
        }
        btn2.setOnClickListener {
            selectedButton(btn2)
        }
        btn3.setOnClickListener {
            selectedButton(btn3)
        }
        btn4.setOnClickListener {
            selectedButton(btn4)
        }
        btn5.setOnClickListener {
            selectedButton(btn5)
        }
        btn6.setOnClickListener {
            selectedButton(btn6)
        }
        btn7.setOnClickListener {
            selectedButton(btn7)
        }
        btn8.setOnClickListener {
            selectedButton(btn8)
        }
        btn9.setOnClickListener {
            selectedButton(btn9)
        }
        restart_btn.setOnClickListener {
            restart()

        }
    }

    fun selectedButton(view:View){
        val selectedBtn = view as Button
        var  btnNum = 0
        when(selectedBtn.id){
            R.id.btn1 -> btnNum = 1
            R.id.btn2 -> btnNum = 2
            R.id.btn3 -> btnNum = 3
            R.id.btn4 -> btnNum = 4
            R.id.btn5 -> btnNum = 5
            R.id.btn6 -> btnNum = 6
            R.id.btn7 -> btnNum = 7
            R.id.btn8 -> btnNum = 8
            R.id.btn9 -> btnNum = 9
        }

        game(btnNum,selectedBtn)
    }
    var player = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun game(cell:Int,selectedBtn:Button){
        restart_btn.visibility = View.INVISIBLE
        val audio = MediaPlayer.create(this , R.raw.clickaudio)
        if (player == 1){
            audio.start()
            Handler().postDelayed(Runnable { audio.release() } , 200)
            selectedBtn.text = "O"
            selectedBtn.background=resources.getDrawable(R.drawable.gradient)
            player1.add(cell)
            player =4
        }else{
            audio.start()
            selectedBtn.text = "X"
            //selectedBtn.background=resources.getDrawable(R.drawable.gradient)
            player = 1
            player2.add(cell)
        }
        selectedBtn.isEnabled = false
        neutral()
        //row1
        if (player1.contains(1)&& player1.contains(2) && player1.contains(3)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //clmn1
        if (player1.contains(1)&& player1.contains(4) && player1.contains(7)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //clmn2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //clmn3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //
        //
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        //
        //
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            Toast.makeText(this,"Player1 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            Toast.makeText(this,"Player2 Won",Toast.LENGTH_SHORT).show()
            disableButton()
        }

    }
    fun restart(){
        player1.clear()
        player2.clear()

        btn1.background=resources.getDrawable(R.drawable.center)
        btn2.background=resources.getDrawable(R.drawable.center)
        btn3.background=resources.getDrawable(R.drawable.center)
        btn4.background=resources.getDrawable(R.drawable.center)
        btn5.background=resources.getDrawable(R.drawable.center)
        btn6.background=resources.getDrawable(R.drawable.center)
        btn7.background=resources.getDrawable(R.drawable.center)
        btn8.background=resources.getDrawable(R.drawable.center)
        btn9.background=resources.getDrawable(R.drawable.center)
        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
        btn5.isEnabled = true
        btn6.isEnabled = true
        btn7.isEnabled = true
        btn8.isEnabled = true
        btn9.isEnabled = true
    }

    fun neutral(){
        if (player1.size ==4 && player2.size ==4 ){
            disableButton()
        }else if (player1.size >4 || player2.size >4){

        }
    }

    fun disableButton(){
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false
        restart_btn.visibility = View.VISIBLE
    }
}
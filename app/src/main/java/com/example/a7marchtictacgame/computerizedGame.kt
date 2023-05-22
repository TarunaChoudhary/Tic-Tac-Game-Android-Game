package com.example.a7marchtictacgame

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

class computerizedGame : AppCompatActivity() {
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button
    lateinit var btn6:Button
    lateinit var btn7:Button
    lateinit var btn8:Button
    lateinit var btn9:Button
    lateinit var btn10:Button
    lateinit var textView:TextView
    var playerTurn:Boolean=true
    val singleUser=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computerized_game)

        textView=findViewById(R.id.textview1)

        btn10=findViewById(R.id.restart_btn)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)
        btn6=findViewById(R.id.btn6)
        btn7=findViewById(R.id.btn7)
        btn8=findViewById(R.id.btn8)
        btn9=findViewById(R.id.btn9)


        btn10.visibility = View.INVISIBLE
        btn1.setOnClickListener {

            selectedButton1(btn1)
        }
        btn2.setOnClickListener {
            selectedButton1(btn2)
        }
        btn3.setOnClickListener {
            selectedButton1(btn3)
        }
        btn4.setOnClickListener {
            selectedButton1(btn4)
        }
        btn5.setOnClickListener {
            selectedButton1(btn5)
        }
        btn6.setOnClickListener {
            selectedButton1(btn6)
        }
        btn7.setOnClickListener {
            selectedButton1(btn7)
        }
        btn8.setOnClickListener {
            selectedButton1(btn8)
        }
        btn9.setOnClickListener {
            selectedButton1(btn9)
        }

        btn10.setOnClickListener {
            reset()

        }
    }

    // player winning count
    var player1Count = 0
    var player2Count = 0

    // this function handle the click event on the board.
    fun selectedButton1(view: View)
    {
        if(playerTurn) {
            val but = view as Button
            var cellID = 0
            when (but.id) {
                R.id.btn1 -> cellID = 1
                R.id.btn2 -> cellID = 2
                R.id.btn3 -> cellID = 3
                R.id.btn4 -> cellID = 4
                R.id.btn5 -> cellID = 5
                R.id.btn6 -> cellID = 6
                R.id.btn7 -> cellID = 7
                R.id.btn8 -> cellID = 8
                R.id.btn9 -> cellID = 9
            }
            playerTurn = false;
            Handler().postDelayed(Runnable { playerTurn = true } , 600)
            playnow(but, cellID)
        }
    }
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1

    // this function update the game board after every move.
    fun playnow(buttonSelected:Button , currCell:Int)
    {   val audio = MediaPlayer.create(this , R.raw.clickaudio)
        if(activeUser == 1)
        {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)
            audio.start()
            buttonSelected.isEnabled = false
            Handler().postDelayed(Runnable { audio.release() } , 200)
            val checkWinner = checkwinner()
            if(checkWinner == 1){
                Handler().postDelayed(Runnable { reset() } , 2000)
            }
            else if(singleUser){
                Handler().postDelayed(Runnable { robot() } , 500)
            }
            else
                activeUser = 2
        }
        else
        {
            buttonSelected.text = "O"
            audio.start()
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            activeUser = 1
            player2.add(currCell)
            emptyCells.add(currCell)
            Handler().postDelayed(Runnable { audio.release() } , 200)
            buttonSelected.isEnabled = false
            val checkWinner  = checkwinner()
            if(checkWinner == 1)
                Handler().postDelayed(Runnable { reset() } , 4000)
        }
    }

    // this function resets the game.
    fun reset()
    {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1;
        for(i in 1..9)
        {
            var buttonselected : Button?
            buttonselected = when(i){
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {btn1}
            }
            buttonselected.isEnabled = true
            buttonselected.text = ""
            textView.text = "Wins : $player1Count"
            //textView2.text = "Player2 : $player2Count"
        }
    }

    // this function disable all the button on the board for a while.
    fun disableReset()
    {
        btn10.isEnabled = false
        Handler().postDelayed(Runnable { btn10.isEnabled = true } , 2200)
    }

    // This function automatically make a move at a random position.
    fun robot()
    {
        val rnd = (1..9).random()
        if(emptyCells.contains(rnd))
            robot()
        else {
            val buttonselected : Button?
            buttonselected = when(rnd) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {btn1}
            }
            emptyCells.add(rnd);
            // move audio
            val audio = MediaPlayer.create(this , R.raw.clickaudio)
            audio.start()
            Handler().postDelayed(Runnable { audio.release() } , 500)
            buttonselected.text = "O"
            buttonselected.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(rnd)
            buttonselected.isEnabled = false
            var checkWinner = checkwinner()
            if(checkWinner == 1)
                Handler().postDelayed(Runnable { reset() } , 2000)
        }
    }

    fun checkwinner(): Int {
        val audio = MediaPlayer.create(this, R.raw.clickaudio)
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) || (player1.contains(
                1
            ) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) || (player1.contains(
                7
            ) && player1.contains(8) && player1.contains(9)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) || (player1.contains(
                1
            ) && player1.contains(5) && player1.contains(9)) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7) || (player1.contains(2) && player1.contains(
                5
            ) && player1.contains(8))
        ) {
            player1Count += 1
            buttonDisable()
            audio.start()
            disableReset()
            Handler().postDelayed(Runnable { audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("You have won the game.."+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                audio.release()
                exitProcess(1)

            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
            return 1


        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) || (player2.contains(
                1
            ) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(
                7
            ) && player2.contains(8) && player2.contains(9)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) || (player2.contains(
                1
            ) && player2.contains(5) && player2.contains(9)) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) || (player2.contains(2) && player2.contains(
                5
            ) && player2.contains(8))
        ) {
            player2Count += 1
            audio.start()
            buttonDisable()
            disableReset()
            Handler().postDelayed(Runnable { audio.release() }, 4000)
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Opponent have won the game"+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()
                audio.release()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                audio.release()
                exitProcess(1)
            }
            Handler().postDelayed(Runnable { build.show() }, 2000)
            return 1
        } else if (emptyCells.contains(1) && emptyCells.contains(2) && emptyCells.contains(3) && emptyCells.contains(
                4
            ) && emptyCells.contains(5) && emptyCells.contains(6) && emptyCells.contains(7) &&
            emptyCells.contains(8) && emptyCells.contains(9)
        ) {

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { dialog, which ->
                reset()
            }
            build.setNegativeButton("Exit") { dialog, which ->
                exitProcess(1)
            }
            build.show()
            return 1

        }
        return 0
    }

    fun buttonDisable() {
        for (i in 1..9) {
            val buttonSelected = when (i) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                    btn1
                }

            }
            if (buttonSelected.isEnabled == true)
                buttonSelected.isEnabled = false
        }
    }


}
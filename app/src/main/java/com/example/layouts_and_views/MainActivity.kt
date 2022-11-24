/*
Assignment5- Events
Rojan Baral
A00233565
 */
package com.example.layouts_and_views
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    var viewCamScore:Int = 0
    var viewSenScore:Int = 0

    @SuppressLint("ResourceType", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create array of basketball points
        val score = arrayOf("0","1", "2", "3")
        var cambrianSpinner = findViewById<Spinner>(R.id.result1)
        val senecaSpinner = findViewById<Spinner>(R.id.result2)
        var arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, score
        )
        cambrianSpinner.adapter = arrayAdapter
        senecaSpinner.adapter = arrayAdapter

        var cambrianScore = findViewById<TextView>(R.id.score1)
        var senecaScore = findViewById<TextView>(R.id.score2)


        //Increase score if cambrian score(+) is clicked
        var camBtnIncrease = findViewById<Button>(R.id.btn1_increase)
        camBtnIncrease.setOnClickListener {
            viewCamScore++
            cambrianScore.setText("$viewCamScore")

        }

        //Decrease score if cambrian score(-) is clicked
        var camBtnDecrease = findViewById<Button>(R.id.btn1_decrease)
        camBtnDecrease.setOnClickListener {
            viewCamScore--
            if(viewCamScore<0){
                //If score is less than zero set it to 0
                cambrianScore.setText("0")
                viewCamScore = 0
            }else{
                cambrianScore.setText("$viewCamScore")
            }
        }

        //Increase score if seneca score(+) is clicked
        var senBtnIncrease = findViewById<Button>(R.id.btn2_increase)
        senBtnIncrease.setOnClickListener {
            viewSenScore++
            senecaScore.setText("$viewSenScore")
        }

        //Decrease score if seneca score(-) is clicked
        var senBtnDecrease = findViewById<Button>(R.id.btn2_decrease)
        senBtnDecrease.setOnClickListener {
            viewSenScore--
            if(viewSenScore<0){
                //If score is less than zero set it to 0
                senecaScore.setText("0")
                viewSenScore = 0
            }else{
                senecaScore.setText("$viewSenScore")
            }

        }




            }
//Menu display
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.about ->{
                val myToast = Toast.makeText(this,"Developed by Rojan Baral, " +
                        "JAV-1001, " +
                        "Cambrian college, Sudbury",Toast.LENGTH_LONG)
                myToast.setGravity(Gravity.LEFT,300,300)
                myToast.show()
                return true
            }else -> super.onOptionsItemSelected(item)
        }
    }
       }





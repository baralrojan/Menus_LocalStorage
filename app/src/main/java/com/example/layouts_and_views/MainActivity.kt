/*
Assignment5- Events
Rojan Baral
A00233565
 */
package com.example.layouts_and_views
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.layouts_and_views.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var cambrianScore = ""
    private var senecaScore = ""
    private var viewCamScore:Int = 0
    private var viewSenScore:Int = 0
    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        Log.i("SaveScoreValue",sharedPrefs.getBoolean("prefs_save_score",false).toString())

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
            if(cambrianScore!= null){
                var newscore = cambrianScore.getText().toString()
                var cambrianScoreToInt = Integer.parseInt(newscore)
                viewCamScore = cambrianScoreToInt
            }
            viewCamScore++
            cambrianScore.setText("$viewCamScore")

        }

        //Decrease score if cambrian score(-) is clicked
        var camBtnDecrease = findViewById<Button>(R.id.btn1_decrease)
        camBtnDecrease.setOnClickListener {
            if(cambrianScore!= null){
                var newscore = cambrianScore.getText().toString()
                var cambrianScoreToInt = Integer.parseInt(newscore)
                viewCamScore = cambrianScoreToInt
            }
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
            if(senecaScore!= null){
                var newscore = senecaScore.getText().toString()
                var senecaScoreToInt = Integer.parseInt(newscore)
                viewSenScore = senecaScoreToInt
            }
            viewSenScore++
            senecaScore.setText("$viewSenScore")
        }

        //Decrease score if seneca score(-) is clicked
        var senBtnDecrease = findViewById<Button>(R.id.btn2_decrease)
        senBtnDecrease.setOnClickListener {
            if(senecaScore!= null){
                var newscore = senecaScore.getText().toString()
                var senecaScoreToInt = Integer.parseInt(newscore)
                viewSenScore = senecaScoreToInt
            }
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

    override fun onResume() {
        super.onResume()

        cambrianScore = sharedPrefs.getString("prefs_cambrian_score",cambrianScore).toString()
        senecaScore = sharedPrefs.getString("prefs_seneca_score",senecaScore).toString()
        binding.score1.text = cambrianScore
        binding.score2.text = senecaScore
        binding.result1.setSelection(sharedPrefs.getInt("prefs_cambrianScore_spinner",0))
        binding.result2.setSelection(sharedPrefs.getInt("prefs_senecaScore_spinner",0))
    }




    override fun onPause() {

        val editor = sharedPrefs.edit()
        if (sharedPrefs.getBoolean("prefs_save_score", false)) {
            editor.putString("prefs_cambrian_score", binding.score1.text.toString())
            editor.putString("prefs_seneca_score", binding.score2.text.toString())
            editor.putInt("prefs_cambrianScore_spinner",binding.result1.selectedItemPosition)
            editor.putInt("prefs_senecaScore_spinner",binding.result2.selectedItemPosition)
        }
        else {
            editor.clear()
            editor.putBoolean("prefs_save_values", false)
        }
        editor.apply()

        super.onPause()
    }
//Menu display
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
            R.id.about ->{
                val myToast = Toast.makeText(this,"Developed by Rojan Baral, " +
                        "JAV-1001, " +
                        "Cambrian college, Sudbury",Toast.LENGTH_LONG)
                myToast.setGravity(Gravity.LEFT,300,300)
                myToast.show()
            }
            R.id.setting ->{
                val intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)

            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }




}





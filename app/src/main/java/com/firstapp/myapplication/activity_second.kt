package com.firstapp.myapplication
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_second.*
import kotlin.math.floor

var arr = emptyArray<String>()
fun my_print_ln(arg: Int, arg2: Int){
    println(arg)
    println(arg2)
}

class activity_second : AppCompatActivity() {
    var pos_x = 0
    var pos_y = 0
    var coord_x = 0
    var coord_y = 0
    var usr = Login().user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        myLayout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val x = event.getX()
                        val y = event.getY()
                        //if (x in 199.0..1000.0 && y >= 153 && y <= 889){
                            pos_x = x.toInt()
                            pos_y = y.toInt()
                            my_print_ln(pos_x - 538, pos_y + 4 - 538)
                            coord_x = ((pos_x - 538) / 50.0).toInt()
                            coord_y = ((pos_y + 4 - 538) / 40.0).toInt() * -1
                            coord_y.toInt()
                            //my_print_ln(pos_x, pos_y)
                            my_print_ln(coord_x, coord_y)
                        //}
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

    }
    fun reditect_main(view: View) {
        val actv = Intent(this, MenuActivity::class.java)
        startActivity(actv)
    }


    fun redirect_mood(view: View) {
        val acvt = Intent(this, activity_second::class.java)

        startActivity(acvt)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun get_txt(view: View){
        val user_id = usr!!.uid
        val txt = new_id.text.toString()
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        println(formatted)

        arr += txt
        for (elem in arr){
            System.out.println(elem)
        }
        val database = FirebaseDatabase.getInstance().reference
        database.child(user_id).setValue(Rec(txt, formatted, usr!!.email.toString()))
    }
    fun redirect_login(view: View) {
        val acvt = Intent(this, Login::class.java)

        startActivity(acvt)

    }



}


// Твой репозиторий
//  |
// \_/
// Что такое статистика настроения?
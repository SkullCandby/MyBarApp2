package com.firstapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firstapp.myapplication.activity_second
import com.firstapp.myapplication.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }


    fun reditect_main(view: View) {
        val actv = Intent(this, MenuActivity::class.java)
        startActivity(actv)
    }

    fun redirect_mood(view: View) {
        val acvt = Intent(this, activity_second::class.java)
        startActivity(acvt)
    }

    fun redirect_login(view: View) {
        val acvt = Intent(this, Login::class.java)
        startActivity(acvt)
    }


}
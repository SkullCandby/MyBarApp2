package com.firstapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class WaitForConfirm : AppCompatActivity() {
    var user = Login().user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseAuth.getInstance().signOut()
}
    fun is_varif(view: View){
        if (user!!.isEmailVerified){
            val actv = Intent(this, MenuActivity::class.java)
            startActivity(actv)
        }

    }
}
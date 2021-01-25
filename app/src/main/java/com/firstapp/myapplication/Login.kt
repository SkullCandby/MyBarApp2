package com.firstapp.myapplication


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class Login : AppCompatActivity() {
    val MY_REQUEST_CODE: Int = 7117
    lateinit var providers : List<AuthUI.IdpConfig>
    var user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        providers = Arrays.asList<AuthUI.IdpConfig>(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build(),
        )


        button4.setOnClickListener{
            AuthUI.getInstance().signOut(this@Login)
                .addOnCompleteListener {
                    button4.isEnabled=false
                    showSingInOptions()
                }
                .addOnFailureListener { e -> Toast.makeText(this@Login, e.message, Toast.LENGTH_SHORT).show()
                }
        }



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser
                textView.setText(user!!.displayName)
                textView3.setText(user.email)
                button4.isEnabled = true
                println("ТУТ")
                user.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                println("SUCCESS")
                            }
                        }
                if (user.isEmailVerified())
                {
                    // user is verified, so you can finish this activity or send user to activity which you want.
                    Toast.makeText(this@Login, "Successfully logged in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // email is not verified, so just prompt the message to the user and restart this activity.
                    // NOTE: don't forget to log out the user.
                    val actv = Intent(this, WaitForConfirm::class.java)
                    startActivity(actv)

                    //restart this activity

                }
            }
            else{
                Toast.makeText(this, "" + response!!.error!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkIfEmailVerified() {
        println("YA BIL TUT")
        val user = FirebaseAuth.getInstance().currentUser
        if (user!!.isEmailVerified) {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Toast.makeText(this@Login, "Successfully logged in", Toast.LENGTH_SHORT).show()
        } else {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            val acvt = Intent(this, WaitForConfirm::class.java)
            startActivity(acvt)
            //restart this activity
        }
    }
    private fun showSingInOptions() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers).setTheme(R.style.MyTheme).build(), MY_REQUEST_CODE)
    }
    fun reditect_main(view: View) {
        val actv = Intent(this, MenuActivity::class.java)
        startActivity(actv)
    }

    fun redirect_mood(view: View) {
        val acvt = Intent(this, activity_second::class.java)
        startActivity(acvt)


    }

    fun choose(view: View) {
        showSingInOptions()
    }
}
package com.firstapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.firstapp.myapplication.activity_second
import com.firstapp.myapplication.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MenuActivity : AppCompatActivity() {
    val reference = FirebaseDatabase.getInstance().getReference("Xzen61WXylX6VRKSkdNkgr9OAXx2") // Заменить этот ключ на универсальный для каждого пользователя
    var data_list = emptyList<Rec>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
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

        fun getData() {
            reference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    println("Cancel")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val list = ArrayList<Rec>()
                    for (data in p0.children) {
                        try {
                            val model = data.getValue(Rec::class.java)
                            list.add(model as Rec)
                        } catch (e: DatabaseException) {
                            println(data.getValue(Rec::class.java))
                            println("ТУТ")
                        }
                    }
                    var btn_lst = ArrayList<Button>()
                    for (i in list.indices) {
                        val button_dynamic = Button(this@MenuActivity)
                        val row = LinearLayout(this@MenuActivity)
                        row.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        button_dynamic.text = list[i].time
                        button_dynamic.y = (i * 150).toFloat()
                        button_dynamic.setOnClickListener {
                            Dialog_Actv(list[i].user_text).show(supportFragmentManager, "MyCustomFragment")

                        }
                        row.addView(button_dynamic)
                        mainLayout.addView(row)
                        btn_lst.add(button_dynamic)

                    }
                    println(btn_lst.size)
                    for (i in btn_lst.indices){
                        println(btn_lst[i].id)
                    }
                }
                })

        }


    }



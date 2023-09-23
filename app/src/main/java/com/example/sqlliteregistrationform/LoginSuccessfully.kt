package com.example.sqlliteregistrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlliteregistrationform.databinding.ActivityLoginSuccessfullyBinding

class LoginSuccessfully : AppCompatActivity() {
    private lateinit var binder:ActivityLoginSuccessfullyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= ActivityLoginSuccessfullyBinding.inflate(layoutInflater)
        setContentView(binder.root)
        val username= intent.getStringExtra("username")
        binder.username.setText(username)

binder.delete.setOnClickListener {
    val intent = Intent(this,MainActivity::class.java)
    startActivity(intent)
}

    }
}

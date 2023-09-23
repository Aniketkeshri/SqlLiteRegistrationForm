package com.example.sqlliteregistrationform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlliteregistrationform.databinding.ActivityRegistrationSuccessfullyBinding

class RegistrationSuccessfully : AppCompatActivity() {
    private lateinit var binder:ActivityRegistrationSuccessfullyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= ActivityRegistrationSuccessfullyBinding.inflate(layoutInflater)
        setContentView(binder.root)


        binder.button.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
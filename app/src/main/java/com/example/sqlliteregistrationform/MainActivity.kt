package com.example.sqlliteregistrationform

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlliteregistrationform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        db = DatabaseHelper(applicationContext)

        binder.btnlogin.setOnClickListener {
            login()
            validate()
        }

        binder.btnreg.setOnClickListener {
            register()
        }
    }

    private fun login() {
        val userEmail = binder.userEmail.text.toString()
        val userPassword = binder.password.text.toString()

        val userData = db.getUser(userEmail)
        if (userData != null && userData.password == userPassword) {
            // Login successful, start home activity
            val intent = Intent(this, LoginSuccessfully::class.java)
            val bundle = Bundle()
            bundle.putString("username", userData.useremail)

            intent.putExtras(bundle)
            startActivity(intent)

            Toast.makeText(this, "valid username or password", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()
        }
    }
    private fun validate(): Boolean {
        if (binder.userEmail.text.toString().isEmpty()) {
            binder.userEmail.error = "Please fill in the name"
            binder.userEmail.requestFocus()
            return false
        }

        if (binder.password.text.toString().isEmpty()) {
            binder.password.error = "Please fill in the Password"
            binder.password.requestFocus()
            return false
        }
        return true
    }


    private fun register(){
        val intent = Intent(this,RegisterPage::class.java)
        startActivity(intent)

    }

}
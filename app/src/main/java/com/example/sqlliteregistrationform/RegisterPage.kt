package com.example.sqlliteregistrationform

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlliteregistrationform.databinding.ActivityRegisterPageBinding
import java.util.*

class RegisterPage : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DatabaseHelper(applicationContext)

        // calling the databaseHelper class

        binding.button.setOnClickListener {
            if (validate()) {
                val username = binding.username.text.toString()
                val useremail = binding.userEmail.text.toString()
                val userpassword = binding.userpassword.text.toString()
                val usermobile = binding.userMobile.text.toString()
                val userdob = binding.userDob.text.toString()
                val usergender = binding.userMale.text.toString()

                dbHelper.insertData(UserObject(username,useremail,userpassword,usermobile,userdob,usergender))

                Toast.makeText(applicationContext, "User registered successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RegistrationSuccessfully::class.java)
                startActivity(intent)
            }
        }
        binding.userDob.setOnClickListener {
            showDatePickerDialog()
        }

        binding.delete.setOnClickListener {
            binding.username.text.clear()
            binding.userEmail.text.clear()
            binding.userpassword.text.clear()
            binding.userDob.text?.clear()
            binding.userMobile.text.clear()
            binding.userMale.text=""
        }

    }







    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDay"
                binding.userDob.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun validate(): Boolean {
        if (binding.username.text.toString().isEmpty()) {
            binding.username.error = "Please fill in the name"
            binding.username.requestFocus()
            return false
        }

        if (binding.userEmail.text.toString().isEmpty()) {
            binding.userEmail.error = "Please fill in the email"
            binding.userEmail.requestFocus()
            return false
        }

        if (binding.userpassword.text.toString().isEmpty()) {
            binding.userpassword.error = "Please fill in the password"
            binding.userpassword.requestFocus()
            return false
        }

        if (binding.userMobile.text.toString().isEmpty()) {
            binding.userMobile.error = "Please fill in the mobile number"
            binding.userMobile.requestFocus()
            return false
        }


        return true
    }
}

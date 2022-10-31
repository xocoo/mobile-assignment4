package com.miu.assignment4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


    }

    fun createNewAccount(view: View) {
        var fn = et_first_name.text.toString()
        var ln = et_last_name.text.toString()
        var email = et_email_address.text.toString()
        var pass = et_password.text.toString()

        if (fn.isEmpty() || ln.isEmpty() || email.isEmpty() || pass.isEmpty())
            Toast.makeText(this, "Please fill all field", Toast.LENGTH_LONG).show()

        val userNew = User(fn, ln, email, pass)
//        Toast.makeText(this, "${userNew.toString()}", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Your account created", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userNew", userNew)
        setResult(RESULT_OK, intent)
        finish()
    }
}
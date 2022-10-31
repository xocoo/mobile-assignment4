package com.miu.assignment4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user1 = User("Khosbayar", "Sandag", "xocoo@miu.edu", "mypass")
        val user2 = User("Byanbo", "Ba", "byano@miu.edu", "mypass")
        val user3 = User("Temka", "Te", "temka@miu.edu", "mypass")
        val user4 = User("Jargal", "JAK", "jarga@miu.edu", "mypass")
        val user5 = User("Tamir", "Ga", "tamir@miu.edu", "mypass")
        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)

        var resultContracts =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val serUser = data!!.getSerializableExtra("userNew")
                    val newUser = serUser as User
                    users.add(newUser)
                    Toast.makeText(this, "User added", Toast.LENGTH_LONG).show()
                } else
                    Toast.makeText(this, "Failed to get Result", Toast.LENGTH_LONG).show()
            }
        btn_create_account.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
            resultContracts.launch(intent)
        }

        sign_in_btn.setOnClickListener {
            var inputEmail = et_email.text.toString()
            var inputPassword = et_password.text.toString()
            val res = checkLogin(inputEmail, inputPassword)
            if (res == "wrong") {
                Toast.makeText(this, "Wrong username, password", Toast.LENGTH_LONG).show()

            } else {
                var intent = Intent(this, ShoppingCategory::class.java)
                intent.putExtra("username", res)
                startActivity(intent)
            }
        }
    }

    fun checkLogin(input_email: String, input_password: String): String {
        var isValid = true;
        if (isEmpty(input_email)) {
            Toast.makeText(this, "Please input Email", Toast.LENGTH_LONG).show()
            isValid = false
        } else
            if (!isEmail(input_email)) {
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_LONG).show()
                isValid = false
            }

        if (isEmpty(input_password)) {
            Toast.makeText(this, "Please input Password", Toast.LENGTH_LONG).show()
            isValid = false
        }

        if (isValid) {
            for (user in users)
                if (user.emailId == input_email && user.password == input_password)
                    return "${user.firstName}  ${user.lastName}"
        }
        return "wrong"
    }

    fun isEmail(input_email: String): Boolean {
        return (!input_email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(input_email).matches())
    }

    fun isEmpty(input: String): Boolean {
        return input.isEmpty()
    }

    fun forgotPassword(view: View) {
        var email = et_email.text.toString()
        if (isEmpty(email)) {
            Toast.makeText(this, "Please input Email", Toast.LENGTH_LONG).show()
        }
        for (user in users) {
            if (user.emailId == email) {
                val emailIntent = Intent()
                emailIntent.action = Intent.ACTION_SEND
                emailIntent.data = Uri.parse("mailto:")
                emailIntent.type = "text/plain"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your password")
                emailIntent.putExtra(Intent.EXTRA_TEXT, user.password)
                try {
                    //start email intent
                    startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."))
                } catch (e: Exception) {
                    //if any thing goes wrong for example no email client application or any exception
                    //get and show exception message
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
            } else
                Toast.makeText(this, "Email not found", Toast.LENGTH_LONG).show()
        }
    }

}
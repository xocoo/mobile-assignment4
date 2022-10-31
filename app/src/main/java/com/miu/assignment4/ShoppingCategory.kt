package com.miu.assignment4

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shopping_category.*

class ShoppingCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        var sintent = intent
        tv_user.text = sintent.getStringExtra("username")
    }

    fun ClickEvent(view: View) {
        when (view.id) {
            R.id.img_electronics -> toastMsg(getResources().getString(R.string.electronics))
            R.id.img_beauty -> toastMsg(getResources().getString(R.string.beauty))
            R.id.img_clothing -> toastMsg(getResources().getString(R.string.clothing))
            R.id.img_food -> toastMsg(getResources().getString(R.string.food))
        }
    }

    fun toastMsg(txt: String) {
        Toast.makeText(this, "You have chosen the $txt category of shopping", Toast.LENGTH_SHORT)
            .show()
    }
}
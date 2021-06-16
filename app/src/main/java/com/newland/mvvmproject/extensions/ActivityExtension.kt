package com.newland.mvvmproject.extensions

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun <T> Activity.startActivity(clazz: Class<T>){
    val intent= Intent(this,clazz)
    startActivity(intent)
}
fun Activity.showToast(text:String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}
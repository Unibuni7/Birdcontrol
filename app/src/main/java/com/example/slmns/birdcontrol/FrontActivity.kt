package com.example.slmns.birdcontrol

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class FrontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)


    }

    fun watchbirds(view: View) {
        startActivity(Intent(this, BirdWatch::class.java))
    }

    fun PictureBirds(view: View) {}

    fun Information(view: View) {
        val data = intent
        val randMail = data.getStringExtra(EMAIL)

        Log.d("MINE", "Hello" + randMail)
    }

    companion object {
        val EMAIL = "Email"
    }
}

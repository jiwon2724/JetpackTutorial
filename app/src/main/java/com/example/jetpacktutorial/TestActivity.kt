package com.example.jetpacktutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacktutorial.databinding.ActivityTestBinding
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityTestBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(activityTestBinding.root)

        activityTestBinding.textView.text = "test activity"
    }
}
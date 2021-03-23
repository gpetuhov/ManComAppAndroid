package ru.mancomapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mancomapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()
}
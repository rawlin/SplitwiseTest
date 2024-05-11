package com.rawlincrasto.splitwise.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.rawlincrasto.splitwise.databinding.ActivityAddExpencesBinding

class AddExpensesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.os.Bundle
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setToolBar()
    }

    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        txtTitle = findViewById(R.id.toolbar_title)
        txtTitle.text = getString(R.string.user_profile)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
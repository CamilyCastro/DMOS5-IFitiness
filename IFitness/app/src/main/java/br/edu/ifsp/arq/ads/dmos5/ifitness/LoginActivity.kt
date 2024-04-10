package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class LoginActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView
    lateinit var btnNewUser: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setToolBar()
        setBtnNewUser()
    }

    private fun setBtnNewUser() {
        btnNewUser = findViewById(R.id.btn_login_new_user)
        btnNewUser.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                this@LoginActivity,
                UserRegisterActivity::class.java
            )
            startActivity(intent)
        })
    }
    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        txtTitle = findViewById(R.id.toolbar_title)
        txtTitle.text = getString(R.string.login)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
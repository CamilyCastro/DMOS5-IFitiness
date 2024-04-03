package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var txtTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        txtTitle = findViewById<TextView>(R.id.toolbar_title)
        txtTitle.text = getString(R.string.app_name)

        drawerLayout = findViewById<DrawerLayout>(R.id.nav_drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.toggle_open,
            R.string.toggle_close
        )
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { item ->
            var intent: Intent?
            when (item.itemId) {
                R.id.nav_home -> {
                    intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.nav_account -> Toast.makeText(
                    this@MainActivity,
                    "Minha Conta",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_activity -> {
                    intent = Intent(
                        this@MainActivity,
                        ActivityRegisterActivity::class.java
                    )
                    startActivity(intent)
                }

                R.id.nav_ranking -> Toast.makeText(
                    this@MainActivity,
                    "Classificação",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_statitics -> Toast.makeText(
                    this@MainActivity,
                    "Estatísticas",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_logout -> Toast.makeText(this@MainActivity, "Sair", Toast.LENGTH_SHORT)
                    .show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
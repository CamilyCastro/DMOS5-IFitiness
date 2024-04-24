package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import br.edu.ifsp.arq.ads.dmos5.ifitness.viewModel.UserViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var txtTitle: TextView
    lateinit var txtLogin: TextView

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      //inicializa tela principal
        setContentView(R.layout.activity_main)      //configura layout
        setToolbar()        //chamando metodo que configura barra de ferramntas
        setDrawerLayout()       //chamando metodo que configura a naegação em gavetas
        setNavigationView()     //chamando método que configura o menu de atividades do app
        setTextLogin()      //chamando método que redireciona o usuário para a tela de login, se o text "ENTRAR" for pressionado
    }

    private fun setTextLogin() {
        txtLogin = navigationView.getHeaderView(0)      //atribuindo valor a txtLogin, no caso ele receberá o objeto TextView "Entrar"
            .findViewById(R.id.header_profile_name)             //inicializando
        txtLogin.setOnClickListener {       //método que define ação se o objeto for pressionado
            val intent = Intent(        //atribuindo a classe LoginActivity ao objeto intent
                this@MainActivity,
                LoginActivity::class.java
            )
            startActivity(intent)       //startando LoginActivity
        }
    }

    private fun setNavigationView() {
        navigationView = findViewById<NavigationView>(R.id.nav_view)        //inicializando navigationView(Menu)
        navigationView.setNavigationItemSelectedListener { item ->      //atribuindo um item adquirido do when(equivalente a switch case) ao objeto intent
            var intent: Intent?
            when (item.itemId) {
                R.id.nav_home -> {
                    intent = Intent(this@MainActivity, MainActivity::class.java)        //neste caso intent recebe MAinActivity
                    startActivity(intent)   //startando MainActivity
                }

                R.id.nav_account -> {
                    intent = Intent(
                        this@MainActivity,      //neste caso intent recebe UserProfileActivity
                        UserProfileActivity::class.java
                    )
                    startActivity(intent)
                }

                R.id.nav_activity -> {
                    intent = Intent(
                        this@MainActivity,
                        ActivityRegisterActivity::class.java        //neste caso intent recebe ActivityRegister
                    )
                    startActivity(intent)
                }

                R.id.nav_ranking -> Toast.makeText(
                    this@MainActivity,      //neste caso intent receberia a classe responsável por exibir a tela de classificação, porém
                    "Classificação",          //como esta classe ainda não foi criada, um toast é exibido na MainActivity mesmo
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_statitics -> Toast.makeText(
                    this@MainActivity,      //neste caso intent receberia a classe responsável por exibir a tela de estatisticas, porém
                    "Estatísticas",           //como esta classe ainda não foi criada, um toast é exibido na MainActivity mesmo
                    Toast.LENGTH_SHORT
                ).show()

                R.id.nav_logout -> {
                    intent = Intent(
                        this@MainActivity,      //neste caso intent recebe LoginActivity, pois o usuário saiu de sua conta e deve entrar em outra, ou na sua própria novamente
                        LoginActivity::class.java
                    )
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)               //indica que a janela posicionada a esquerda(START) da tela, deve ser fechada
            true
        }
    }

    private fun setDrawerLayout() {
        drawerLayout = findViewById(R.id.nav_drawer_layout)     //recupera o objeto DrawerLayout do layout desta activity através do id
        val toggle = ActionBarDrawerToggle(     //classe auxiliar que simplifica interação entre gaveta de interação e barra de ferramentas
            this,
            drawerLayout,
            toolbar,
            R.string.toggle_open,
            R.string.toggle_close
        )
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

    private fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        txtTitle = findViewById(R.id.toolbar_title)
        txtTitle.text = getString(R.string.app_name)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
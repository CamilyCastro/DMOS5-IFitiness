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
        super.onCreate(savedInstanceState)      //inicializanado tela de login
        setContentView(R.layout.activity_login)     //configurando layout tela de login
        setToolBar()        //chamando método que configura barra de ferramentas
        setBtnNewUser()     //chamando metodo que configura botão que redireciona o usuario para a tela de cadastro de novos usuarios.
    }

    private fun setBtnNewUser() {
        btnNewUser = findViewById(R.id.btn_login_new_user)      //inicializando botão
        btnNewUser.setOnClickListener(View.OnClickListener {        //método chamado qunado o botão é pressionado
            val intent = Intent(        //objeto intent
                this@LoginActivity,
                UserRegisterActivity::class.java        //atribuição da classe UserRegisterActivity ao objeto intent
            )
            startActivity(intent)       //startando intent(UserRegisterActivity)
        })
    }
    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)        //inicializando barra de ferramentas

        supportActionBar?.setDisplayHomeAsUpEnabled(true)       //configura exibição botão voltar
        supportActionBar?.setDisplayShowTitleEnabled(false)     //desativa exibição titulo atividade

        txtTitle = findViewById(R.id.toolbar_title)     //inicializa title
        txtTitle.text = getString(R.string.login)       //define texto
    }

    override fun onSupportNavigateUp(): Boolean {       // metodo que encerra atividade atual e volta para a anterior, quando o 'VOLTAR' é pressionado
        onBackPressed()
        return true
    }
}
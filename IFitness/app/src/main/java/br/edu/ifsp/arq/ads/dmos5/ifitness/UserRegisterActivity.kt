package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      //inicializando a tela de perfil/MyAccount
        setContentView(R.layout.activity_user_register)      //configurando layout
        setToolBar()    //chamando método que configura barra de ferramentas
    }

    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)        //atribuindo valor ao objeto toolbar
        setSupportActionBar(toolbar)        //inicializando barra de ferramentas

        supportActionBar?.setDisplayHomeAsUpEnabled(true)       //configura exibição botão voltar
        supportActionBar?.setDisplayShowTitleEnabled(false)     //desativa exibição titulo atividade

        txtTitle = findViewById(R.id.toolbar_title)     //inicializa title
        txtTitle.text = getString(R.string.new_user)        //define texto
    }


    override fun onSupportNavigateUp(): Boolean {        //metodo que encerra atividade atual e volta para a anterior, quando o 'VOLTAR' é pressionado
        onBackPressed()
        return true
    }
}
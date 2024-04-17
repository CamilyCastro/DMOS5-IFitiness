package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      //inicializando tela inicial, que contém geralmente a logo
        setContentView(R.layout.activity_splash)        //configurando layout

        val intent = Intent(this, MainActivity::class.java)     //criando objeto intent e atribuindo a classe MainActivity a ele
        val handler = Handler()     //criando um objeto  handler
        handler.postDelayed(Runnable {      //handler cria uma ação com atraso, portanto, após um delay de
            startActivity(intent)           //3000 milisegundos, a intent(MainActivity) é startada.
            finish()        //metodo que encerra a SplashActivity
        }, 3000)
    }
}



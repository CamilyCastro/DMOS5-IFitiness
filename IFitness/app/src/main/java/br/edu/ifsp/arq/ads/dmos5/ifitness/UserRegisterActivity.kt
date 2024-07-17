package br.edu.ifsp.arq.ads.dmos5.ifitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import br.edu.ifsp.arq.ads.dmos5.ifitness.model.User
import br.edu.ifsp.arq.ads.dmos5.ifitnessapp.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class UserRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView
    lateinit var edtName: TextInputEditText
    lateinit var edtEmail: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    lateinit var btnUserRegister: Button

    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      //inicializando a tela de perfil/MyAccount
        setContentView(R.layout.activity_user_register)      //configurando layout
        setToolBar()    //chamando método que configura barra de ferramentas
        setBtnUserRegister()
    }

    private fun setBtnUserRegister() {
        edtName = findViewById(R.id.txt_edt_name)
        edtEmail = findViewById(R.id.txt_edt_email)
        edtPassword = findViewById(R.id.txt_edt_password)

        btnUserRegister = findViewById(R.id.btn_user_register)
        btnUserRegister.setOnClickListener {
            if (validate()) {
                val user = User(
                    email = edtEmail.text.toString(),
                    name = edtName.text.toString(),
                    surname = "",
                    password = edtPassword.text.toString(),
                    image = "",
                    dateOfBirth = null,
                    gender = User.Gender.PREFIRO_NAO_DIZER
                )

                userViewModel.createUser(user)
                userViewModel.login(user.email, user.password).observe(this, Observer {
                    finish()
                })
            }
        }
    }

    private fun validate() : Boolean {
        var isValid = true

        edtName.apply {
            if(text.isNullOrEmpty()) {
                error = "Preencha o campo nome."
                isValid = false
            } else {
                error = null
            }
        }
        edtEmail.apply {
            if(text.isNullOrEmpty()) {
                error = "Preencha o campo email."
                isValid = false
            } else {
                error = null
            }
        }
        edtPassword.apply {
            if(text.isNullOrEmpty()) {
                error = "Preencha o campo a senha."
                isValid = false
            } else {
                error = null
            }
        }

        return isValid
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
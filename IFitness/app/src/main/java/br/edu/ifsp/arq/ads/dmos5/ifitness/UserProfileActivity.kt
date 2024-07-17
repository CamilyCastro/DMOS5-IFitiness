package br.edu.ifsp.arq.ads.dmos5.ifitness

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import br.edu.ifsp.arq.ads.dmos5.ifitness.model.User
import br.edu.ifsp.arq.ads.dmos5.ifitnessapp.viewmodel.UserViewModel

import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserProfileActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView
    lateinit var edtName: TextInputEditText
    lateinit var edtSurname: TextInputEditText
    lateinit var edtEmail: TextInputEditText
    lateinit var edtDateOfBirth: TextInputEditText
    lateinit var spnGender: Spinner
    lateinit var btnProfileUpdate: Button

    private val userViewModel by viewModels<UserViewModel>()

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)      //inicializando a tela de perfil/MyAccount
        setContentView(R.layout.activity_user_profile)      //configurando layout
        setToolBar()    //chamando método que configura barra de ferramentas
        setComponents()
        loadUserLogged()
        setBtnProfileUpdate()
    }

    private fun setBtnProfileUpdate() {
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        if(validate()) {
            user.email = edtEmail.text.toString()
            user.name = edtName.text.toString()
            user.surname = edtSurname.text.toString()
            user.dateOfBirth = edtDateOfBirth.text.toString()
            user.gender = User.Gender.values()[spnGender.selectedItemPosition]
            if (userViewModel.updateUser(user)) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.update_message),
                    Toast.LENGTH_SHORT
                ).show()
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
        edtSurname.apply {
            if(text.isNullOrEmpty()) {
                error = "Preencha o campo sobrenome."
                isValid = false
            } else {
                error = null
            }
        }

        edtDateOfBirth.apply {
            if(text.isNullOrEmpty()) {
                error = "Preencha o campo data de nascimento."
                isValid = false
            } else {
                error = null
            }
        }

        return isValid
    }


    private fun loadUserLogged() {
        userViewModel.isLogged().observe(this, Observer {
            if (it != null) {
                user = it
                edtName.setText(it.name)
                edtSurname.setText(it.surname)
                edtEmail.setText(it.email)
                if(it.dateOfBirth != null){
                    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    edtDateOfBirth.setText(it.dateOfBirth!!.format(dtf).toString())
                }else{
                    edtDateOfBirth.setText("")
                }
                resources.getStringArray(R.array.gender).asList().indexOf(it.gender?.value).let {
                    spnGender.setSelection(it)
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
    }

    private fun setComponents() {
        edtName = findViewById(R.id.txt_edt_name);
        edtSurname = findViewById(R.id.txt_edt_surname);
        edtEmail = findViewById(R.id.txt_edt_email);
        edtDateOfBirth = findViewById(R.id.txt_edt_date_of_birth);
        spnGender = findViewById(R.id.spn_gender);
    }


    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)        //atribuindo valor ao objeto toolbar
        setSupportActionBar(toolbar)        //inicializando barra de ferramentas

        supportActionBar?.setDisplayHomeAsUpEnabled(true)       //configura exibição botão voltar
        supportActionBar?.setDisplayShowTitleEnabled(false)     //desativa exibição titulo atividade

        txtTitle = findViewById(R.id.toolbar_title)     //inicializa title
        txtTitle.text = getString(R.string.user_profile)        //define texto
    }


    override fun onSupportNavigateUp(): Boolean {        //metodo que encerra atividade atual e volta para a anterior, quando o 'VOLTAR' é pressionado
        onBackPressed()
        return true
    }
}
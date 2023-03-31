package com.example.fofoca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Cadastro : AppCompatActivity() {

    private lateinit var tiCadastro: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbVerdade: RadioButton
    private lateinit var rbMentira: RadioButton
    private lateinit var btnSalvar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.tiCadastro = findViewById(R.id.tiCadastro)
        this.radioGroup = findViewById(R.id.radioGroup)
        this.rbVerdade = findViewById(R.id.rbVerdade)
        this.rbMentira = findViewById(R.id.rbMentira)
        this.btnSalvar = findViewById(R.id.btnSalvar)
        this.btnCancelar = findViewById(R.id.btnCancelar)

        this.btnSalvar.setOnClickListener({ this.cadastrar() })
        this.btnCancelar.setOnClickListener({ this.cancelar() })

    }

    fun cadastrar(){
        val descricao = this.tiCadastro.text.toString()
        val status = this.rbVerdade.isChecked
        val salvar = Fofoca(descricao, status)
        val intent = Intent().apply {
            putExtra("FOFOCA", salvar)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    fun cancelar() {
        finish()
    }



}
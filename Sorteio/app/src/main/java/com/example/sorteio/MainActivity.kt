package com.example.sorteio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var btnSortear: Button
    private lateinit var tvResultado: TextView
    private lateinit var sorteio: Sorteio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etNome = findViewById(R.id.etNome)
        this.btnCadastrar = findViewById(R.id.btnCadastrar)
        this.btnSortear = findViewById(R.id.btnSortear)
        this.tvResultado = findViewById(R.id.tvResultado)
        this.sorteio = Sorteio()

        this.btnCadastrar.setOnClickListener({ cadastrar() })
        this.btnSortear.setOnClickListener({ sortear() })
        this.etNome.setText("")
    }

//    inner class ClickBotao: View.OnClickListener {
//        override fun onClick(v: View?) {
//            var nome = this@MainActivity.etNome.text
//
//            this@MainActivity.sorteio.add(nome.toString())
//        }
//    }

    fun cadastrar() {
        val nome = this@MainActivity.etNome.text.toString()
        this.sorteio.add(nome)
        this.etNome.text.clear()
    }

    fun sortear() {
        val nome = this.sorteio.sorteio()
        if (nome == null) {
            Toast.makeText(this, "Não é possível inserir um valor vazio!", Toast.LENGTH_SHORT).show()
        }
        this@MainActivity.tvResultado.text = nome
    }
}
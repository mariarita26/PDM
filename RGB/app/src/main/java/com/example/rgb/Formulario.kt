package com.example.rgb

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText

class Formulario : AppCompatActivity() {

    private lateinit var etCor: EditText
    private lateinit var etAzul: EditText
    private lateinit var etVermelho: EditText
    private lateinit var etVerde: EditText
    private lateinit var btnEnviar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        this.etCor = findViewById(R.id.etCor)
        this.etAzul = findViewById(R.id.etAzul)
        this.etVermelho = findViewById(R.id.etVermelho)
        this.etVerde = findViewById(R.id.etVerde)
        this.btnEnviar = findViewById(R.id.btnEnviar)
        this.btnCancelar = findViewById(R.id.btnCancelar)

        val cor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("COR", Cor::class.java)
        } else {
            intent.getSerializableExtra("COR")
        } as? Cor

        this.etCor.setText(cor?.nome)
        this.etVermelho.setText(cor?.vermelho.toString())
        this.etVerde.setText(cor?.verde.toString())
        this.etAzul.setText(cor?.azul.toString())

        this.btnEnviar.setOnClickListener({ this.cadastrar() })
        this.btnCancelar.setOnClickListener({ this.cancelar() })
    }

    fun cadastrar(){
        val cor = this.etCor.text.toString()
        val vermelho = this.etVermelho.text.toString().toInt()
        val verde = this.etVerde.text.toString().toInt()
        val azul = this.etAzul.text.toString().toInt()
        val salvar = Cor(cor, vermelho, verde, azul)
        val corHexadecimal = salvar.toHex()

        val intent = Intent().apply {
            putExtra("COR", salvar)
        }
        setResult(RESULT_OK, intent)
        finish()
        Log.d("FORMULARIO_ACTIVITY", "Nova cor adicionada: $corHexadecimal")

        val resultIntent = Intent().apply {
            putExtra("nome", etCor.text.toString())
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
    fun cancelar() {
        finish()
    }
}
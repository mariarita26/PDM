package com.example.rgb

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class Formulario : AppCompatActivity() {

    private lateinit var etCor: EditText
    private lateinit var etRGB: EditText
    private lateinit var etVermelho: EditText
    private lateinit var etVerde: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        this.etCor = findViewById(R.id.etCor)
        this.etRGB = findViewById(R.id.etRGB)
        this.etVermelho = findViewById(R.id.etVermelho)
        this.etVerde = findViewById(R.id.etVerde)
        this.btnEnviar = findViewById(R.id.btnEnviar)

        val cor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("COR", Cor::class.java)
        } else {
            intent.getSerializableExtra("COR")
        } as? Cor

        this.etCor.setText(cor?.nome)
        this.etRGB.setText(cor?.codigo)

        this.btnEnviar.setOnClickListener({ this.cadastrar() })
    }

    fun cadastrar(){
        val cor = this.etCor.text.toString()
        val codigoCor = this.etRGB.toString()
        val salvar = Cor(cor, codigoCor)
        val intent = Intent().apply {
            putExtra("COR", salvar)
        }
        setResult(RESULT_OK, intent)
        finish()
        Log.d("FORMULARIO_ACTIVITY", "Nova cor adicionada: $cor")


        val resultIntent = Intent().apply {
            putExtra("nome", etCor.text.toString())
//            putExtra("red", sbRed.progress)
//            putExtra("green", sbGreen.progress)
//            putExtra("blue", sbBlue.progress)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

    }
}
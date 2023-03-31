package com.example.fofoca

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var btnJogar: Button
    private lateinit var btnCadastrar: Button
    private lateinit var salvar: Salvar

    init {
        this.salvar = Salvar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnJogar = findViewById(R.id.btnJogar)
        this.btnCadastrar = findViewById(R.id.btnCadastrar)

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val fofoca = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("FOFOCA", Fofoca::class.java)
                } else {
                    it.data?.getSerializableExtra("FOFOCA")
                } as Fofoca
                this.salvar.add(fofoca)
                Toast.makeText(this, "Cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        var jogoResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                Toast.makeText(this, "Ganhou!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Perdeu!", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnJogar.setOnClickListener{
            val fofoca = this.salvar.get()
            val intent = Intent(this, Jogo::class.java).apply {
                putExtra("FOFOCA", fofoca)
            }
            jogoResult.launch(intent)
        }

        this.btnCadastrar.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            formResult.launch(intent)
        }
    }
}
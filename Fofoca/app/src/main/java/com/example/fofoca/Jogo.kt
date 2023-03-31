package com.example.fofoca

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class Jogo : AppCompatActivity() {

    private lateinit var tvDescricao: TextView
    private lateinit var radioGroup2: RadioGroup
    private lateinit var rbVerdade2: RadioButton
    private lateinit var rbMentira2: RadioButton
    private lateinit var btnResposta: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var fofoca: Fofoca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        this.tvDescricao = findViewById(R.id.tvDescricao)
        this.radioGroup2 = findViewById(R.id.radioGroup2)
        this.rbVerdade2 = findViewById(R.id.rbVerdade2)
        this.rbMentira2 = findViewById(R.id.rbMentira2)
        this.btnResposta = findViewById(R.id.btnResposta)
        this.progressBar = findViewById(R.id.progressBar)

        this.fofoca = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("FOFOCA", Fofoca::class.java)
        } else {
            intent.getSerializableExtra("FOFOCA")
        } as Fofoca

        this.tvDescricao.text = fofoca.texto
        this.btnResposta.setOnClickListener({ responder() })

        iniciaTempo()
    }

    fun responder(){
        if ((this.fofoca.booleano) && (this.rbVerdade2.isChecked)){
            setResult(RESULT_OK)
        }else if ((!this.fofoca.booleano) && (this.rbMentira2.isChecked)){
            setResult(RESULT_OK)
        }
        finish()
    }

    fun iniciaTempo(){
        Thread{
            while (this.progressBar.progress < 100){
                this.progressBar.progress += 1
                Thread.sleep(100)
            }
            finish()
        }.start()
    }

}
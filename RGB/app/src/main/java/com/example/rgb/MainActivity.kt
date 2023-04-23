package com.example.rgb

import android.content.ClipData
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvNomes: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var salvar: Salvar
    private lateinit var etText: EditText
    private lateinit var cor: Cor
    private var lista = mutableListOf<String>()
    private lateinit var adapter: MyAdapter
    private var formResult: ActivityResultLauncher<Intent>? = null

    init {
        this.salvar = Salvar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvNomes = findViewById(R.id.rvNomes)
        this.fabAdd = findViewById(R.id.fabAdd)

        Log.d("MainActivity", "Lista de cores: ${this.salvar.listaCores}")

        this.adapter = MyAdapter(this.lista)

        this.rvNomes.adapter = this.adapter

        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvNomes)

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val cor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("COR", Cor::class.java)
                } else {
                    it.data?.getSerializableExtra("COR")
                } as Cor
                this.salvar.add(cor)
                Log.d("COR", cor.toString())
                Log.d("LISTA", this.salvar.listaCores.map { it.nome }.toString())
                Toast.makeText(this, "Cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                this.lista.add(cor.nome)
                this.adapter.atualizarLista(this.lista)

            }
        }

        this.fabAdd.setOnClickListener {
            val intent = Intent(this, Formulario::class.java)
            formResult.launch(intent)
        }

    }

    inner class OnItemClick: OnItemClickRecyclerView() {
        override fun onItemClick(position: Int) {
            val intent = Intent(this@MainActivity, Formulario::class.java).apply {
                putExtra("COR", cor)
            }
            this@MainActivity.formResult?.launch(intent)
        }
    }

    inner class OnSwipe : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            (this@MainActivity.rvNomes.adapter as MyAdapter).mov(
                viewHolder.adapterPosition,
                target.adapterPosition
            )
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val item = lista[position]

            if (direction == ItemTouchHelper.END){
                val cor = salvar.listaCores[position]
                val code = cor.codigo

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Código hexadecimal da cor ${cor.nome}: $code")
                }
                startActivity(Intent.createChooser(intent, "Compartilhar código da cor"))

            } else {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Excluir item")
                    .setMessage("Tem certeza que deseja excluir este item?")
                    .setPositiveButton("Sim") { dialog, _ ->
                        (this@MainActivity.rvNomes.adapter as MyAdapter).del(position)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Não") { dialog, _ ->
                        (this@MainActivity.rvNomes.adapter as MyAdapter).atualizarLista(lista)
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

}
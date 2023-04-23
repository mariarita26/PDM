package com.example.rgb

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var lista: MutableList<String>): RecyclerView.Adapter<MyAdapter.MyHolder>() {
    var onItemClickRecyclerView: OnItemClickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyHolder, position: Int) {
        val nome = this.lista.get(position)
        holder.tvNome.text = nome
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    fun atualizarLista(novaLista: MutableList<String>) {
        this.lista = novaLista
        notifyDataSetChanged()
    }

    fun add(nome: String){
        this.lista.add(nome)
        this.notifyItemInserted(this.lista.size)
    }

    fun del(position: Int){
        this.lista.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.lista.size)
    }

    fun mov(firstNamePosition: Int, secondNamePosition: Int) {
        val tmp = this.lista[firstNamePosition]
        this.lista[firstNamePosition] = this.lista[secondNamePosition]
        this.lista[secondNamePosition] = tmp
        this.notifyItemChanged(firstNamePosition)
        this.notifyItemChanged(secondNamePosition)
    }

    inner class MyHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var tvNome: TextView

        init {
            this.tvNome = itemView.findViewById(R.id.tvItemNome)

            itemView.setOnClickListener{
                this@MyAdapter.onItemClickRecyclerView?.onItemClick(this.adapterPosition)
            }
        }
    }
}

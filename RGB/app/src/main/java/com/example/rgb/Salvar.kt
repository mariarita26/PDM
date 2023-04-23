package com.example.rgb

class Salvar {

    var listaCores: MutableList<Cor>

    init {
        this.listaCores = mutableListOf()
    }

    fun add(cor: Cor){
        this.listaCores.add(cor)
    }


}
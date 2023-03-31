package com.example.sorteio

class Sorteio {

    private var valores: MutableList<String>

    init{
        this.valores = mutableListOf()
    }

    fun add(palavra: String) {
        this.valores.add(palavra)
    }

    fun sorteio(): String{
        if (this.valores.isEmpty()) {
            return "TÁ VAZIO"
        }
        return this.valores.random()
    }
}
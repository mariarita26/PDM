package com.example.fofoca

class Salvar {

//    data class TextoBooleano

    private var listaTextos: MutableList<Fofoca>

    init {
        this.listaTextos = mutableListOf()
    }


    fun add(fofoca: Fofoca){
        this.listaTextos.add(fofoca)
    }

    fun verificarTexto(texto: String): Boolean? {
        for (tb in listaTextos) {
            if (tb.texto == texto) {
                return tb.booleano
            }
        }
        return null
    }

    fun get(): Fofoca {
        return this.listaTextos.random()
    }

}
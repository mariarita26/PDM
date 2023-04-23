package com.example.rgb

class Cor (val nome: String, val vermelho: Int, val verde: Int, val azul: Int): java.io.Serializable{

    fun toHex(): String {
        val hexRed = vermelho.toString(16).padStart(2, '0')
        val hexGreen = verde.toString(16).padStart(2, '0')
        val hexBlue = azul.toString(16).padStart(2, '0')
        return "#$hexRed$hexGreen$hexBlue"
    }

}
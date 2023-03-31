package com.example.jogodoarrocha

class Arrocha(var menorValor: Int, var maiorValor: Int) {

    var sorteio: Int
    var status: Status

    init {
        this.sorteio = ((this.menorValor + 1) .. (this.maiorValor - 1)).random()
        this.status = Status.EXECUTANDO
    }

    fun isArrochado(): Boolean{
        return this.menorValor + 1 == this.maiorValor - 1
    }

    fun atualizaIntervalo(chute: Int): Int{
        if (chute < this.sorteio){
            this.menorValor = chute
            return -1
        }else{
            this.maiorValor = chute
            return 1
        }
    }

    fun chuteValido(chute: Int): Boolean{
        return chute > this.menorValor && chute < this.maiorValor && chute != this.sorteio
    }

    fun jogar(chute: Int): Int{
        if (!this.chuteValido(chute)) {
            this.status = Status.PERDEU
            return 0
        }
        else{
            var valor = this.atualizaIntervalo(chute)
            if (this.isArrochado()){
                this.status = Status.GANHOU
            }
            return valor
        }
    }

}
import Foundation

class ArrochaViewModel: ObservableObject {
    private var sorteado: Int
    @Published var menor: Int
    @Published var maior: Int
    @Published var tela: Tipo
    
    init(){
        self.tela = .JOGO
        self.menor = 1
        self.maior = 100
        self.sorteado = Int.random(in: 2 ... 99)
    }
    
    func arrochado() -> Bool {
        return self.menor + 1 == self.maior - 1
    }
    
    func jogo(chute: Int){
        if (chute <= self.menor) || (chute >= self.maior) || (chute == self.sorteado){
            self.tela = .PERDEU
        } else {
            if (chute < self.sorteado){
                self.menor = chute
            } else {
                self.maior = chute
            }
            if (self.arrochado()){
                self.tela = .GANHOU
            }
        }
    }
    
    func reset() {
        self.tela = .JOGO
        self.menor = 1
        self.maior = 100
        self.sorteado = Int.random(in: 2 ... 99)
            
    }

}

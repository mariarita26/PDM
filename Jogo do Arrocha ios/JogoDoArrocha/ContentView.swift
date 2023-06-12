import SwiftUI

struct ContentView: View {
    @ObservedObject var avm: ArrochaViewModel
    
    var body: some View {
        VStack{
            if (self.avm.tela == .JOGO){
                MainView(avm: self.avm)
            }
            else if (self.avm.tela == .PERDEU){
                DerrotaView(avm: self.avm)
            }
            else {
                VitoriaView(avm: self.avm)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(avm: ArrochaViewModel())
    }
}

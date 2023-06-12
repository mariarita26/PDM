import SwiftUI

struct MainView: View {
    @ObservedObject var avm: ArrochaViewModel
    @State var chute: String = ""
    @State var menor: Int = 1
    @State var maior: Int = 100
    
    var body: some View {
        VStack{
            Spacer()
            HStack{
                Text(self.avm.menor.description)
                Text("e")
                Text(self.avm.maior.description)
            }.font(.largeTitle)
            
            Spacer()
            TextField("Chute", text: self.$chute).frame(width: 100).background(Color.cyan).foregroundColor(Color.white)
            Spacer()
            Button("Chutar"){
                let valor = Int(self.chute) ?? 0
                self.avm.jogo(chute: valor)
                self.chute = ""
                
            }
            Spacer()
        }
    }
}


struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView(avm: ArrochaViewModel())
    }
}

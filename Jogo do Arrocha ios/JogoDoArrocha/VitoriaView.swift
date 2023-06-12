import SwiftUI

struct VitoriaView: View {
    @ObservedObject var avm: ArrochaViewModel
    var body: some View {
        VStack{
            Spacer()
            Text("GANHOU")
                .padding()
                .font(.largeTitle)
            Spacer()
            Button("Voltar"){
                self.avm.reset()
            }
            Spacer()
        }.frame(maxWidth: .infinity).background(Color.green)
    }
}

struct VitoriaView_Previews: PreviewProvider {
    static var previews: some View {
        VitoriaView(avm: ArrochaViewModel())
    }
}

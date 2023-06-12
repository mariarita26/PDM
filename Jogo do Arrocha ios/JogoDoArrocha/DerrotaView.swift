import SwiftUI

struct DerrotaView: View {
    @ObservedObject var avm: ArrochaViewModel
    var body: some View {
        VStack{
            Spacer()
            Text("PERDEU")
                .padding()
                .font(.largeTitle)
            Spacer()
            Button("Voltar"){
                self.avm.reset()
            }
            Spacer()
        }
        .frame(maxWidth: .infinity)
        .background(Color.red)

    }
}

struct DerrotaView_Previews: PreviewProvider {
    static var previews: some View {
        DerrotaView(avm: ArrochaViewModel())
    }
}

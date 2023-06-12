import SwiftUI

@main
struct JogoDoArrochaApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(avm: ArrochaViewModel())
        }
    }
}

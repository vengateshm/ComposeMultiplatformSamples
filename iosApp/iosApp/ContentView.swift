import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // Define the width and height callbacks
        let widthCallback: () -> KotlinInt = {
            return KotlinInt(int: Int32(Int(UIScreen.main.bounds.size.width)))
        }
                
        let heightCallback: () -> KotlinInt = {
            return KotlinInt(int: Int32(Int(UIScreen.main.bounds.size.height)))
        }
        return MainViewControllerKt.MainViewController(width: widthCallback, height: heightCallback)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}




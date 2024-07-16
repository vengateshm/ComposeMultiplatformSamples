import SwiftUI
import ComposeApp
import FirebaseApp

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
      FirebaseApp.configure()
    return true
  }
}

@main
struct iOSApp: App {
    
    // register app delegate for Firebase setup
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        AppModulesKt.initializeKoin()
    }
	var body: some Scene {
		WindowGroup {
            ContentView().ignoresSafeArea()
		}
	}
}

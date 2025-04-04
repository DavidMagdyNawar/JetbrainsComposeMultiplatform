This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.



## AI prompting
I asked [Claude 3.7 Sonnet](claud.ai) to generate for me the bioleplate code for the DI Framework I'm using namely [Koin](https://insert-koin.io/) and to generate the network layer.

The generated code could be found under the **ai pacakge** in the project structure under the **commonMain**

My approach I have implementd emphasize the following:
- **Koin** : 
1) Clear separation from paltform and shared modules which I used in the di directory.
2) Using Factory pattern for different data base access based on the platform.

- **Network** :
1) Platform specific for every platform, HTTP for Android and Darwin for iOS,
2) API Service implementation generated from Claude ai is poor in separation of concers as it handles the state management and netwrok request using shared flow, which can cause highly coupling .
3) There is a proper mapping for the network responses to the domain objects using the extension functions.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

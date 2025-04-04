# AI Generator for Kotlin Multiplatform Mobile Apps

This is a Compose multiplatfrom application designed for showing a **list of products** and targeting Andoir, IOS

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

It provides a screen with 2 tabs, the first tab is the list of products from an API, and the second tab is the favourited products.

**Technologies used** : Kotlin compose multiplatform, Ktor, ViewModel, MVI, Room, Koin, HTTP, Darwin, Coroutines, Flow, States management, Unidirectional data flow, and more

## Prerequisites
Use your preferred IDE to run the compose multi-platform project

## Usage
Once the application stars to run on your preferred device(Android-iOS), you can see a Screen contains 2 tabs.

The first tab is for the product list, and the second tab is for the favourited by the user.

## Structure
The project is structured into several packages, In **ComposeApp** It has the following default structure for the following targeted source sets

├── androidMain
├── commonMain
├── iosMain

in the **commonMain/kotlin** package it has the following structure :
├── app
├── core
├── di
├── product 


## Report
Report for using AI is included in the `README.md` file

## License
This project is licensed under the MIT License.


## Code
[You can find the code as a zip file here](url)

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

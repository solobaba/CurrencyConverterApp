# CurrencyConverter

A currency converter application built with Jetpack Compose.

## Languages, libraries and tools used
* Kotlin
* Jetpack compose
* Compose navigation
* Hilt - dependency injection
* Retrofit
* OkHttp
* Moshi
* Flow coroutines

## How to run the project
* Download the latest android studio
* Clone the project from github using this command
  `git clone https://github.com/solobaba/CurrencyConverterApp`
* Open the project in android studio
* Run the project on an emulator or a physical device

## Architecture
* This project makes use of MVVM architecture.
* The **data layer** is responsible communicating with the rest API.
* The **domain layer** is responsible for the business logic.
* The **presentation layer** is responsible for the UI.
* The **navigation layer** is responsible for the navigation.
* The **viewmodel layer** is responsible for the viewmodel.
* The **di layer** is responsible for the dependency injection.
* The **util layer** is responsible for the utility functions.

* Endpoints:
    * https://data.fixer.io/api/`
* The **ViewModel** handles the UI logic
* The **Composables** render the UI
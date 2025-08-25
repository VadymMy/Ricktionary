# Rick & Morty Test Project

## Tech Stack

* **Architecture**: Clean Architecture  
* **Pattern**: MVI (Unidirectional Data Flow)  
* **UI**: Jetpack Compose, Material 3  
* **Navigation**: Navigation-Compose  
* **DI**: Hilt
* **Async**: Kotlin Coroutines + Flow  
* **Network**: Retrofit 2
* **Serialization**: Gson  
* **Local Cache**: Room
* **Pagination**: Paging 3 (RemoteMediator + DB cache)  
* **Images**: Coil

## Features

* Characters list screen with:
  - Smimmer loading
  - Error state with retry button
  - Empty state
  - Data state (list of characters)
  - Pull-to-refresh

* Character details screen:
  - Name, status, gender, species, type
  - Origin and location
  - Episodes list
  - Pills for attributes

## Potential Improvements

* Filters (status, gender, name)  
* Append loader at the bottom of the list  
* Animations for transitions between states
* Snackbar on errors with retry action  

# KlivvrAssignment

## Overview
This project is designed to efficiently filter and display a list of cities based on a prefix input by the user. The goal is to demonstrate problem-solving skills, UX judgment, and code quality while adhering to specific technical constraints.

## Features
- Efficiently loads and filters a list of 200k cities.
- Displays results in a scrollable, alphabetical list.
- Provides a responsive UI that updates as the user types.
- Tapping a city displays its location on Google Maps.

## Technology Stack
- **Language**: Kotlin
- **Architecture**: Model-View-ViewModel (MVVM)
- **Data Structures**: Trie
- **Dependency Injection**: [Dagger/Hilt] (or any DI framework used)
- **Serialization**: [Gson/Moshi] (or the serialization library used)

## Architectural Decisions

### MVVM Architecture
I chose the MVVM pattern to separate concerns and ensure maintainability and scalability. This architecture allows the ViewModel to handle all business logic and UI-related data, keeping the UI layer (View) as simple as possible. This separation also facilitates testing and future modifications.

- **Model**: Represents the data and the business logic.
- **ViewModel**: Manages UI-related data and handles user input. Uses `MutableStateFlow` and `SharedFlow` to manage state and events.
- **View**: Displays the data and handles user interactions.

### Trie Data Structure
To efficiently filter cities based on the prefix, I used a Trie (prefix tree). A Trie allows for faster lookups, making it an optimal choice for this use case where the goal is to have time efficiency better than linear.

- **Why Trie?**
  - **Fast Lookup**: Tries offer an efficient way to search for prefixes, which is crucial for large datasets like the one in this project.
  - **Memory Usage**: While Tries may use more memory compared to simple lists, the time efficiency gain in prefix searches is significant, especially with a dataset of 200k cities.

### Domain Layer
The domain layer contains the business logic and is independent of the other layers. This makes the app more modular and easier to test.

- **Use Case**: The filtering logic is encapsulated in a use case that interacts with the Trie data structure to provide the filtered list of cities to the ViewModel.

## UI/UX Considerations
- **Responsiveness**: The UI is designed to be highly responsive, updating the list in real-time as the user types.
- **User Interaction**: Tapping on a city opens its location in Google Maps, enhancing user experience.

## Additional Notes
- **Screen Rotation**: The app supports screen rotation, maintaining the state and current filter.
- **Code Comments**: I have added comments in the code to explain key decisions and optimizations, particularly around the use of the Trie and MVVM architecture.

## How to Run
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or device running Android 5.0+.

## Conclusion
This project showcases the use of efficient algorithms and modern Android architecture patterns to create a responsive and maintainable app. By using a Trie for prefix filtering and MVVM for architecture, the app is both performant and easy to extend.

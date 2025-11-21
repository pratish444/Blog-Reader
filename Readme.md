# Blog Reader App

A modern Android application built with Jetpack Compose that fetches and displays blog posts from the WordPress REST API.  
The app uses an offline-first approach, infinite scroll pagination, and Material Design 3.

---

## Screenshots

| Blog List                                    | Blog Detail                                      | Offline / Loading State                                 |
|---------------------------------------------|--------------------------------------------------|--------------------------------------------------------|
| ![Blog List](app/src/test/blogs.jpeg)       | ![Blog Detail](app/src/test/blog%20detail.jpeg)  | ![Offline Blogs](app/src/test/offline%20blogs.jpeg)    |

---

## Features

### User Features
- Blog List screen showing posts with images, titles, and excerpts
- Blog Detail screen with full content rendered in a styled WebView
- Infinite scroll pagination as the user scrolls
- Pull to refresh on the blog list
- Offline support using locally cached data
- Material Design 3 with light and dark themes
- Efficient image loading with Coil
- Responsive layout for phones and tablets

### Technical Features
- MVVM architecture with clean separation of concerns
- Room database caching with 7-day auto-expiry
- Retrofit-based networking with WordPress REST API
- Dependency injection using Hilt
- Jetpack Compose for declarative UI
- Navigation Compose for type-safe navigation
- Kotlin Coroutines and StateFlow for reactive state handling

---

## Architecture

The app follows Clean Architecture with three main layers:

```text
┌─────────────────────────────────────────────────────────┐
│                   Presentation Layer                    │
│  ┌────────────┐  ┌─────────────┐  ┌──────────────┐      │
│  │  Screens   │  │ ViewModels  │  │ UI Components│      │
│  └────────────┘  └─────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│                     Domain Layer                        │
│  ┌────────────┐  ┌─────────────┐                        │
│  │   Models   │  │  Use Cases  │                        │
│  └────────────┘  └─────────────┘                        │
└─────────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│                      Data Layer                         │
│  ┌────────────┐  ┌─────────────┐  ┌──────────────┐      │
│  │ Repository │  │  Room (DB)  │  │ Retrofit API │      │
│  └────────────┘  └─────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────┘
```

## Tech Stack

| Category | Technology |
|----------|-----------|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | MVVM + Clean Architecture |
| Dependency Injection | Hilt |
| Networking | Retrofit + OkHttp |
| Database | Room (SQLite) |
| Async | Kotlin Coroutines + Flow / StateFlow |
| Navigation | Navigation Compose |
| Image Loading | Coil |
| WebView | Accompanist WebView |
| JSON Parsing | Gson |

## Dependencies

```kotlin
// Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.7.5")

// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Room
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Hilt
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-compiler:2.48")
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

// Coil
implementation("io.coil-kt:coil-compose:2.5.0")

// WebView
implementation("com.google.accompanist:accompanist-webview:0.32.0")
```

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

### Installation

1. Clone the repository

```bash
git clone https://github.com/pratish444/Blog-Reader.git
cd Blog-Reader
```

2. Open in Android Studio

- Open Android Studio
- Select "Open an Existing Project"
- Choose the cloned directory

3. Sync Gradle

- Let Android Studio sync and download all dependencies

4. Run the app

- Connect a device or start an emulator
- Click Run or press Shift + F10

## Project Structure

```
app/src/main/java/com/example/blogreader/
├── data/
│   ├── local/
│   │   ├── BlogDatabase.kt          // Room database
│   │   ├── BlogDao.kt               // Database queries
│   │   └── BlogEntity.kt            // Database entity
│   ├── remote/
│   │   ├── BlogApiService.kt        // Retrofit API interface
│   │   └── BlogDto.kt               // API DTO models
│   └── repository/
│       └── BlogRepository.kt        // Repository implementation
├── domain/
│   └── model/
│       └── Blog.kt                  // Domain model
├── presentation/
│   ├── bloglist/
│   │   ├── BlogListScreen.kt        // Blog list UI
│   │   ├── BlogListViewModel.kt     // List screen ViewModel
│   │   └── BlogListState.kt         // State holder for list
│   ├── blogdetail/
│   │   ├── BlogDetailScreen.kt      // Detail UI
│   │   ├── BlogDetailViewModel.kt   // Detail ViewModel
│   │   └── BlogDetailState.kt       // State holder for detail
│   ├── components/
│   │   └── BlogCard.kt              // Reusable UI components
│   └── navigation/
│       └── Navigation.kt            // Navigation graph
├── di/
│   └── AppModule.kt                 // Hilt modules
├── ui/theme/
│   └── Theme.kt                     // Material 3 theme setup
├── BlogReaderApplication.kt         // Application class
└── MainActivity.kt                  // Entry activity
```

## Author

**Pratish Dwivedi**

- GitHub: [@pratish444](https://github.com/pratish444)
- Email: pratishk444@gmail.com

## Acknowledgments

- [Vrid Technology](https://blog.vrid.in) for the blog API
- [Android Jetpack](https://developer.android.com/jetpack) libraries
- [Material Design 3](https://m3.material.io/) guidelines
- The Android developer community

---

**Built with Kotlin and Jetpack Compose.**
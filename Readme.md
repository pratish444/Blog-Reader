# 📱 Blog Reader App

A modern Android application built with Jetpack Compose that fetches and displays blog posts from WordPress REST API. Features offline-first architecture, infinite scroll pagination, and beautiful Material Design 3 UI.



## 📸 Screenshots

### Light Theme
| Blog List | Blog Detail | Loading State |
|-----------|-------------|---------------|
| ![Blog List](screenshots/blog_list_light.png) | ![Blog Detail](screenshots/blog_detail_light.png) | ![Loading](screenshots/loading_light.png) |

### Dark Theme
| Blog List | Blog Detail | Offline Mode |
|-----------|-------------|--------------|
| ![Blog List Dark](screenshots/blog_list_dark.png) | ![Blog Detail Dark](screenshots/blog_detail_dark.png) | ![Offline](screenshots/offline_mode.png) |

### Features Demo
| Pagination | Pull to Refresh | Error State |
|------------|-----------------|-------------|
| ![Pagination](screenshots/pagination.gif) | ![Refresh](screenshots/pull_refresh.gif) | ![Error](screenshots/error_state.png) |

---

## ✨ Features

### Core Features
- ✅ **Blog List Screen** - Display list of blog posts with images, titles, and excerpts
- ✅ **Blog Detail Screen** - View full blog content in a styled WebView
- ✅ **Infinite Scroll** - Automatic pagination as user scrolls
- ✅ **Pull to Refresh** - Swipe down to refresh blog list
- ✅ **Offline Support** - Works without internet using cached data
- ✅ **Beautiful UI** - Material Design 3 with light/dark theme support
- ✅ **Image Loading** - Efficient image loading with Coil
- ✅ **Responsive Design** - Adapts to different screen sizes

### Technical Features
-  **MVVM Architecture** - Clean separation of concerns
- **Room Database** - SQLite caching with 7-day auto-expiry
-  **Retrofit** - RESTful API integration
-  **Hilt** - Dependency injection
-  **Jetpack Compose** - Modern declarative UI
-  **Navigation Component** - Type-safe navigation
-  **Kotlin Coroutines** - Asynchronous programming
-  **StateFlow** - Reactive state management

---

## 🏗️ Architecture

This app follows **Clean Architecture** principles with clear layer separation:

```
┌─────────────────────────────────────────────────────────┐
│                   Presentation Layer                     │
│  ┌────────────┐  ┌─────────────┐  ┌──────────────┐     │
│  │  Screens   │  │ ViewModels  │  │ UI Components│     │
│  └────────────┘  └─────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│                     Domain Layer                         │
│  ┌────────────┐  ┌─────────────┐                        │
│  │   Models   │  │  Use Cases  │                        │
│  └────────────┘  └─────────────┘                        │
└─────────────────────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────┐
│                      Data Layer                          │
│  ┌────────────┐  ┌─────────────┐  ┌──────────────┐     │
│  │ Repository │  │  Room (DB)  │  │ Retrofit API │     │
│  └────────────┘  └─────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────┘
```


## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose |
| **Architecture** | MVVM + Clean Architecture |
| **Dependency Injection** | Hilt |
| **Networking** | Retrofit + OkHttp |
| **Database** | Room (SQLite) |
| **Async** | Kotlin Coroutines + Flow |
| **Navigation** | Navigation Compose |
| **Image Loading** | Coil |
| **WebView** | Accompanist WebView |
| **JSON Parsing** | Gson |

---

## 📦 Dependencies

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

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 11 or higher
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/pratish444/Blog-Reader
cd blog-reader-app
```

2. **Open in Android Studio**
- Launch Android Studio
- Click on "Open an Existing Project"
- Navigate to the cloned directory and select it

3. **Sync Gradle**
- Android Studio will automatically sync Gradle
- Wait for dependencies to download

4. **Run the app**
- Connect an Android device or start an emulator
- Click the "Run" button or press `Shift + F10`

---
## 📂 Project Structure

```
app/src/main/java/com/example/blogreader/
├── data/
│   ├── local/
│   │   ├── BlogDatabase.kt          # Room database
│   │   ├── BlogDao.kt                # Database queries
│   │   └── BlogEntity.kt             # Database entity
│   ├── remote/
│   │   ├── BlogApiService.kt         # Retrofit API
│   │   └── BlogDto.kt                # API models
│   └── repository/
│       └── BlogRepository.kt         # Data repository
├── domain/
│   └── model/
│       └── Blog.kt                   # Domain model
├── presentation/
│   ├── bloglist/
│   │   ├── BlogListScreen.kt         # List UI
│   │   ├── BlogListViewModel.kt      # List logic
│   │   └── BlogListState.kt          # List state
│   ├── blogdetail/
│   │   ├── BlogDetailScreen.kt       # Detail UI
│   │   ├── BlogDetailViewModel.kt    # Detail logic
│   │   └── BlogDetailState.kt        # Detail state
│   ├── components/
│   │   └── BlogCard.kt               # Reusable components
│   └── navigation/
│       └── Navigation.kt             # App navigation
├── di/
│   └── AppModule.kt                  # Dependency injection
├── ui/theme/
│   └── Theme.kt                      # Material3 theme
├── BlogReaderApplication.kt          # Application class
└── MainActivity.kt                   # Entry point

---

## 👨‍💻 Author

**Pratish Dwivedi**
- GitHub: https://github.com/pratish444
- Email: pratishk444@gmail.com

---

## 🙏 Acknowledgments

- [Vrid Technology](https://blog.vrid.in) for the blog API
- [Android Jetpack](https://developer.android.com/jetpack) for amazing libraries
- [Material Design 3](https://m3.material.io/) for design guidelines
- The Android developer community

---
**Built with ❤️ using Kotlin & Jetpack Compose**
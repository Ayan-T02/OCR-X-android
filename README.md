# OCR-X Android

OCR-X is a Kotlin Android application that uses the rear camera to recognize text from the physical world in real time. It is built as a professional Android OCR assignment project with CameraX, Google ML Kit, Jetpack Compose, Navigation Compose, and a Room database foundation.

## Features

- Real-time rear camera preview using CameraX
- On-device OCR using Google ML Kit Text Recognition
- Smooth live OCR result panel with copy and clear actions
- OCR result stabilization to reduce fast flickering text updates
- Session-based OCR capture
- Saved OCR strings with timestamps
- Review current and past scan sessions
- Animated splash screen with branded OCR-X boot sequence
- Custom OCR-X adaptive launcher icon
- Animated Material 3 home screen with scan session flow
- Navigation from home screen to live camera scanner
- Room database architecture for session and OCR text persistence
- Tested on Samsung Galaxy M20

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- CameraX
- Google ML Kit Text Recognition
- Navigation Compose
- Room Database
- Gradle Kotlin DSL
- Git and GitHub

## Architecture

The project is organized into focused packages:

```text
camera/
  CameraPermission.kt
  CameraPreview.kt
  CameraScreen.kt

mlkit/
  ImageAnalyzer.kt
  OCRProcessor.kt
  OCRResult.kt
  TextRecognizerManager.kt

data/
  dao/
  database/
  entity/
  repository/

navigation/
  AppNavigation.kt

ui/
  components/
  home/
  splash/
  theme/
```

## How It Works

1. The app starts with an animated OCR-X splash screen.
2. The home screen lets the user start a new scan session.
3. CameraX opens the rear camera and streams frames for analysis.
4. ML Kit processes camera frames on-device and extracts text.
5. The detected text is normalized, stabilized, and displayed in a polished Material 3 result card.
6. Stable OCR strings are automatically saved into the active Room session.
7. Users can review captured text later with timestamps from the session list.

## Build Instructions

Open the project in Android Studio and run:

```bash
./gradlew :app:assembleDebug
```

The debug APK is generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Current Status

OCR-X includes a working real-time OCR camera flow, polished animated screens, session-based local persistence, and review of captured OCR text with timestamps.

## Future Improvements

- Search across scanned text
- PDF and TXT export
- Multi-language OCR options
- Cloud backup

## Author

Ayan Kaushik

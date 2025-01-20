# RSS Reader Application

This is a showcase application designed for reading RSS feeds. With this app, you can manage RSS
feeds from various sources, switch between them easily, and stay updated with new articles. You can
also mark articles as favorites for quick access.

---

## Test URLs

Here are some sample RSS feeds you can use to test the application:

- [ABC News - US Headlines](https://abcnews.go.com/abcnews/usheadlines)
- [NBC News - World](https://feeds.nbcnews.com/nbcnews/public/world)
- [NPR - News](https://feeds.npr.org/1003/rss.xml)
- [BuzzFeed - Politics](https://www.buzzfeed.com/politics.xml)
- Explore more options
  from [this list](https://about.fb.com/wp-content/uploads/2016/05/rss-urls-1.pdf).

---

## Features

- **Add and Manage Feeds**: Add RSS feeds from multiple sources and switch between them
  effortlessly.
- **Unread Articles**: Track unread articles and stay informed.
- **Notifications**: Receive notifications when new articles arrive, with background updates running
  hourly.
- **Favorites**: Mark articles as favorites for easy access later.
- **Pull to Refresh**: Easily fetch the latest data by pulling down on the screen.
- **Offline Mode**: A network manager ensures smooth functionality even when offline.
- **Landscape Mode**: Fully functional landscape orientation for better usability.

---

## Tech Stack

The application is built using modern tools and frameworks to ensure performance and
maintainability:

### Core Technologies

- **Kotlin Compose**: For building the user interface.
- **Material3**: For implementing modern design principles.

### Architecture

- **MVVM** (Model-View-ViewModel): Ensures clear separation of concerns, making the app modular and
  testable.
- **Clean Architecture**: Provides scalability and maintains a clean codebase.

### Key Libraries

- **Koin**: For dependency injection, simplifying the setup and management of dependencies.
- **Ktor**: Handles network operations, including fetching and processing RSS feed data.
- **Datastore**: For lightweight and efficient key-value storage.
- **Room**: Manages structured, relational data with SQLite.
- **WorkManager**: Runs background tasks, such as fetching new articles and sending notifications
  every hour.
- **Coil (Coil3)**: For smooth image loading and caching.
- **Compose Destinations**: Simplifies navigation within the app.

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/jskako/rss-feed.git
   ```

2. Open the project in [Android Studio](https://developer.android.com/studio).

3. Build and run the application on an emulator or physical device.

4. Add your favorite RSS feeds and start exploring.

---

## Contribution

Contributions are welcome. If you have ideas to improve the app or find a bug, feel free to:

- Fork the repository.
- Create a new branch for your feature or bug fix.
- Submit a pull request.

---

## License

This project is licensed under the [Apache License](LICENSE).

---

## Contact

If you have any questions, feel free to reach out:

- Email: jskako@gmail.com
- GitHub: [jskako](https://github.com/jskako)

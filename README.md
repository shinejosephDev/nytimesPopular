# NYTimesMostPopular

 An android app built using Kotlin that consumes [Ney York Times API](https://developer.nytimes.com/get-started/3) to display current popular articles.
It has been built with clean architecture using components like retrofit, hilt, coroutines, flow, viewbinding and navigation component.
### Prerequisites

#### Ney York Times API Key.

The key needs to be added to the `local.properties` file, so that it's read as a build config value. 

```yaml
API_KEY="*******************"
```

## The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Hilt.
3. **domain**: It contains dto classes and repositories.
4. **presentation**: View classes along with their corresponding Presenters.
5. **utils**: Utility classes.

### Libraries.

- [Hilt](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
  -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
     - [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

  
- [Retrofit](https://square.github.io/retrofit/) - To make network request.
-  Tests
    - [JUnit5](https://junit.org/junit5/)
    - [Mockito](https://github.com/mockito/mockito)
    - [Turbine](https://github.com/cashapp/turbine)


### Screenshots

I added some screenshots in the `screenshots` folder, in the root directory of the project.


<p align="center">
    <img src="/screenshots/list.png" width="320"/>
    <img src="/screenshots/detail.png" width="320"/>
</p>
<br>

<p align="center">
    <img src="/screenshots/device-2023-04-27-125457.webm"/>
</p>
<br>

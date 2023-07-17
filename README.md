# Dog's bredds app

This is a simple app with 3 different screens. First screen is a list of breeds you can click on them and see photos of breeds.
you are able to pick your favorite breeds and find them in a separate screen in the application.
The architecture of the app is MVVM. with Model layer, viewmodel and view.

# How to run the app

the project config is Gradle 8 JVM 17.
So make sure that you have both of them on your machine the simply you can run the project from the
android studio
or if you do not access to android studio you can simply go to the root of the project with terminal
and run this command
"gradlew assembleDebug"
you going to find the apk in CryptoPrices/app/build/outputs/apk/

if you need the prebuild version of the app you can find it in DogsBreeds/app/release

# Libraries that used in the project

* Compose: for the Ui of the Application
* Dagger Hilt: for dependency Injection. it is easy to use instead of dagger and other libraries
* Retrofit: used for remote restApi. it is great for rest api and also works good with coroutine.
* OkHttp: for internet connection as client for retrofit.
* Coroutine: used int for thread handle. it is easy to understand and use instead of rxjava or  
  other ways.
* Navigation Component: for designing single activity app and also navigation of application and  
  pass parameters screens.
* architecture components
    * ViewModel
    * Lifecycle
* Turbine: for unit testing flows
* Truth: for unit test assertion
* Junit: and Mockito for unit testing
* Glide: for lazy loading the Images.
* CI: github actions
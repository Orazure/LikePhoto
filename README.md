# LikPhoto - A simple photo gallery app for Android devices build with Kotlin
Images are provided by [Unsplash](https://unsplash.com/)
## Features

- [x] Display photos  
- [x] Display photo details
  - The details screen contains the photo's description, the number of like and a button to like or unlike the photo
    - when i click on the like button, the photo is added to the liked photos
- [x] Search photos by keyword
  - i used directly the search endpoint of the API and display it on the random photos screen
- [x] Display liked photos
  - when i click on the unlike button, the photo is removed from the liked photos

The endpoints used are :
- [Get a list of photos](https://unsplash.com/documentation#list-photos)
- [Like a photo](https://unsplash.com/documentation#like-a-photo)
- [Unlike a photo](https://unsplash.com/documentation#unlike-a-photo)
- [Search photos](https://unsplash.com/documentation#search-photos)


## Architecture

- MVVM
- Repository pattern


## Libraries

- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Glide]() - An image loading and caching library for Android focused on smooth scrolling
- [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For managing background threads with simplified code and reducing needs for callbacks
- [Xplosion](https://github.com/BanDev/Xplosion) - A simple library for creating explosion animations
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines

## Screenshots

![home_screen.png](docs/home_screen.png){:height="50%" width="50%"}
This is the home screen of the app.
The user can :
- Search photos by keyword
- Display liked photos by clicking on the `See my fav photos` button
- Display photos by clicking on the `See photos` button

![ramdom_pictures.png](docs/ramdom_pictures.png){:height="50%" width="50%"}
This is the screen where the user can see the photos.

![details_photo.png](docs/details_photo.png){:height="50%" width="50%"}
This is the screen where the user can see the details of a photo.
The screen contains the photo's description, the number of like and a button to like or unlike the photo.

![fav_picture.png](docs/fav_picture.png){:height="50%" width="50%"}
This is the screen where the user can see the liked photos.




## Issues reporting

I have encountered errors :
- when i try to like or get a photos, i get a 403 error => i reached the limit of 50 likes per hour
- Sometimes i have to restart android studio to make the app work again (the AVD is not working)
- 

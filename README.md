# :camera_flash: LikPhoto - A simple photo gallery app for Android devices build with Kotlin
> Images are provided by [Unsplash](https://unsplash.com/)
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

| Plugin | Link |
| ------ |------|
| Retrofit | https://square.github.io/retrofit/ |
| Glide | https://bumptech.github.io/glide/ |
| Room | https://developer.android.com/topic/libraries/architecture/room |
| Kotlin Coroutines | https://kotlinlang.org/docs/reference/coroutines-overview.html |
| Xplosion | https://github.com/BanDev/Xplosion |
| Coil | https://coil-kt.github.io/coil/ |


## Screenshots

![home_screen.png](docs/home_screen.png)


This is the home screen of the app.
The user can :
- Search photos by keyword
- Display liked photos by clicking on the `See my fav photos` button
- Display photos by clicking on the `See photos` button

![ramdom_pictures.png](docs/ramdom_pictures.png)


This is the screen where the user can see the photos.

![details_photo.png](docs/details_photo.png)


This is the screen where the user can see the details of a photo.
The screen contains the photo's description, the number of like and a button to like or unlike the photo.

![fav_picture.png](docs/fav_picture.png)


This is the screen where the user can see the liked photos.




## :warning: Issues reporting

I have encountered errors :
- when i try to like or get a photos, i get a 403 error => i reached the limit of 50 likes per hour
- Sometimes i have to restart android studio to make the app work again (the AVD is not working)
- i modified my room database to add new columns but i have an error => i have to change the version of the database to make it work (version =2)


## Authors

* **Alexandre LORNE** - *Initial work*

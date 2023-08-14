
## 🛠 Built With

- [Kotlin](https://kotlinlang.org)
- [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAiA25v_BRBNEiwAZb4-ZRLrSzIFlpm0NDTFGSuapyosjuVKi0AVLXGgVqSwqe46gejCg31LvRoCAwIQAvD_BwE&gclsrc=aw.ds)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [View Binding](https://developer.android.com/topic/libraries/view-binding)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
    * [Room] (https://developer.android.com/jetpack/androidx/releases/room)
- [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata?hl=id)
- [Koin](https://insert-koin.io/)
- [Retrofit](https://square.github.io/retrofit/)
- [3rd Party Library] :
  - [QRCode Scanner](https://github.com/yuriy-budiyev/code-scanner)
  - [Image Picker](https://github.com/ParkSangGwon/TedImagePicker)
  - [PermissionX](https://github.com/guolindev/PermissionX)


## 🗼 Architecture

[![MVVM](https://gcdnb.pbrd.co/images/9q6VpRVAVdLL.png?o=1 "MVVM")](https://gcdnb.pbrd.co/images/9q6VpRVAVdLL.png?o=1 "MVVM")

- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model–view–viewmodel) (MVVM) pattern helps to completely separate the business and presentation logic from the UI
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) For small projects, MVVM is sufficient, but as our codebase grows more extensive, the ViewModels begin to bloat. It becomes challenging to separate responsibilities. For that case, MVVM with Clean Architecture is a suitable choice.

- [Modularization](https://developer.android.com/topic/modularization) enables separate functionality into independent, interchangeable modules. But in this project I only make separate module for core, data and domain module, while the feature module is not due to the small project size/feature

##  Data Source

I used dummy data in MachineRepositoryImpl as the first source of data, and then the data will be saved to local database using Room.

And everything will be queried by using Room. 

## Data Flow 



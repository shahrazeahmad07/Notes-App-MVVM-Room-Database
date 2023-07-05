# Notes-App-MVVM-Room-Database
It's a simple note taking application. Notes are saved in Room Database, i.e., saved in mobile memory and once uninstalled, all notes will be gone.

## Features
* Add Note
* View Note
* Edit Note
* Delete Note

## Details
This project implements android's most recommended architecture MVVM -> Model View ViewModel. The database used in this application is Room Database. So first we have to setup the room database by simply creating Model class, DAO interface and Database class. then we have to create a repository class that is sort of a wrapper around the DAO interface. In Repository class we write code of fetching data. Then after this we make ViewModel class. this class does all the data fetching by using the repository class. the data is fetched here in this class using viewModelScope. then the UI classes (the Activity classes or fragment classes) show the data fetched by the ViewModel class by using its object. This was the whole explanation of the MVVM Architecture.

The main and best advantage of using MVVM architecture is the separation of concerns. When you are changing the view of the application, you are provided by only view related code because all the data is presend in repository class. This is one example, but yeah you got the Idea. You can easily implement change in the view without harming the data and vice versa.
This is quite useful when working with big applications. And thus because of this, MVVM architecture helps to build scalable application.

## Demo Video

https://github.com/shahrazeahmad07/Notes-App-MVVM-Room-Database/assets/68849516/515d090f-5b8c-4e33-b5c6-2fb8d54e8d41

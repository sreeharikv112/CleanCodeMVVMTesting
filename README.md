# Clean Code Architecture +  MVVM +  Testing

Source code of [Medium Article](https://medium.com/@sreeharikv112/android-unit-testing-clean-code-architecture-with-mvvm-73eb2992cab7) which describes Android Unit Testing Clean Code Architecture with MVVM.


## Architecture followed

![ArchDiagram1](https://user-images.githubusercontent.com/39777674/71248284-78d00800-2340-11ea-9077-080e29a8c918.png)

<br>

## The article explains 


- Ways to include dependencies using Koin 
- Making usefull data flow using above architecture 
- Creating unit test case with dependencies
- Override the default dependencies 
- Mock required dependency and create required test cases 

<br>

## Landing Screen with data from SWAPI API
<p align="center">
<img width="142" alt="screen1" src="https://user-images.githubusercontent.com/39777674/71302211-774d2100-23ce-11ea-8e7b-efe51e7a1d4d.png">
</p>
<br>

## Main libraries included

- Coroutines https://developer.android.com/kotlin/coroutines

- Retrofit https://square.github.io/retrofit/

- MockWebServer https://github.com/square/okhttp/tree/master/mockwebserver

- Koin (💉Dipendency Injection) https://insert-koin.io/

- Kotlin (https://developer.android.com/kotlin)

## Uses Swapi API for explaining required data flow 

Uses https://swapi.co/  API as network end points. 

The complete API documentation detail can be found over https://swapi.co/documentation

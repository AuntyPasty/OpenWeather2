# OpenWeather2
V2 of Open weather test app

This is a Weather app using the Open Weather API.

The basic functionality of the app is to enter a search string for a city and pass this string to the Open Weather backend.
The Open Weather Backend will return a list of cities matching the search. 
However the list of cities returned do not seem to always match the search string. However for the purposes of this app
this doesn't really matter.
The list of cities is then displayed in a list on the screen.
Clicking a particular city takes the city id and calls the Open Weather API to fetch an hourly forecast of the specified city.
The hourly forecast is then shown in another list in another activity.

It has been rewritten to use Retrofit, MVVM and Coroutines. Hilt was recently added as a DI solution.

The original app was written in Java, MVP and RxJava. It also used VolleyAdapter instead of Retrofit.
It was then migrated to Kotlin with some code tidy-ups.

With version 2 of the app, the business logic was re-architected to use MVVM but the UI has only undergone minor updates
so currently a bit old fashioned.

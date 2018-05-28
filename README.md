# AI Weather Android App

Sample Project to show you, how you can use Clean Architecture + MVP with Kotlin, Room, RxJava2, Retrofit2, Dagger2, etc. in your awesome android app! This app contains base boilerplate code that I usually use in my every day life.
To build this project, you will need:
 - an Open Weather Map API Key. You can get it [here](https://openweathermap.org/) and put it in your `gradle.properties` file with a link from your build.gradle file;
 - a Dialog Flow API Key. You can get it [here](https://console.dialogflow.com).

Libraries and tools included:

- Support libraries
- RecyclerViews
- Kotlin
- Room DB
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)  
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [Stetho](http://facebook.github.io/stetho/)
- [Permissions Retriever](https://github.com/AndroidPirates/PermissionRetriever-Kt)
- [Timber](https://github.com/JakeWharton/timber)
- [Chuck](https://github.com/jgilfelt/chuck)
- [Glide](https://github.com/bumptech/glide)
- Functional tests with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Robolectric](http://robolectric.org/)
- [Mockito](http://mockito.org/)
- [Checkstyle](http://checkstyle.sourceforge.net/), [PMD](https://pmd.github.io/) and [Findbugs](http://findbugs.sourceforge.net/) for code analysis

## Requirements

- Kotlin
- JDK 1.8
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android O [(API 26)](http://developer.android.com/tools/revisions/platforms.html)
- Latest Android SDK Tools


## Architecture

This project follows Uncle Bob's Clean Architecture (https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) and MVP Android architecture approach (https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter). Read more about it [here](http://antonioleiva.com/mvp-android/). 

**NOTE:** Some say, that you just can't use boilerplate code to start a new project... Well, it's not true. I like programming and I don't like to write a lot of boilerplate code (same as you, I suppose). On the web, for instance, they have Twitter Bootstrap for front-end, and all the people around use it just because it's useful and easy to use, so you can concentrate on some other goals. 

This project uses as a base [Android Pirates' team best practices and all the experience we aggregated](https://github.com/AndroidPirates) for the last few years. My team and I found and created this codebase empirically. For now, this is the best base you could have for your android project (IMO).

And, of course, I'd like to mention, that I'm using the [Permissions Retriever library](https://github.com/AndroidPirates/PermissionRetriever-Kt) here. This is the easiest tool one could use for permission requests. This tool was born in the Android Pirates Lab behind the closed doors. Then we decided to make it public and published it on Github. So go ahead and try it yourself! 

## License

```
    Copyright 2016 fakeGEEK (Sergey Antonov) http://fakegeek.ru, AndroidPirates http://androidpirates.ru

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
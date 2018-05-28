package ru.androidpirates.aiweather.injection.main

import dagger.Subcomponent
import ru.androidpirates.aiweather.injection.modules.ActivityModule
import ru.androidpirates.aiweather.injection.scope.ConfigPersistent

@ConfigPersistent
@Subcomponent(modules = [(ActivityModule::class)])
interface MainComponent {

    interface Creator {
        fun createMainComponent() : MainComponent
    }
}
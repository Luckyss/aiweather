package ru.androidpirates.aiweather.injection.components

import ru.androidpirates.aiweather.injection.scope.ConfigPersistent

@ConfigPersistent
//@Subcomponent(modules = [BaseDataModule::class])
interface BaseComponent {

    interface Creator {

        fun createBaseComponent(): BaseComponent
    }
}
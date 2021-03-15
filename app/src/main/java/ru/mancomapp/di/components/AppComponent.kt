package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.AppModule
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    // TODO: add injects
}
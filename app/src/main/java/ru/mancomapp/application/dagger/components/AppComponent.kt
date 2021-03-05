package ru.mancomapp.application.dagger.components

import dagger.Component
import ru.mancomapp.application.dagger.modules.AppModule
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    // TODO: add injects
}
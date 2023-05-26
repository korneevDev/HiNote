package github.mik0war.hinote.di

import dagger.Binds
import dagger.Module
import github.mik0war.hinote.domain.ResourceManager

@Module
abstract class BaseModule {
    @Binds
    abstract fun provideBaseResourceManager(resourceManager: ResourceManager.Base): ResourceManager
}
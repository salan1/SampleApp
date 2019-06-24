package com.example.myapplication.di

import com.example.myapplication.di.scope.ActivityScope
import com.example.myapplication.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Binds all sub-components within the app.
 */
@Module
internal abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}
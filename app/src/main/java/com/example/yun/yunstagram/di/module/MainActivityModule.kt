package com.example.yun.yunstagram.di.module

import com.example.yun.yunstagram.di.scope.PerFragment
import com.example.yun.yunstagram.views.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun provideHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    internal abstract fun provideProfileFragment(): ProfileFragment
}
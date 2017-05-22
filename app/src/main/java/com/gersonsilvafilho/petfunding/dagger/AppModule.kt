package com.gersonsilvafilho.petfunding.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



/**
 * Created by GersonSilva on 5/19/17.
 */
@Module
class AppModule (val mApplication:Application){

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mApplication
    }
}
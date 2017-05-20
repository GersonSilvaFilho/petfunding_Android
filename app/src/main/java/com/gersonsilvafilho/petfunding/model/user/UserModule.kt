package com.gersonsilvafilho.petfunding.model.user

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/19/17.
 */
@Module
class UserModule{

    @Provides
    @Singleton
    fun module():UserRepository
    {
        return UserFirebaseRepository()
    }

}
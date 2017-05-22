package com.gersonsilvafilho.petfunding.model.user

import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/19/17.
 */
@Module
class UserModule(val mUserRepository:UserRepository){

    @Provides
    @UserScope
    fun module():UserRepository
    {
        return mUserRepository
    }
}
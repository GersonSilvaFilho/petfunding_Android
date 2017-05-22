package com.gersonsilvafilho.petfunding.likeList

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/22/17.
 */
@Module
class LikeListModule (val likeListContract: LikeListContract.View){
    @Provides
    fun provideListListPresenter(userRepository: UserRepository): LikeListContract.Presenter{
        return LikeListPresenter(likeListContract,userRepository)
    }
}
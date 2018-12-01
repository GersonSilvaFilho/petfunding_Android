package com.gersonsilvafilho.petfunding.likelist

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by GersonSilva on 5/22/17.
 */
@Module
class LikeListModule (val likeListContract: LikeListContract.View){
    @Provides
    fun provideListListPresenter(userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity): LikeListContract.Presenter{
        return LikeListPresenter(likeListContract,userRepository, petRepository, matchReposity)
    }
}
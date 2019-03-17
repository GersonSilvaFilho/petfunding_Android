package com.gersonsilvafilho.petfunding.mypets

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by GersonSilva on 5/22/17.
 */
@Module
class MyPetsActivityModule {
    @Provides
    fun provideMyPetsPresenter(
        myPetsActivity: MyPetsActivity,
        userRepository: UserRepository,
        petRepository: PetRepository,
        matchReposity: MatchReposity
    ): MyPetsContract.Presenter {
        return MyPetsPresenter(myPetsActivity, userRepository, petRepository, matchReposity)
    }
}

@Module
abstract class MyPetsActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MyPetsActivityModule::class])
    abstract fun contributeVoucherActivity(): MyPetsActivity
}
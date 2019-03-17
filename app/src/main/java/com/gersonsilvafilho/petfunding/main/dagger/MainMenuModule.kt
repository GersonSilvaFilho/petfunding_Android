package com.gersonsilvafilho.petfunding.main.dagger

import com.gersonsilvafilho.petfunding.main.ui.MainMenuActivity
import com.gersonsilvafilho.petfunding.main.ui.MainMenuContract
import com.gersonsilvafilho.petfunding.main.ui.MainMenuPresenter
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
object MainMenuModule {

    @JvmStatic
    @Provides
    fun provideMainMenuPresenter(
        chatActivity: MainMenuActivity,
        userRepository: UserRepository,
        petRepository: PetRepository,
        matchReposity: MatchReposity
    ): MainMenuContract.Presenter {
        return MainMenuPresenter(chatActivity, userRepository, petRepository, matchReposity)
    }
}

@Module
abstract class MainMenuBuilderModule {
    @ContributesAndroidInjector(modules = [MainMenuModule::class])
    abstract fun contributeVoucherActivity(): MainMenuActivity
}
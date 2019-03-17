package com.gersonsilvafilho.petfunding.likelist

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
object LikeListActivityModule {
    @JvmStatic
    @Provides
    fun provideListListPresenter(
        likeListView: LikeListActivity,
        userRepository: UserRepository,
        petRepository: PetRepository,
        matchReposity: MatchReposity
    ): LikeListContract.Presenter {
        return LikeListPresenter(likeListView, userRepository, petRepository, matchReposity)
    }
}

@Module
abstract class LikeListActivityBuilderModule {
    @ContributesAndroidInjector(modules = [LikeListActivityModule::class])
    abstract fun contributeVoucherActivity(): LikeListActivity
}
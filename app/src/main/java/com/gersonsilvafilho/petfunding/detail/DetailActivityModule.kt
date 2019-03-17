package com.gersonsilvafilho.petfunding.detail

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
object DetailActivityModule {
    @JvmStatic
    @Provides
    fun provideMainChatPresenter(
        chatActivity: DetailActivity,
        userRepository: UserRepository
    ): DetailContract.Presenter = DetailPresenter(chatActivity, userRepository)

}

@Module
abstract class DetailActivityBuilderModule {
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun contributeVoucherActivity(): DetailActivity
}
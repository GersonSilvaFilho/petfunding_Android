package com.gersonsilvafilho.petfunding.chat

import com.gersonsilvafilho.petfunding.addpet.AddPetActivity
import com.gersonsilvafilho.petfunding.addpet.AddPetContract
import com.gersonsilvafilho.petfunding.addpet.AddPetPresenter
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
object AddPetActivityModule {
    @JvmStatic
    @Provides
    fun provideMainChatPresenter(addPetActivity: AddPetActivity, userRepository: UserRepository, petRepository: PetRepository): AddPetContract.Presenter {
            return AddPetPresenter(addPetActivity, petRepository, userRepository)
    }
}

@Module
abstract class AddPetActivityBuilderModule {
    @ContributesAndroidInjector(modules = [AddPetActivityModule::class])
    abstract fun contributeVoucherActivity(): AddPetActivity
}
package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserModule
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import javax.inject.Inject


/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter: MainMenuContract.Presenter
{
    lateinit var mMainMenuView: MainMenuContract.View

    @Inject
    lateinit var mUserRepository: UserRepository

    @Inject
    lateinit var mPetRepository: PetRepository

    override fun initView(view: MainMenuContract.View) {
        initDagger()
        mMainMenuView = view
    }

    private fun initDagger() {
        DaggerMainMenuComponent
                .builder()
                .userModule(UserModule())
                .build()
                .inject(this)
    }

    override fun userMatchedPet(pet: Pet) {
        mUserRepository.addMatch(pet.uid).toObservable().subscribe {a -> mMainMenuView.showItsMatchDialog(pet, a)}
    }

    override fun userLogout() {
        mUserRepository.userLogout()
    }

    override fun loadPets() {
        mPetRepository.getPets().subscribe { p ->  mMainMenuView.updateCardAdapter(p)}
    }

}
package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository


/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter constructor(var mPetRepository: PetRepository, var mUserRepository: UserRepository, var mMainMenuView: MainMenuContract.View) : MainMenuContract.Presenter
{
    override fun userLogout()
    {
        mUserRepository.userLogout()
    }

    override fun loadPets()
    {
        mPetRepository.getPets().subscribe { p ->  mMainMenuView.updateCardAdapter(p)}
    }

}
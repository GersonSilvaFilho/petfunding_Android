package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.User
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter: MainMenuContract.Presenter
{
    var mMainMenuView: MainMenuContract.View

    var mUserRepository: UserRepository

    var mPetRepository: PetRepository
    var mMatchRepository: MatchReposity

    constructor(view: MainMenuContract.View, userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity) {
        mMainMenuView = view
        mUserRepository = userRepository
        mPetRepository = petRepository
        mMatchRepository = matchReposity
        mUserRepository.currentUserChanged().subscribe ({ user: User -> setUserProfile() }, {})
    }


    override fun userMatchedPet(pet: Pet) {

        mMatchRepository.checkIfMatchExists(pet.uid, mUserRepository.getCurrentUserId()).subscribe { exists, t2 ->
            if(exists)
            {
                mMainMenuView.showItsMatchDialog(pet)
            }
            else
            {
                mMatchRepository.addMatch(pet.uid, mUserRepository.getCurrentUserId())
                        .toObservable().subscribe {
                            a -> mMainMenuView.showItsMatchDialog(pet)
                            mUserRepository.addMatchToUser(a).subscribe()

                }
            }
        }
    }

    override fun userUnmatchedPet(pet: Pet)
    {
        mUserRepository.addUnmatch(pet.uid).subscribe()
    }

    override fun userLogout() {
        mUserRepository.userLogout()
    }

    override fun loadPets() {
        mMainMenuView.showRippleWaiting()
        mPetRepository.getPets().delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { p ->
            mMainMenuView.updateCardAdapter(p)
            mMainMenuView.hideRippleWaiting()
        }
    }

    override fun setUserProfile()
    {
        mMainMenuView.setDrawerUserInformation(mUserRepository.getCurrentUser())
    }

}
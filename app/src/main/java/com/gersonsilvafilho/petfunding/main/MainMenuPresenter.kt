package com.gersonsilvafilho.petfunding.main

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.User
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.util.monthsSinceNow
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

    var filteredTypes:List<String> = ArrayList()
    var filteredSex:List<String> = ArrayList()
    var filteredSize:List<String> = ArrayList()
    var filteredCondition:List<String> = ArrayList()
    var filteredLike:List<String> = ArrayList()
    var filteredAge:List<String> = ArrayList()

    constructor(view: MainMenuContract.View, userRepository: UserRepository, petRepository: PetRepository, matchReposity: MatchReposity) {
        mMainMenuView = view
        mUserRepository = userRepository
        mPetRepository = petRepository
        mMatchRepository = matchReposity

        setupUserRepositoryObservers()
        setupViewObservers()
    }

    private fun setupUserRepositoryObservers()
    {
        mUserRepository.currentUserChanged().subscribe ({ user: User -> setUserProfile() }, {})
    }

    private fun setupViewObservers()
    {
        mMainMenuView.filterTypeChanges().subscribe { list -> filteredTypes = list }
        mMainMenuView.filterSexChanges().subscribe { list -> filteredSex = list }
        mMainMenuView.filterSizeChanges().subscribe { list -> filteredSize = list }
        mMainMenuView.filterConditionChanges().subscribe { list -> filteredCondition = list }
        mMainMenuView.filterLikeChanges().subscribe { list -> filteredLike = list }
        mMainMenuView.filterAgeChanges().subscribe { list -> filteredAge = list }
        mMainMenuView.applyButtonClicked().subscribe ({
            loadPets()
            mMainMenuView.hideFilterView()
        }, {})
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
        mPetRepository.getPets().delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { petsList ->
                    val filteredPets = petsList
                            .filter { pet -> filteredTypes.isEmpty() || filteredTypes.contains(pet.type)}
                            .filter { pet -> filteredSex.isEmpty() || filteredSex.contains(pet.sex) }
                            .filter { pet -> filteredSize.isEmpty() || filteredSize.contains(pet.size) }
                            .filter { pet -> filteredCondition.isEmpty() ||
                                        ((filteredCondition.contains("Vacinado") && pet.vaccinated) ||
                                                (filteredCondition.contains("Castrado") && pet.castrated) ||
                                                (filteredCondition.contains("Desverminado") && pet.dewormed))
                            }
                            .filter { pet -> filteredLike.isEmpty() ||
                                    ((pet.likeChildren && filteredLike.contains("Crianças") )||
                                            (pet.likeAnimals && filteredLike.contains("Outros Animais")) ||
                                                    (pet.likeElders && filteredLike.contains("Idosos"))) }
                            .filter { pet -> filteredAge.isEmpty() ||
                                    ((filteredAge.contains("Filhote") && pet.birthDate.monthsSinceNow() < 12)||
                                            (filteredAge.contains("Adulto") && pet.birthDate.monthsSinceNow() >= 12 && pet.birthDate.monthsSinceNow() < 96) ||
                                            (filteredAge.contains("Idoso") && pet.birthDate.monthsSinceNow() >= 96)) }
            mMainMenuView.updateCardAdapter(filteredPets)
            mMainMenuView.hideRippleWaiting()
        }
    }

    override fun setUserProfile()
    {
        mMainMenuView.setDrawerUserInformation(mUserRepository.getCurrentUser())
    }

}
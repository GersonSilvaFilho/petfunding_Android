package com.gersonsilvafilho.petfunding.main.ui

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.util.monthsSinceNow
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


/**
 * Created by GersonSilva on 3/21/17.
 */
class MainMenuPresenter(
    private val view: MainMenuContract.View,
    private val userRepository: UserRepository,
    private val petRepository: PetRepository,
    private val matchReposity: MatchReposity
) : MainMenuContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            userRepository.currentUserChanged()
                .subscribe { setUserProfile() }
        )

        compositeDisposable.add(
            view.applyButtonClicked()
                .subscribe {
                    loadPets()
                    view.hideFilterView()
                }
        )

        compositeDisposable.add(
            userRepository.userStatus()
                .filter { !it }
                .subscribe {
                    view.startSplashActivity()
                }
        )
    }


    override fun userMatchedPet(pet: Pet) {
        compositeDisposable.add(
            matchReposity.checkIfMatchExists(pet.uid, userRepository.getCurrentUserId())
                .subscribe { exists ->
                    if (exists) {
                        view.showItsMatchDialog(pet)
                    } else {
                        matchReposity.addMatch(pet.uid, userRepository.getCurrentUserId())
                            .toObservable().subscribe { a ->
                                view.showItsMatchDialog(pet)
                                userRepository.addMatchToUser(a).subscribe()

                            }
                    }
                }
        )
    }

    override fun userUnmatchedPet(pet: Pet) {
        compositeDisposable.add(userRepository.addUnmatch(pet.uid).subscribe())
    }

    override fun userLogout() {
        userRepository.userLogout()
    }

    override fun loadPets() {
        view.showRippleWaiting()
        compositeDisposable.add(
            petRepository.getPets()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { petsList ->
                    val filteredPets = petsList
                        .filter { pet -> view.filterTypeList().isEmpty() || view.filterTypeList().contains(pet.type) }
                        .filter { pet -> view.filterSexList().isEmpty() || view.filterSexList().contains(pet.sex) }
                        .filter { pet -> view.filterSizeList().isEmpty() || view.filterSizeList().contains(pet.size) }
                        .filter { pet ->
                            view.filterConditionList().isEmpty() ||
                                    ((view.filterConditionList().contains("Vacinado") && pet.vaccinated) ||
                                            (view.filterConditionList().contains("Castrado") && pet.castrated) ||
                                            (view.filterConditionList().contains("Desverminado") && pet.dewormed))
                        }
                        .filter { pet ->
                            view.filterLikeList().isEmpty() ||
                                    ((pet.likeChildren && view.filterLikeList().contains("Crianças")) ||
                                            (pet.likeAnimals && view.filterLikeList().contains("Outros Animais")) ||
                                            (pet.likeElders && view.filterLikeList().contains("Idosos")))
                        }
                        .filter { pet ->
                            view.filterAgeList().isEmpty() ||
                                    ((view.filterAgeList().contains("Filhote") && pet.birthDate.monthsSinceNow() < 12) ||
                                            (view.filterAgeList().contains("Adulto") && pet.birthDate.monthsSinceNow() >= 12 && pet.birthDate.monthsSinceNow() < 96) ||
                                            (view.filterAgeList().contains("Idoso") && pet.birthDate.monthsSinceNow() >= 96))
                        }
                    view.updateCardAdapter(filteredPets)
                    view.hideRippleWaiting()
                }
        )
    }

    override fun setUserProfile() {
        view.setDrawerUserInformation(userRepository.getCurrentUser())
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}
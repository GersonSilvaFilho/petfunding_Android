package com.gersonsilvafilho.petfunding.main.ui

import android.util.Log
import com.gersonsilvafilho.petfunding.filter.model.Filter
import com.gersonsilvafilho.petfunding.filter.model.FilterList
import com.gersonsilvafilho.petfunding.filter.model.containsString
import com.gersonsilvafilho.petfunding.filter.model.containsStringWithValue
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
    override var filterList = FilterList(listOf())

    init {
        compositeDisposable.add(
            userRepository.userStatus()
                .filter { !it }
                .subscribe {
                    setUserProfile()
                    loadPets(FilterList(listOf()))
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

    override fun loadPets(filterList: FilterList) {
        view.showRippleWaiting()
        this.filterList = filterList
        compositeDisposable.add(
            petRepository.getPets()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { view.hideRippleWaiting() }
                .subscribe({ petsList ->
                    val filteredPets = petsList
                        .filter { filterList.containsString(Filter.Type.AnimalType, it.type) }
                        .filter { filterList.containsString(Filter.Type.Sex, it.sex) }
                        .filter { filterList.containsString(Filter.Type.Size, it.size) }
                        .filter { filterList.containsString(Filter.Type.AnimalType, it.type) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Condition, "Vacinado", it.vaccinated) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Condition, "Castrado", it.castrated) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Condition, "Desverminado", it.dewormed) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Likes, "Crianças", it.likeChildren) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Likes, "Outros Animais", it.likeAnimals) }
                        .filter { filterList.containsStringWithValue(Filter.Type.Likes, "Idosos", it.likeElders) }
                        .filter {
                            filterList.containsStringWithValue(Filter.Type.Age, "Filhote", it.birthDate.monthsSinceNow() < 12) ||
                                    filterList.containsStringWithValue(Filter.Type.Age, "Adulto", it.birthDate.monthsSinceNow() >= 12 && it.birthDate.monthsSinceNow() < 96) ||
                                    filterList.containsStringWithValue(Filter.Type.Age, "Idoso", it.birthDate.monthsSinceNow() >= 96)
                        }
                    view.updateCardAdapter(filteredPets)
                }, { e -> Log.e("Error", "Failed to load pets ${e.message}") })

        )
    }

    override fun setUserProfile() {
        view.setDrawerUserInformation(userRepository.getCurrentUser())
    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    override fun onMatchButtonClicked(pet: Pet) {
        if (userRepository.getCurrentUserId() != null)
        {
            view.startChatActivity(pet)
        }
        else
        {
            view.startSplashActivity()
        }
    }
}
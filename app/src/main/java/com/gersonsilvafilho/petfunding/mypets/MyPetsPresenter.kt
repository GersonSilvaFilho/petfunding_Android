package com.gersonsilvafilho.petfunding.mypets

import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by GersonSilva on 5/22/17.
 */
class MyPetsPresenter(val view: MyPetsContract.View, val userRepository: UserRepository, val petRepository: PetRepository, val matchRepository: MatchReposity) :
    MyPetsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        loadLikes()
    }


    override fun loadLikes() {
        compositeDisposable.add(petRepository.getPetsFromUserId(userRepository.getCurrentUserId()).subscribe { l ->
            view.setAdapter(l)
        })
    }

    override fun petSelected(pet: Pet) {
        view.startDetails(pet)
    }

    override fun petEdit(it: Pet) {
        view.startEditPet(it)
    }

    override fun getUsersFromPet(groupOrdinal: Int, petId: String) {
        compositeDisposable.add(matchRepository.getAllMatchesFromPet(petId).subscribe { t1 ->
            userRepository.getUserFromMatch(petId).subscribe { l ->
                view.setUser(groupOrdinal, l.filter { user -> t1.map { t -> t.userId }.contains(user.uid) })
            }
        })

    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}
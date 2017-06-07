package com.gersonsilvafilho.petfunding.SplashTests

import com.gersonsilvafilho.petfunding.main.MainMenuContract
import com.gersonsilvafilho.petfunding.main.MainMenuPresenter
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.pet.PetRepository
import com.gersonsilvafilho.petfunding.model.user.User
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Created by GersonSilva on 5/1/17.
 */
class MainMenuPresenterTest {

     lateinit var mockView : MainMenuContract.View
     lateinit var mockUserRepository : UserRepository
     lateinit var mockPetRepository:PetRepository
     lateinit var mockMatchRepository : MatchReposity

     lateinit var mockUser : User

     lateinit var presenter: MainMenuPresenter

     @Before
     fun init()
     {
         mockView = mock(MainMenuContract.View::class.java)
         mockUserRepository = mock(UserRepository::class.java)
         mockPetRepository = mock(PetRepository::class.java)
         mockMatchRepository = mock(MatchReposity::class.java)
         mockUser = User()
         mockUser.username = "Gerson Silva"

         setupUserRepositoryObservers()
         setupViewObservers()
         presenter = MainMenuPresenter(mockView,mockUserRepository,mockPetRepository,mockMatchRepository)
     }

    private fun setupUserRepositoryObservers()
    {
        Mockito.`when`(mockUserRepository.currentUserChanged()).thenReturn(Observable.just(mockUser))
    }

    private fun setupViewObservers()
    {
        Mockito.`when`(mockView.filterTypeChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.filterSexChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.filterSizeChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.filterConditionChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.filterLikeChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.filterAgeChanges()).thenReturn(Observable.just(listOf()))
        Mockito.`when`(mockView.applyButtonClicked()).thenReturn(Observable.just(Unit))
    }

    @Test
    fun setUserProfileTest() {
        Mockito.`when`(mockUserRepository.getCurrentUser()).thenReturn(mockUser)
        presenter.setUserProfile()
        Mockito.verify(mockView, Mockito.times(1)).setDrawerUserInformation(mockUser)
    }

    @Test
    fun setUserLogoutTest() {
        presenter.userLogout()
        Mockito.verify(mockUserRepository, Mockito.times(1)).userLogout()
    }

    @Test
    fun setUnmatchedPetTest() {
        val pet = Pet()
        Mockito.`when`(mockUserRepository.addUnmatch(pet.uid)).thenReturn(Completable.complete())
        presenter.userUnmatchedPet(pet)
        Mockito.verify(mockUserRepository, Mockito.times(1)).addUnmatch(pet.uid)
    }

    @Test
    fun setMatchedExistPetTest() {
        val pet = Pet()
        Mockito.`when`(mockMatchRepository.checkIfMatchExists(pet.uid,mockUser.uid)).thenReturn(Single.just(true))
        Mockito.`when`(mockUserRepository.getCurrentUserId()).thenReturn(mockUser.uid)
        presenter.userMatchedPet(pet)
        Mockito.verify(mockView, Mockito.times(1)).showItsMatchDialog(pet)
    }

    @Test
    fun setMatchedDoesntExistPetTest() {
        val pet = Pet()
        Mockito.`when`(mockMatchRepository.checkIfMatchExists(pet.uid,mockUser.uid)).thenReturn(Single.just(false))
        Mockito.`when`(mockUserRepository.getCurrentUserId()).thenReturn(mockUser.uid)
        Mockito.`when`(mockMatchRepository.addMatch(pet.uid,mockUser.uid)).thenReturn(Single.just(""))
        Mockito.`when`(mockUserRepository.addMatchToUser("")).thenReturn(Completable.complete())
        presenter.userMatchedPet(pet)
        Mockito.verify(mockView, Mockito.times(1)).showItsMatchDialog(pet)
        Mockito.verify(mockUserRepository, Mockito.times(1)).addMatchToUser("")
    }
}

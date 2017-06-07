package com.gersonsilvafilho.petfunding.SplashTests

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.splash.SplashContract
import com.gersonsilvafilho.petfunding.splash.SplashPresenter
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

/**
 * Created by GersonSilva on 5/1/17.
 */
class SplashPresenterTest {

     lateinit var mockView : SplashContract.View
     lateinit var mockUserRepository : UserRepository

     lateinit var presenter: SplashPresenter

     @Before
     fun init()
     {
         mockView = mock(SplashContract.View::class.java)
         mockUserRepository = mock(UserRepository::class.java)

         Mockito.`when`(mockUserRepository.userStatus()).thenReturn(Observable.just(true)).thenReturn(Observable.just(false))
         presenter = SplashPresenter(mockView,mockUserRepository)
     }

     @Test
     fun testCancelFacebook() {
         presenter.facebookCancel()
         Mockito.verify(mockView, times(1)).showFacebookError()
     }

     @Test
     fun testErrorFacebook() {
         presenter.facebookOnError()
         Mockito.verify(mockView, times(1)).showFacebookError()
     }

     @Test
     fun testOkFacebook() {
         presenter.facebookSuccess()
         Mockito.verify(mockView, times(1)).facebookSuccess()
     }

    @Test
    fun testFirebaseSuccess() {
        Mockito.`when`(mockUserRepository.loginWithFacebook("test")).thenReturn(Observable.just(true))
        presenter.firebaseSuccess("test")
        Mockito.verify(mockUserRepository, times(1)).getUsernameFromFacebook()
    }

    @Test
    fun testFirebaseError() {
        Mockito.`when`(mockUserRepository.loginWithFacebook("test")).thenReturn(Observable.just(false))
        presenter.firebaseSuccess("test")
        Mockito.verify(mockUserRepository, times(0)).getUsernameFromFacebook()
    }

    @Test
    fun testUserLoggedIn() {
        Mockito.verify(mockView, times(1)).showFirebaseSuccess()
        Mockito.verify(mockView, times(1)).goToMainMenuActivity()
        Mockito.verify(mockUserRepository, times(1)).monitorCurrentUser()
    }

}

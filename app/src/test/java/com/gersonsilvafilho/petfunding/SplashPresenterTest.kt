package com.gersonsilvafilho.petfunding

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.splash.ui.SplashContract
import com.gersonsilvafilho.petfunding.splash.ui.SplashPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

/**
 * Created by GersonSilva on 5/1/17.
 */
class SplashPresenterTest {

    private val view: SplashContract.View = mock()
    private val userRepository: UserRepository = mock()
    private lateinit var presenter: SplashPresenter

    @Test
    fun `test init with user already logged`() {
        whenever(userRepository.isUserLoggedIn()).thenReturn(Observable.just(true))
        presenter = SplashPresenter(view, userRepository)

        verify(userRepository).isUserLoggedIn()
        verify(view).goToMainMenuActivity()
    }

    @Test
    fun `test init with user not logged`() {
        whenever(userRepository.isUserLoggedIn()).thenReturn(Observable.just(false))
        presenter = SplashPresenter(view, userRepository)

        verify(userRepository).isUserLoggedIn()
        verify(view, never()).goToMainMenuActivity()
    }

    @Test
    fun `test facebook login button success`() {
        val token = "facebook token"
        whenever(userRepository.isUserLoggedIn()).thenReturn(Observable.just(false))
        whenever(userRepository.loginWithFacebook(any())).thenReturn(Observable.just(true))
        presenter = SplashPresenter(view, userRepository)
        presenter.facebookSuccess(token)

        verify(userRepository).loginWithFacebook(token)
        verify(view).goToMainMenuActivity()
        verify(view).showToast(R.string.login_success_facebook)
    }

    @Test
    fun `test facebook login button failed`() {
        val token = "facebook token"
        whenever(userRepository.isUserLoggedIn()).thenReturn(Observable.just(false))
        whenever(userRepository.loginWithFacebook(any())).thenReturn(Observable.just(false))
        presenter = SplashPresenter(view, userRepository)
        presenter.facebookSuccess(token)

        verify(userRepository).loginWithFacebook(token)
        verify(view, never()).goToMainMenuActivity()
    }
}

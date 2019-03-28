package com.gersonsilvafilho.petfunding.splash.ui

import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.model.user.UserRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter(private val view: SplashContract.View, private val userRepository: UserRepository) : SplashContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            userRepository.isUserLoggedIn()
                .take(1)
                .filter { it }
                .subscribe {
                    proceedToMainMenu()
                })
    }

    override fun facebookSuccess(token: String)
    {
        view.showToast(R.string.login_success_facebook)
        compositeDisposable.add(
            userRepository.loginWithFacebook(token)
                .take(1)
                .subscribe {
                    if (it) {
                        proceedToMainMenu()
                    } else {
                        facebookOnError()
                    }
                }
        )
    }

    override fun facebookCancel() = view.showToast(R.string.user_canceled_facebook)

    override fun facebookOnError() = view.showToast(R.string.error_login_facebook)

    override fun onStop() = compositeDisposable.clear()

    private fun proceedToMainMenu() = view.goToMainMenuActivity()

}
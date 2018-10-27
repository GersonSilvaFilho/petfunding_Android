package com.gersonsilvafilho.petfunding.splash.ui

import com.gersonsilvafilho.petfunding.model.user.UserRepository
import com.gersonsilvafilho.petfunding.splash.ui.SplashContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by GersonSilva on 3/21/17.
 */
class SplashPresenter(private val view: SplashContract.View, private val userRepository: UserRepository) : SplashContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            userRepository.userStatus()
                .take(1)
                .subscribe { logged ->
                    if (logged) {
                        view.goToMainMenuActivity()
                        userRepository.monitorCurrentUser()
                    }
                })
    }

    override fun facebookSuccess(token: String)
    {
        view.showToast("Facebook Success")
        compositeDisposable.add(
            userRepository.loginWithFacebook(token)
                .take(1)
                .subscribe {
                    view.goToMainMenuActivity()
                }
        )
    }

    override fun facebookCancel() = view.showToast("User canceled")

    override fun facebookOnError() = view.showToast("Error on try to login on Facebook")

    override fun onStop() = compositeDisposable.clear()

}
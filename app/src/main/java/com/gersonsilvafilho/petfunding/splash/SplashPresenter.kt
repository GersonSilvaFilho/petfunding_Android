package com.gersonsilvafilho.petfunding.splash

import com.gersonsilvafilho.petfunding.model.user.UserRepository
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
                        view.showToast("Facebook Success")
                        view.goToMainMenuActivity()
                        userRepository.monitorCurrentUser()
                    } else {
                        view.startSelfActivity()
                    }
                })
    }

    override fun facebookSuccess(token: String)
    {
        compositeDisposable.add(
            userRepository.loginWithFacebook(token)
                .take(1)
                .subscribe { userRepository.getUsernameFromFacebook() }
        )
    }

    override fun facebookCancel()
    {
        view.showToast("User canceled")
    }

    override fun facebookOnError()
    {
        view.showToast("Error on try to login on Facebook")
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}
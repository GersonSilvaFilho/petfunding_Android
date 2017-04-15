package com.gersonsilvafilho.library.view

/**
 * Created by GersonSilva on 4/10/17.
 */
interface View {

    fun showMessage(message: String)

    fun showMessage(messageResId: Int)

    fun showProgress()

    fun showProgress(message: String)

    fun showProgress(messageResId: Int)

    fun showProgress(message: String, title: String)

    fun showProgress(messageResId: Int, titleResId: Int)

    fun hideProgress()

    fun hideKeyboard()
}

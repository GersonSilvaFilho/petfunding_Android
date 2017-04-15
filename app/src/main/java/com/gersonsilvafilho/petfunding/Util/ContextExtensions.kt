package com.gersonsilvafilho.petfunding.util

import android.content.Context
import android.widget.Toast

/**
 * Created by GersonSilva on 4/8/17.
 */

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

package com.laxian.ktx.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast

fun Activity.push(target: Class<out Activity>) =
    this.startActivity(Intent(this, target))

fun Activity.pushAndReplace(target: Class<out Activity>) {
    this.startActivity(Intent(this, target))
    finish()
}

fun Activity.toast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Activity.hideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)?.hideSoftInputFromWindow(
        (currentFocus ?: View(this)).windowToken,
        0
    )
    window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    currentFocus?.clearFocus()
}

fun Activity.showKeyboard(et: EditText) {
    et.requestFocus()
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)?.showSoftInput(
        et,
        InputMethodManager.SHOW_IMPLICIT
    )
}

fun Activity.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)?.hideSoftInputFromWindow(
        view.windowToken,
        0
    )
}
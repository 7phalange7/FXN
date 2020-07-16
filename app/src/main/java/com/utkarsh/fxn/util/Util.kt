package com.utkarsh.fxn.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

//----------------------------------------------------------------------//
//--------------------------//Hide Keyboard//---------------------------//

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}



fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//----------------------------------------------------------------------//

fun Fragment.showKeyboard(editText: EditText) {
    view?.let { activity?.showKeyboard(it,editText) }
}

fun Context.showKeyboard(view: View, editText: EditText) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
}

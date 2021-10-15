package com.test.kumparan.util

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeBy(disposeBag: CompositeDisposable) {
    disposeBag.add(this)
}

fun TextView.setUnderlineSpannableText(message: String) {
    text = if (message.isBlank()) {
        ""
    } else {
        SpannableString(message).apply {
            setSpan(UnderlineSpan(), 0, message.length, 0)
        }
    }
}
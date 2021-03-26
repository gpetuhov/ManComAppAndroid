package ru.mancomapp.utils

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan

fun getUnderlinedText(text: String): SpannableStringBuilder {
    val spannableStringBuilder = SpannableStringBuilder(text)
    val underlineSpan = UnderlineSpan()
    spannableStringBuilder.setSpan(underlineSpan, 0, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableStringBuilder
}

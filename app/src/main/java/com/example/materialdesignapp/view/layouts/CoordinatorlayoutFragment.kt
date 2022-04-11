package com.example.materialdesignapp.view.layouts

import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentCoordinatorlayoutBinding
import com.example.materialdesignapp.utils.BindingFragment

class CoordinatorlayoutFragment : BindingFragment<FragmentCoordinatorlayoutBinding>(
    FragmentCoordinatorlayoutBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFont()
        setSpans()

    }

    private fun setSpans() {
        var text = getString(R.string.large_text)

        var spannableStringBuilder =
            SpannableStringBuilder(text) // меняем текст, и его свойства/параметры
        // val spannableString = SpannableString(text)//  меняем свойства/параметры текста
        //    val spanned = SpannedString(text) // ничего не можем изменить, но можем хранить ссылку на результат SpannableStringBuilder и/или SpannableString

        binding.coordTextView.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)
        spannableStringBuilder = binding.coordTextView.text as SpannableStringBuilder


        var startLetter = 0
        var endLetter = 1
        var typeSpan = 1
        var startParagraph = 0
        var endParagraph = 1
        var stripeWidth = 10

        with(spannableStringBuilder) {
            indices.forEach {

                if (this[it] == ' ') {
                    if (endLetter > startLetter) {

                        when (typeSpan) {
                            1 -> {
                                setSpan(
                                    ForegroundColorSpan(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.green
                                        )
                                    ), startLetter, endLetter, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            2 -> {
                                setSpan(
                                    StyleSpan(Typeface.BOLD_ITALIC),
                                    startLetter,
                                    endLetter,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            3 -> {
                                setSpan(
                                    BackgroundColorSpan(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.light_blue
                                        )
                                    ), startLetter, endLetter, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            4 -> {
                                setSpan(
                                    ScaleXSpan(1.5f),
                                    startLetter,
                                    endLetter,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            5 -> {
                                setSpan(
                                    UnderlineSpan(),
                                    startLetter,
                                    endLetter,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            6 -> {
                                setSpan(
                                    TextAppearanceSpan(context, R.style.spanText),
                                    startLetter,
                                    endLetter,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                            7 -> {
                                setSpan(
                                    MaskFilterSpan(
                                        BlurMaskFilter(
                                            5f,
                                            BlurMaskFilter.Blur.SOLID
                                        )
                                    ), startLetter, endLetter, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                                )
                            }
                        }
                        if (typeSpan == 7) typeSpan = 1 else typeSpan++
                    }
                    startLetter = it + 1
                } else {
                    endLetter = it + 1
                }

                if (this[it] == '\n') {
                    if (endParagraph > startParagraph) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            setSpan(
                                QuoteSpan(
                                    ContextCompat.getColor(requireContext(), R.color.red),
                                    stripeWidth,
                                    10
                                ), startParagraph, endParagraph, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                            )
                            stripeWidth++
                        }
                    }
                    startParagraph = it + 1
                } else {
                    endParagraph = it
                }

            }

        }


    }

    private fun setFont() {
        val request = FontRequest(
            "com.google.android.gms.fonts", "com.google.android.gms", "Lemonada",
            R.array.com_google_android_gms_fonts_certs
        )

        val callback = object : FontsContractCompat.FontRequestCallback() {
            override fun onTypefaceRetrieved(typeface: Typeface?) {
                super.onTypefaceRetrieved(typeface)
                binding.coordTextView.typeface = typeface

            }
        }
        FontsContractCompat.requestFont(
            requireContext(), request, callback,
            Handler(requireActivity().mainLooper)
        )
    }


    override val viewModel: Any
        get() = TODO("Not yet implemented")

}
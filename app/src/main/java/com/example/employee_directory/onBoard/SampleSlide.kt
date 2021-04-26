package com.example.employee_directory.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


class SampleSlide : Fragment() {
    private var layoutResId = 0
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null && requireArguments().containsKey(ARG_LAYOUT_RES_ID)) layoutResId = requireArguments().getInt(ARG_LAYOUT_RES_ID)
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    companion object {
        private const val ARG_LAYOUT_RES_ID = "layoutResId"
        fun newInstance(layoutResId: Int): SampleSlide {
            val sampleSlide = SampleSlide()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
            sampleSlide.arguments = args
            return sampleSlide
        }
    }
}
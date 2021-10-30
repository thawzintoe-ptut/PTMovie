package com.ptut.ptmovie.feature.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ptut.appbase.core.BaseFragment
import com.ptut.ptmovie.databinding.FragmentInfoBinding

class InfoFragment:BaseFragment<FragmentInfoBinding>() {
    override fun bindView(inflater: LayoutInflater): FragmentInfoBinding {
        return FragmentInfoBinding.inflate(inflater)
    }

}
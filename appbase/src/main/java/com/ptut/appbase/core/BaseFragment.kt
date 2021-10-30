package com.ptut.appbase.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

  protected lateinit var binding: VB

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    binding = bindView(inflater)
    return binding.root
  }

  abstract fun bindView(inflater: LayoutInflater): VB

}
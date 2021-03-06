package com.ptut.appbase.core.mvp

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ptut.appbase.core.BaseActivity
import com.ptut.appbase.di.Injectable
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class MvpActivity<VB : ViewBinding, V : Viewable, VM : BaseViewModel<V>> :
  BaseActivity<VB>(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  protected abstract val viewModel: VM

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    try {
      viewModel.attachView(this as V)
    } catch (exception: Exception) {
      Timber.e(exception)
      throw InvalidMvpImplementationException()
    }
    setContentView(binding.root)
  }

  override fun onDestroy() {
    super.onDestroy()
    viewModel.detachView()
  }

  /**
   * Helper function for easily init of viewModel
   */
  protected inline fun <reified VM : BaseViewModel<V>> contractedViewModel(): Lazy<VM> =
    ViewModelLazy(VM::class)

  inner class ViewModelLazy<VM : ViewModel>(
    private val viewModelClass: KClass<VM>
  ) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
      get() {
        var viewModel = cached
        if (viewModel == null) {
          viewModel = ViewModelProvider(
            this@MvpActivity,
            viewModelFactory
          ).get(viewModelClass.java)
          cached = viewModel
        }
        return viewModel
      }

    override fun isInitialized() = cached != null
  }

}
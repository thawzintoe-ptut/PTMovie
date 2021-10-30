package com.ptut.appbase.core.mvp

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ptut.appbase.core.BaseDialogFragment
import com.ptut.appbase.di.Injectable
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class MvpDialogFragment<V : Viewable, VM : BaseViewModel<V>> :
  BaseDialogFragment(), Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  protected abstract val viewModel: VM

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

    viewModel.attachView(this as V)

    val dialog = super.onCreateDialog(savedInstanceState)

    return dialog
  }

  /**
   * Helper function for easily init of viewModel
   */
  protected inline fun <reified VM : BaseViewModel<V>> contractedViewModel(): Lazy<VM> =
    ViewModelLazy(VM::class)

  inner class ViewModelLazy<VM : androidx.lifecycle.ViewModel>(
    private val viewModelClass: KClass<VM>
  ) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
      get() {
        var viewModel = cached
        if (viewModel == null) {
          viewModel =
            ViewModelProvider(
              this@MvpDialogFragment,
              viewModelFactory
            ).get(viewModelClass.java)
          cached = viewModel
        }
        return viewModel
      }

    override fun isInitialized() = cached != null
  }
}

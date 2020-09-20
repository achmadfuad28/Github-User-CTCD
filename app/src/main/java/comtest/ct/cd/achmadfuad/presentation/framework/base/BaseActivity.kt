package comtest.ct.cd.achmadfuad.presentation.framework.base

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import comtest.ct.cd.achmadfuad.BR
import comtest.ct.cd.achmadfuad.presentation.framework.annotations.ViewLayout
import comtest.ct.cd.achmadfuad.presentation.framework.owner.ViewDataBindingOwner
import comtest.ct.cd.achmadfuad.presentation.framework.owner.ViewModelOwner
import comtest.ct.cd.achmadfuad.presentation.framework.view.BindingView

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutIfDefined()
    }

    private fun setLayoutIfDefined() {
        val layoutResId = getViewLayoutResId()
        if (layoutResId == View.NO_ID) return

        if (this is ViewDataBindingOwner<*>) {
            setContentViewBinding(this, layoutResId)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, this.viewModel)
            }
            if (this is BindingView) {
                binding.setVariable(BR.view, this)
            }

            binding.lifecycleOwner = this
        } else {
            setContentView(layoutResId)
        }
    }

    protected open fun getViewLayoutResId(): Int {
        val layout = javaClass.annotations.find { it is ViewLayout } as? ViewLayout
        return layout?.value ?: View.NO_ID
    }

    protected fun setHomeAsUpIndicator(@DrawableRes resId: Int) {
        supportActionBar?.setHomeAsUpIndicator(resId)
    }
}
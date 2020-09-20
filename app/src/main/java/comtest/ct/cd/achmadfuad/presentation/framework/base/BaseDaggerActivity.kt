package comtest.ct.cd.achmadfuad.presentation.framework.base

import android.os.Bundle

/**
 * BaseDaggerActivity
 * Base class for module's activity with dependency components attached
 */
abstract class BaseDaggerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun injectComponent()
}

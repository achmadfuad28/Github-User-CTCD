package comtest.ct.cd.achmadfuad.presentation.framework.annotations

import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewLayout(@LayoutRes @NonNull val value: Int = View.NO_ID)

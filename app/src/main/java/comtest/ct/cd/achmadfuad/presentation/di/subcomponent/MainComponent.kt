package comtest.ct.cd.achmadfuad.presentation.di.subcomponent

import comtest.ct.cd.achmadfuad.presentation.di.scope.ActivityScope
import comtest.ct.cd.achmadfuad.presentation.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)

}

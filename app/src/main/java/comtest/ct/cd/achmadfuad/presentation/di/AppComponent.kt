package comtest.ct.cd.achmadfuad.presentation.di


import android.app.Application
import comtest.ct.cd.achmadfuad.presentation.di.subcomponent.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            NetworkModule::class,
            RepositoryModule::class,
            AndroidSupportInjectionModule::class,
            InteractorModule::class
        ]
)

interface AppComponent {

    @Component.Factory
    interface Builder {

        fun build(@BindsInstance application: Application): AppComponent
    }

    fun mainComponent() : MainComponent.Factory

}
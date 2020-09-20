package comtest.ct.cd.achmadfuad

import android.app.Application
import androidx.multidex.MultiDex
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB
import comtest.ct.cd.achmadfuad.presentation.di.AppComponent
import comtest.ct.cd.achmadfuad.presentation.di.DaggerAppComponent


class BaseApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().build(this)
    }


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        setupCommonProperties()
    }

    private fun setupCommonProperties() {
        Gander.setGanderStorage(GanderIMDB.getInstance())
    }


}
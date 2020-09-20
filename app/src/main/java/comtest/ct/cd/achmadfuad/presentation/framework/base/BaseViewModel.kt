package comtest.ct.cd.achmadfuad.presentation.framework.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private  val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun  addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
    private fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposable()
    }
}
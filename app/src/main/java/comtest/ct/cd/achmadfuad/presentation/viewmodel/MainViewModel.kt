package comtest.ct.cd.achmadfuad.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import comtest.ct.cd.achmadfuad.domain.entities.ResultState
import comtest.ct.cd.achmadfuad.domain.interactor.SearchUsersInteractor
import comtest.ct.cd.achmadfuad.domain.interactor.impl.SearchUsersInteractorImpl
import comtest.ct.cd.achmadfuad.presentation.framework.base.BaseViewModel
import comtest.ct.cd.achmadfuad.presentation.framework.getResultStateError
import comtest.ct.cd.achmadfuad.presentation.framework.observableIo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val searchUsersInteractor: SearchUsersInteractor) : BaseViewModel() {
    var bTextSearch = MutableLiveData<String>("")
    var bSortType = MutableLiveData<String>("desc")

    val stateResultUser = MutableLiveData<ResultState>()
    var currentPage = 1
    val showLoadingView = ObservableField<Boolean>(true)

    fun fetchUsers(query: String, order: String, page:Int){

        val param = SearchUsersInteractorImpl.Params(query, order, page)

        val disposable = searchUsersInteractor.searchUser(param)
            .compose(observableIo())
            .doOnSubscribe {
                stateResultUser.value = ResultState.Loading
            }
            .subscribe ({ resultState ->
                stateResultUser.value = ResultState.HideLoading
                stateResultUser.value = resultState

            },{
                stateResultUser.value = ResultState.HideLoading
                stateResultUser.value = getResultStateError(it)

            })
        addDisposable(disposable)
    }

    fun showLoading() {
        showLoadingView.set(true)
    }
    fun hideLoading() {
        showLoadingView.set(false)
    }
}

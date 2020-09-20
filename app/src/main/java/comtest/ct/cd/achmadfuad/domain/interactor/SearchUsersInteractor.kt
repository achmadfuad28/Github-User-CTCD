package comtest.ct.cd.achmadfuad.domain.interactor

import comtest.ct.cd.achmadfuad.domain.entities.ResultState
import comtest.ct.cd.achmadfuad.domain.interactor.impl.SearchUsersInteractorImpl
import io.reactivex.Observable

interface SearchUsersInteractor {
    fun searchUser(param: SearchUsersInteractorImpl.Params) : Observable<ResultState>

}
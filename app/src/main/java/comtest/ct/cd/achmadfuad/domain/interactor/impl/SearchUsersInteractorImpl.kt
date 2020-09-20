package comtest.ct.cd.achmadfuad.domain.interactor.impl

import comtest.ct.cd.achmadfuad.data.repository.SearchUserRepository
import comtest.ct.cd.achmadfuad.domain.entities.ResultState
import comtest.ct.cd.achmadfuad.domain.interactor.SearchUsersInteractor
import comtest.ct.cd.achmadfuad.domain.mapper.SearchUsersMapper
import io.reactivex.Observable

class SearchUsersInteractorImpl(private val repository: SearchUserRepository, private val searchUsersMapper: SearchUsersMapper)
    : SearchUsersInteractor {

    override fun searchUser(params: Params): Observable<ResultState> {
        return repository.searchUsers(params.query, params.order, params.page)
            .map {
            val data = it.items ?: mutableListOf()
            if (data.isNotEmpty()) {
                ResultState.Success(searchUsersMapper.transform(it), "")

            } else {
                ResultState.NotFound(Throwable(""))
            }
        }
    }

    data class Params(val query: String, val order: String, val page: Int)
}
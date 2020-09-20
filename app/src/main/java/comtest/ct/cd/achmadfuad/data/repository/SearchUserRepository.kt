package comtest.ct.cd.achmadfuad.data.repository

import comtest.ct.cd.achmadfuad.data.dto.UsersApiDto
import comtest.ct.cd.achmadfuad.domain.entities.SearchUsersResult
import io.reactivex.Observable


interface SearchUserRepository {

    /**
     * search user in Github
     *
     * @query[q] The query used to retrieve users data
     * @return [SearchUsersResult]
     */
    fun searchUsers(query: String?, order: String?, page: Int): Observable<UsersApiDto>
}
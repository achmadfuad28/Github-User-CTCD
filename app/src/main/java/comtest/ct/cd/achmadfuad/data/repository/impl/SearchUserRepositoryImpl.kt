package comtest.ct.cd.achmadfuad.data.repository.impl

import comtest.ct.cd.achmadfuad.data.api.SearchApi
import comtest.ct.cd.achmadfuad.data.repository.SearchUserRepository

class SearchUserRepositoryImpl(private val service: SearchApi
) : SearchUserRepository {

    override fun searchUsers(query: String?, order: String?, page: Int)= service.searchUsers(query, order, page)
}
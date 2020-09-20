package comtest.ct.cd.achmadfuad.domain.mapper

import comtest.ct.cd.achmadfuad.data.dto.UsersApiDto
import comtest.ct.cd.achmadfuad.domain.entities.SearchUsersResult
import comtest.ct.cd.achmadfuad.domain.entities.UserItemResult


class SearchUsersMapper : Mapper<UsersApiDto, SearchUsersResult> {

    override fun transform(from: UsersApiDto): SearchUsersResult {
        val repositoryList = arrayListOf<UserItemResult>()

        from.items?.let {
            for (item in it) {

                val repositoryResult = UserItemResult(item.id)
                    .apply {
                        name = item.login
                        avatar = item.avatar_url
                    }

                repositoryList.add(repositoryResult)
            }
        }

        return SearchUsersResult().apply {
            data = repositoryList
            lastPage = from.incompleteResult
            count = from.totalCount
        }
    }
}
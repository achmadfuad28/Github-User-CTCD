package comtest.ct.cd.achmadfuad.data.api

import comtest.ct.cd.achmadfuad.data.dto.UsersApiDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/users")
    fun searchUsers(@Query("q") q: String?,
                    @Query("order") order: String?,
                    @Query("page") page: Int): Observable<UsersApiDto>

}
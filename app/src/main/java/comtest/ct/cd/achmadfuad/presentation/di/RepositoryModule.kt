package comtest.ct.cd.achmadfuad.presentation.di

import comtest.ct.cd.achmadfuad.data.api.SearchApi
import comtest.ct.cd.achmadfuad.data.repository.SearchUserRepository
import comtest.ct.cd.achmadfuad.data.repository.impl.SearchUserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideSearchUserRepository(@ApiRetrofit searchApi: SearchApi): SearchUserRepository {
        return SearchUserRepositoryImpl(
            searchApi)
    }
}
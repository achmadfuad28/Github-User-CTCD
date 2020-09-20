package comtest.ct.cd.achmadfuad.presentation.di

import comtest.ct.cd.achmadfuad.data.repository.SearchUserRepository
import comtest.ct.cd.achmadfuad.domain.interactor.SearchUsersInteractor
import comtest.ct.cd.achmadfuad.domain.interactor.impl.SearchUsersInteractorImpl
import comtest.ct.cd.achmadfuad.domain.mapper.SearchUsersMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InteractorModule {

    @Provides
    @Singleton
    internal fun provideSearchUsersInteractor(repository: SearchUserRepository, searchUserMapper: SearchUsersMapper): SearchUsersInteractor{
        return SearchUsersInteractorImpl(repository, searchUserMapper)

    }

    @Provides
    @Singleton
    fun provideSearchUsersMapper(): SearchUsersMapper {
        return SearchUsersMapper()
    }
}
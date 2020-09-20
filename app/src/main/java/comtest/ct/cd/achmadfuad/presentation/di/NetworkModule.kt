package comtest.ct.cd.achmadfuad.presentation.di

import android.app.Application
import com.ashokvarma.gander.GanderInterceptor
import comtest.ct.cd.achmadfuad.BuildConfig
import comtest.ct.cd.achmadfuad.data.api.SearchApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @ApiRetrofit
    internal fun provideOkHttpClient(application: Application): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)
            httpClientBuilder.addInterceptor(GanderInterceptor(application).showNotification(true))
        }
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(@ApiRetrofit okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        application: Application): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()

    }

    @Singleton
    @Provides
    @ApiRetrofit
    internal fun provideApiService(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }



}
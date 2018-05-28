package ru.androidpirates.aiweather.injection.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidpirates.aiweather.common.HostSelectionInterceptor
import ru.androidpirates.aiweather.common.constants.Constants
import ru.androidpirates.aiweather.injection.InstrumentationModule
import ru.androidpirates.aiweather.injection.qualifiers.ForApplication
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [InstrumentationModule::class])
class NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppInterceptor

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NetworkInterceptor

    @Provides
    @Singleton
    fun provideApi(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(Constants.BASE_API_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideHostSelectionInterceptor(
            @ForApplication appContext: Context
    ): HostSelectionInterceptor = HostSelectionInterceptor(appContext)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
            hostSelectionInterceptor: HostSelectionInterceptor,
            @AppInterceptor appInterceptor: @JvmSuppressWildcards Interceptor,
            @NetworkInterceptor networkInterceptor: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(hostSelectionInterceptor)
        builder.interceptors().add(appInterceptor)
        builder.networkInterceptors().addAll(networkInterceptor)
        return builder.build()
    }
}
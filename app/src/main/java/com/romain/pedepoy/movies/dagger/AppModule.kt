package com.romain.pedepoy.movies.dagger

import android.app.Application
import com.romain.pedepoy.movies.data.AppDatabase
import com.romain.pedepoy.movies.data.MoviesRemoteDataSource
import com.romain.pedepoy.movies.service.MoviesApi
import com.romain.pedepoy.movies.utilities.BASE_URL
import com.romain.pedepoy.movies.utilities.CERTIFICATE_AUTHORITY
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideMoviesApi(okHttpClient: OkHttpClient): MoviesApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MoviesApi::class.java)


    @Singleton
    @Provides
    fun provideOkHttpClient(handshakeCertificates: HandshakeCertificates) =
        OkHttpClient.Builder()
        .sslSocketFactory(handshakeCertificates.sslSocketFactory(), handshakeCertificates.trustManager)
        .build()

    @Singleton
    @Provides
    fun provideCertificates() =
        HandshakeCertificates.Builder()
        .addTrustedCertificate(CERTIFICATE_AUTHORITY.trimIndent().decodeCertificatePem())
        .build()

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(moviesApi: MoviesApi)
            = MoviesRemoteDataSource(moviesApi)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideMoviesDao(db: AppDatabase) = db.moviesDao()

}

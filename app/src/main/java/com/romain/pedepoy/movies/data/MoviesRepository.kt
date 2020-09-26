package com.romain.pedepoy.movies.data

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import javax.inject.Inject
import javax.inject.Singleton
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers

@Singleton
class MoviesRepository  @Inject constructor(private val dao: MoviesDao,
                                            private val remoteSource: MoviesRemoteDataSource) {

    val movies = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = dao.getPagedList().toLiveData(pageSize = 20).map { Result.success(it)}
        emitSource(source)

        val responseStatus = remoteSource.fetchData()
        if (responseStatus.status == Result.Status.SUCCESS) {
            dao.insertAll(responseStatus.data!!.map {
                Movie(null,
                    it.page.movie_title,
                    it.heros.locale.imageurl,
                    it.clips[0].versions.enus.sizes.sd.srcAlt,
                    it.details.official_url,
                    it.details.locale.en.synopsis,
                    it.page.release_copy,
                    if(it.details.locale.en.castcrew?.directors != null) it.details.locale.en.castcrew.directors.map { director -> director.name } else arrayListOf(),
                    if(it.details.locale.en.castcrew?.writers != null) it.details.locale.en.castcrew.writers.map { writer -> writer.name } else arrayListOf(),
                    if(it.details.locale.en.castcrew?.actors != null) it.details.locale.en.castcrew.actors.map { actor -> actor.name } else arrayListOf())
            })
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
            emitSource(source)
        }
    }

    fun getMovie(id: Long) = dao.getMovie(id)
}
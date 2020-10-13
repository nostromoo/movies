package com.romain.pedepoy.movies.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val dao: MoviesDao,
    private val remoteSource: MoviesRemoteDataSource
) {

    fun getMovies(callback: (List<Movie>) -> Unit) {
//        emit(Result.loading())
        GlobalScope.launch(Dispatchers.IO) {


                val moviesList = dao.getMoviesList()
                callback(moviesList)

                val responseStatus = remoteSource.fetchData()
                if (responseStatus.status == Result.Status.SUCCESS) {
                    val newMovies = responseStatus.data!!.map {
                        Movie(it.page.movie_title,
                            it.heros.locale.imageurl,
                            it.clips[0].versions.enus.sizes.sd.srcAlt,
                            it.clips[0].versions.enus.sizes.sd.height.toInt(),
                            it.clips[0].versions.enus.sizes.sd.width,
                            it.details.official_url,
                            it.details.locale.en.synopsis,
                            it.page.release_copy,
                            it.page.movie_rating,
                            if (it.details.locale.en.castcrew?.directors != null) it.details.locale.en.castcrew.directors.map { director -> director.name } else arrayListOf(),
                            if (it.details.locale.en.castcrew?.writers != null) it.details.locale.en.castcrew.writers.map { writer -> writer.name } else arrayListOf(),
                            if (it.details.locale.en.castcrew?.actors != null) it.details.locale.en.castcrew.actors.map { actor -> actor.name } else arrayListOf())
                    }
                    dao.insertAll(newMovies)
                    callback(newMovies)
                } else if (responseStatus.status == Result.Status.ERROR) {
//            emit(Result.error(responseStatus.message!!))
//            emitSource(source)
                }
        }
    }

    fun getMovie(title: String) = dao.getMovie(title)
}
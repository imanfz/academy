package com.iman.academy.di

import android.content.Context

import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.local.LocalDataSource
import com.iman.academy.data.source.local.room.AcademyDatabase
import com.iman.academy.data.source.remote.RemoteDataSource
import com.iman.academy.utils.AppExecutors
import com.iman.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}

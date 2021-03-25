package com.iman.academy.di

import android.content.Context
import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.remote.RemoteDataSource
import com.iman.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteRepository = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteRepository)
    }
}

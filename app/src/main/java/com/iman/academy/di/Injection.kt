package com.iman.academy.di

import android.content.Context
import com.iman.academy.data.source.AcademyRepository
import com.iman.academy.data.source.remote.RemoteDataSource
import com.iman.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}
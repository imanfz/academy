package com.iman.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.local.entity.CourseEntity
import com.iman.academy.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

//    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getAllCourses()
}


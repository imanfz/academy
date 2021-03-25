package com.iman.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.local.entity.CourseEntity
import com.iman.academy.data.source.local.entity.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}



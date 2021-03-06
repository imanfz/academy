package com.iman.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.iman.academy.data.CourseEntity
import com.iman.academy.data.source.AcademyRepository
import com.iman.academy.utils.DataDummy

/* tanpa repo
class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}*/

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()

}
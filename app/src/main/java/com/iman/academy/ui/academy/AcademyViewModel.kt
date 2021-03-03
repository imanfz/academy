package com.iman.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.iman.academy.data.CourseEntity
import com.iman.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}
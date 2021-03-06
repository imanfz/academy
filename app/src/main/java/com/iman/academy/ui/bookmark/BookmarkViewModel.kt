package com.iman.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.iman.academy.data.CourseEntity
import com.iman.academy.data.source.AcademyRepository
import com.iman.academy.utils.DataDummy

/*
class BookmarkViewModel : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}*/

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()

}
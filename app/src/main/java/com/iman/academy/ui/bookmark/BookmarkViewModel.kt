package com.iman.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> {
        return academyRepository.getBookmarkedCourses()
    }
}


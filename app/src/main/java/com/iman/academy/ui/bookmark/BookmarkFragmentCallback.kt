package com.iman.academy.ui.bookmark

import com.iman.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

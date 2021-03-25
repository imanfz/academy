package com.iman.academy.ui.bookmark

import com.iman.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}


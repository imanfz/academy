package com.iman.academy.data


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.iman.academy.data.source.local.entity.CourseEntity
import com.iman.academy.data.source.local.entity.CourseWithModule
import com.iman.academy.data.source.local.entity.ModuleEntity
import com.iman.academy.vo.Resource

interface AcademyDataSource {

//    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

//    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)
}
package com.iman.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.iman.academy.data.AcademyRepository
import com.iman.academy.data.source.local.entity.CourseEntity
import com.iman.academy.utils.DataDummy
import com.iman.academy.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/*
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}*/

//@RunWith(MockitoJUnitRunner::class)
//class AcademyViewModelTest {
//
//    private lateinit var viewModel: AcademyViewModel
//
//    @Mock
//    private lateinit var academyRepository: AcademyRepository
//
//    @Before
//    fun setUp() {
//        viewModel = AcademyViewModel(academyRepository)
//    }
//
//    @Test
//    fun getCourses() {
//        `when`(academyRepository.getAllCourses()).thenReturn(DataDummy.generateDummyCourses() as ArrayList<CourseEntity>)
//        val courseEntities = viewModel.getCourses()
//        verify(academyRepository).getAllCourses()
//        assertNotNull(courseEntities)
//        assertEquals(5, courseEntities.size)
//    }
//}

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    //private lateinit var observer: Observer<List<CourseEntity>>
    //pakai room
//    private lateinit var observer: Observer<Resource<List<CourseEntity>>>
    //paginatoin
    private lateinit var observer: Observer<Resource<PagedList<CourseEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<CourseEntity>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    /*@Test
    fun getCourses() {
        //val dummyCourses = DataDummy.generateDummyCourses()
        //val courses = MutableLiveData<List<CourseEntity>>()
        //pakai room
        val dummyCourses = Resource.success(DataDummy.generateDummyCourses())
        val courses = MutableLiveData<Resource<List<CourseEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        //val courseEntities = viewModel.getCourses().value
        val courseEntities = viewModel.getCourses().value?.data
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }*/

    @Test
    fun getCourses() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(5)
        val courses = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value?.data
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}
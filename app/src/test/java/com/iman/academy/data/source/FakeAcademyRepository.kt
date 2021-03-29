package com.iman.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.iman.academy.data.AcademyDataSource
import com.iman.academy.data.NetworkBoundResource
import com.iman.academy.data.source.local.LocalDataSource
import com.iman.academy.data.source.local.entity.ContentEntity
import com.iman.academy.data.source.local.entity.CourseEntity
import com.iman.academy.data.source.local.entity.CourseWithModule
import com.iman.academy.data.source.local.entity.ModuleEntity
import com.iman.academy.data.source.remote.ApiResponse
import com.iman.academy.data.source.remote.RemoteDataSource
import com.iman.academy.data.source.remote.response.ContentResponse
import com.iman.academy.data.source.remote.response.CourseResponse
import com.iman.academy.data.source.remote.response.ModuleResponse
import com.iman.academy.utils.AppExecutors
import com.iman.academy.vo.Resource

/*
class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    override fun getAllCourses(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses) {
            val course = CourseEntity(response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponses) {
            val course = CourseEntity(response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponses = remoteDataSource.getAllCourses()
        lateinit var course: CourseEntity
        for (response in courseResponses) {
            if (response.id == courseId) {
                course = CourseEntity(response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath)
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for(response in moduleResponses) {
            val course = ModuleEntity(response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false)
            moduleList.add(course)
        }
        return moduleList
    }


    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module: ModuleEntity
        for(response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false)
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }
}*/
/*
class FakeAcademyRepository(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponses) {
                    val course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })

        return courseResults
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = java.util.ArrayList<CourseEntity>()
                for (response in courseResponses) {
                    val course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult = MutableLiveData<CourseEntity>()

        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                lateinit var course: CourseEntity
                for (response in courseResponses) {
                    if (response.id == courseId) {
                        course = CourseEntity(response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath)
                    }
                }
                courseResult.postValue(course)
            }
        })

        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponses) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)

                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }
        })

        return moduleResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()

        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                for (response in moduleResponses) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)
                        remoteDataSource.getContent(moduleId, object : RemoteDataSource.LoadContentCallback {
                            override fun onContentReceived(contentResponse: ContentResponse) {
                                module.contentEntity = ContentEntity(contentResponse.content)
                                moduleResult.postValue(module)
                            }
                        })
                        break
                    }
                }
            }
        })
        return moduleResult
    }
}*/
/*
class FakeAcademyRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors)
    : AcademyDataSource {

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<CourseEntity>> =
                    localDataSource.getAllCourses()
            override fun shouldFetch(data: List<CourseEntity>?): Boolean =
                    data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> =
                    remoteDataSource.getAllCourses()
            public override fun saveCallResult(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponses) {
                    val course = CourseEntity(response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath)
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }
    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> =
            localDataSource.getBookmarkedCourses()
    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CourseWithModule> =
                    localDataSource.getCourseWithModules(courseId)
            override fun shouldFetch(courseWithModule: CourseWithModule?): Boolean =
                    courseWithModule?.mModules == null || courseWithModule.mModules.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                    remoteDataSource.getModules(courseId)
            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponses) {
                    val course = ModuleEntity(response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }
    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> =
                    localDataSource.getAllModulesByCourse(courseId)
            override fun shouldFetch(modules: List<ModuleEntity>?): Boolean =
                    modules == null || modules.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                    remoteDataSource.getModules(courseId)
            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponses) {
                    val course = ModuleEntity(response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }
    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> =
                    localDataSource.getModuleWithContent(moduleId)
            override fun shouldFetch(moduleEntity: ModuleEntity?): Boolean =
                    moduleEntity?.contentEntity == null
            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                    remoteDataSource.getContent(moduleId)
            override fun saveCallResult(contentResponse: ContentResponse) =
                    localDataSource.updateContent(contentResponse.content.toString(), moduleId)
        }.asLiveData()
    }
    override fun setCourseBookmark(course: CourseEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setCourseBookmark(course, state) }
    override fun setReadModule(module: ModuleEntity) =
            appExecutors.diskIO().execute { localDataSource.setReadModule(module) }
}
*/
class FakeAcademyRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors)
    : AcademyDataSource {

    override fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object : NetworkBoundResource<PagedList<CourseEntity>, List<CourseResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<CourseEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllCourses(), config).build()
            }
            override fun shouldFetch(data: PagedList<CourseEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteDataSource.getAllCourses()
            }
            public override fun saveCallResult(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (i in courseResponses.indices) {
                    val response = courseResponses[i]
                    val course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath)
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedCourses(), config).build()
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CourseWithModule> =
                    localDataSource.getCourseWithModules(courseId)

            override fun shouldFetch(courseWithModule: CourseWithModule?): Boolean =
                    courseWithModule?.mModules == null || courseWithModule.mModules.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                    remoteDataSource.getModules(courseId)

            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponses) {
                    val course = ModuleEntity(response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)

                    moduleList.add(course)
                }

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> =
                    localDataSource.getAllModulesByCourse(courseId)
            override fun shouldFetch(modules: List<ModuleEntity>?): Boolean =
                    modules == null || modules.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                    remoteDataSource.getModules(courseId)
            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponses) {
                    val course = ModuleEntity(response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> =
                    localDataSource.getModuleWithContent(moduleId)
            override fun shouldFetch(moduleEntity: ModuleEntity?): Boolean =
                    moduleEntity?.contentEntity == null
            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                    remoteDataSource.getContent(moduleId)
            override fun saveCallResult(contentResponse: ContentResponse) =
                    localDataSource.updateContent(contentResponse.content.toString(), moduleId)
        }.asLiveData()
    }
    override fun setCourseBookmark(course: CourseEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setCourseBookmark(course, state) }
    override fun setReadModule(module: ModuleEntity) =
            appExecutors.diskIO().execute { localDataSource.setReadModule(module) }
}
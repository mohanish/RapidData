package com.rapidata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rapidata.box.LoadingState
import com.rapidata.box.apimodels.SearchValue
import com.rapidata.box.data.BaseRepository
import com.rapidata.ui.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    private lateinit var viewModel: SearchViewModel

    private lateinit var loadingState: LiveData<LoadingState>
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val searchValues = listOf(
        SearchValue(
            id = 1,
            title = "Android",
            name = "Android One",
            thumbnail = "",
            base64Encoding = "",
            imageWebSearchUrl = "",
            height = 10,
            width = 10,
            thumbnailHeight = 400,
            thumbnailWidth = 100,
            url = ""
        ),
        SearchValue(
            id = 2,
            title = "Android",
            name = "Android One",
            thumbnail = "",
            base64Encoding = "",
            imageWebSearchUrl = "",
            height = 10,
            width = 10,
            thumbnailHeight = 400,
            thumbnailWidth = 100,
            url = ""
        )
    )

    @Mock
    private lateinit var repo: BaseRepository

    @Spy
    private val searchValuesLiveData: MutableLiveData<List<SearchValue>> = MutableLiveData()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        `when`(repo.searchResultData).thenReturn(searchValuesLiveData)
        viewModel = SearchViewModel(repo)
        loadingState = viewModel.loadingState
    }

    @ExperimentalCoroutinesApi
    @Test
    fun refreshData() {
        coroutineTestRule.runBlockingTest {
            var loadingState = LoadingState.Status.RUNNING
            Assert.assertNotNull(loadingState)
//            verify(repo).refresh(1, "Android")
            loadingState = LoadingState.Status.SUCCESS
            Assert.assertNotNull(loadingState)
        }
    }
}
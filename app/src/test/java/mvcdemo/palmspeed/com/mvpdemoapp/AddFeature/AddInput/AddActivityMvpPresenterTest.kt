package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Prashant on 26-11-2017.
 */
class AddActivityMvpPresenterTest {

  @Mock
  private val mAddActivityMvpView: AddActivityMvpContract.View? = null

  @Mock
  private var mAddActivityMvpInteractor: AddActivityMvpContract.Interactor? = null

  private var mAddActivityMvpPresenter: AddActivityMvpPresenter? = null

  @Before
  fun setUp() {
    // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
    // inject the mocks in the test the initMocks method needs to be called.
    MockitoAnnotations.initMocks(this)

    // Get a reference for Presenter
    mAddActivityMvpPresenter = AddActivityMvpPresenter()
    mAddActivityMvpPresenter?.start()
    mAddActivityMvpPresenter?.attachView(mAddActivityMvpView as AddActivityMvpContract.View)

    // Get the reference for Interactor
    //mAddActivityMvpInteractor = AddActivityMvpInteractor()
  }

  @After
  fun tearDown() {
  }

  @Test
  fun additionTestSuccess() {

    // Given0
    val firstNumber = "10"
    val secondNumber = "20"
    val additionResult = "30"

    // When
    `when`(mAddActivityMvpInteractor?.addTwoNumbers(firstNumber, secondNumber))
        .thenReturn(Single.just(Integer.parseInt(additionResult)))

    // Then
    mAddActivityMvpPresenter?.addTwoNumbers(firstNumber = firstNumber, secondNumber = secondNumber)
    verify(mAddActivityMvpView)?.displayProgress()
    verify(mAddActivityMvpView)?.goToSuccessPage(additionResult)

  }
}
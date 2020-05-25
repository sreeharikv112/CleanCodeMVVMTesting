package com.dev.ccodetest.screens.login


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.ccodetest.R
import com.dev.ccodetest.models.login.AllPeople
import com.dev.ccodetest.models.login.AllPeopleResult
import com.dev.ccodetest.platform.BaseFragment
import com.dev.ccodetest.platform.BaseViewModelFactory
import com.dev.ccodetest.platform.LiveDataWrapper
import com.dev.ccodetest.screens.login.helper.LoginRecyclerViewAdapter
import com.dev.ccodetest.screens.login.uc.LoginUseCase
import com.dev.ccodetest.screens.login.vm.LoginActivityViewModel
import kotlinx.android.synthetic.main.fragment_main_activity.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

/**
 * Login Fragment.
 * Handles UI.
 * Fires rest api initiation.
 */
class LoginActivityFragment : BaseFragment() {

    companion object{
        fun getMainActivityFragment() = LoginActivityFragment()
    }

    //---------------Class variables---------------//

    val mLoginUseCase : LoginUseCase by inject()
    private val mBaseViewModelFactory : BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO,mLoginUseCase)
    private val TAG = LoginActivityFragment::class.java.simpleName
    val mDemoParam = "1"
    lateinit var mRecyclerViewAdapter: LoginRecyclerViewAdapter

    private val mViewModel : LoginActivityViewModel by lazy {
        ViewModelProviders.of(this,mBaseViewModelFactory)
            .get(LoginActivityViewModel::class.java)    }

    //---------------Life Cycle---------------//

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Start observing the targets
        this.mViewModel.mAllPeopleResponse.observe(this, this.mDataObserver)
        this.mViewModel.mLoadingLiveData.observe(this, this.loadingObserver)

        mRecyclerViewAdapter =
            LoginRecyclerViewAdapter(
                activity!!,
                arrayListOf()
            )
        landingListRecyclerView.adapter = mRecyclerViewAdapter
        landingListRecyclerView.layoutManager = LinearLayoutManager(activity!!)

        this.mViewModel.requestLoginActivityData(mDemoParam)

    }

    //---------------Observers---------------//
    private val mDataObserver = Observer<LiveDataWrapper<AllPeople>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for http request
                logD(TAG,"LiveDataResult.Status.ERROR = ${result.response}")
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                logD(TAG,"LiveDataResult.Status.SUCCESS = ${result.response}")
                val mainItemReceived = result.response as AllPeople
                val  listItems =  mainItemReceived.results as ArrayList<AllPeopleResult>
                processData(listItems)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_main_activity

    /**
     * Handle success data
     */
    private fun processData(listItems: ArrayList<AllPeopleResult>) {
        logD(TAG,"processData called with data ${listItems.size}")
        logD(TAG,"processData listItems =  ${listItems}")

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mRecyclerViewAdapter.updateListItems(listItems)
        }
    }

    /**
     * Hide / show loader
     */
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide progress bar
        if(visible){
            progress_circular.visibility = View.VISIBLE
        }else{
            progress_circular.visibility = View.INVISIBLE
        }
    }
}

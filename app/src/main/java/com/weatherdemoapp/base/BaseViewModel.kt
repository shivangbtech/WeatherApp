package com.weatherdemoapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.weatherdemoapp.component.SingleEvent
import com.weatherdemoapp.component.SingleLiveEvent
import com.weatherdemoapp.entity.Entity

open class BaseViewModel : ViewModel() {

    /**
     * Live data to handle error
     */
    private var errorLiveData = MediatorLiveData<SingleEvent<Entity.ErrorEntity>>()


    val mErrorLiveData: LiveData<SingleEvent<Entity.ErrorEntity>>
        get() = errorLiveData

    /**
     * Live data to handle loading
     */
    private var loadingLiveData = SingleLiveEvent<Boolean>()

    val mLoadingLiveData: SingleLiveEvent<Boolean>
        get() = loadingLiveData

    /**
     * Method call to handle loading
     */
    fun showLoading(show: Boolean) {
        loadingLiveData.postValue(show)
    }

    protected fun handleError(error: Entity.ErrorEntity) {
        errorLiveData.postValue(SingleEvent(error))
    }
}
package com.weatherdemoapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.weatherdemoapp.BR
import com.weatherdemoapp.R
import com.weatherdemoapp.base.BaseViewModel
import com.weatherdemoapp.component.SingleLiveEvent
import com.weatherdemoapp.model.Todo
import com.weatherdemoapp.model.WeatherDetailItem
import com.weatherdemoapp.model.WeatherDetailsList
import com.weatherdemoapp.network.ResultState
import com.weatherdemoapp.repository.WeatherRepository
import com.weatherdemoapp.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tatarka.bindingcollectionadapter2.ItemBinding


/**
 * Created by shivanggoel
 */
class WeatherListViewModel : BaseViewModel() {

    val weatherDetailsItems: ObservableArrayList<WeatherDetailItem> = ObservableArrayList()
    val weatherDetailsItemBinding: ItemBinding<WeatherDetailItem> = ItemBinding.of(BR.item, R.layout.list_item_weather)
    val showError = ObservableBoolean()
    private val todoRepository: WeatherRepository = WeatherRepositoryImpl()
    internal val reloadClick = SingleLiveEvent<Void>()
    val error = ObservableField<String>()

    init {
        showError.set(false)
        getWeatherDetailsList("201301")
    }

    fun getWeatherDetailsList(zip:String) {
        showLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getWeatherDetailList(zip).let {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultState.Success -> {
                            showError.set(false)
                            showLoading(false)
                            weatherDetailsItems.addAll(it.data.list)
                        }
                        is ResultState.Error -> {
                            error.set(it.errorResponse.errorMessage)
                            showLoading(false)
                            showError.set(true)
//                            Timber.e(it.throwable)
                        }
                    }
                }
            }
        }
    }

    fun reloadClick() {
        reloadClick.call()
    }
}
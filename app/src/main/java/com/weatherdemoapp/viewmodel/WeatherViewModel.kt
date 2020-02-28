package com.weatherdemoapp.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.weatherdemoapp.base.BaseViewModel
import com.weatherdemoapp.component.SingleLiveEvent
import com.weatherdemoapp.model.Weather
import com.weatherdemoapp.network.ResultState
import com.weatherdemoapp.repository.WeatherRepository
import com.weatherdemoapp.repository.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by shivanggoel on 28,February,2020
 */
class WeatherViewModel : BaseViewModel() {

    val weatherDetail: ObservableField<Weather> = ObservableField()
    val showError = ObservableBoolean()
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()

    val error = ObservableField<String>()

    val reloadClick = SingleLiveEvent<Void>()
    val searchClick = SingleLiveEvent<Void>()
    val forecastClick = SingleLiveEvent<Void>()

    val clearUI = SingleLiveEvent<Void>()

    init {
        showError.set(false)
        getWeatherDetail("201301")
    }

    fun getWeatherDetail(zip:String) {
        showLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getWeatherDetail(zip).let {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultState.Success -> {
                            showError.set(false)
                            showLoading(false)
                            it.data.mainWeather = ""
                                if(it.data.weatherItem!=null && it.data.weatherItem.size> 0){
                                it.data.mainWeather =it.data.weatherItem[0].main
                            }

                            weatherDetail.set(it.data)
                        }
                        is ResultState.Error -> {
                            error.set(it.errorResponse.errorMessage)
                            showLoading(false)
                            showError.set(true)
                            if(it.errorResponse.errorCode == 404){
                                clearUI.call()
                            }
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

    fun searchClick() {
        searchClick.call()
    }

    fun forecastClick() {
        forecastClick.call()
    }
}
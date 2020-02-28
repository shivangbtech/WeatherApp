package com.weatherdemoapp.repository

import com.weatherdemoapp.base.BaseRepositoryImpl
import com.weatherdemoapp.common.Config
import com.weatherdemoapp.model.Todo
import com.weatherdemoapp.model.Weather
import com.weatherdemoapp.model.WeatherDetailsList
import com.weatherdemoapp.network.DataServiceFactory
import com.weatherdemoapp.network.ResultState

/**
 * Created by shivanggoel on 19,February,2020
 */
class WeatherRepositoryImpl : BaseRepositoryImpl(), WeatherRepository {

    override suspend fun getWeatherDetail(zip: String): ResultState<Weather> {
        return try {
            ResultState.Success(DataServiceFactory.weatherService.getWeatherDetails(zip, Config.UNIT_METRIC, Config.APP_ID))
        } catch (e: Exception) {
            handleErrorReturn(e) as ResultState<Weather>
        }
    }

    override suspend fun getWeatherDetailList(zip: String): ResultState<WeatherDetailsList> {
        return try {
            ResultState.Success(DataServiceFactory.weatherService.getWeatherDetailsList("https://samples.openweathermap.org/data/2.5/forecast/daily", zip, Config.APP_ID))
        } catch (e: Exception) {
            handleErrorReturn(e) as ResultState<WeatherDetailsList>
        }
    }


}
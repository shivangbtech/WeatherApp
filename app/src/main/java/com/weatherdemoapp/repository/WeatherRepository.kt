package com.weatherdemoapp.repository

import com.weatherdemoapp.base.BaseRepository
import com.weatherdemoapp.model.Todo
import com.weatherdemoapp.model.Weather
import com.weatherdemoapp.model.WeatherDetailsList
import com.weatherdemoapp.network.ResultState

/**
 * Created by shivanggoel on 19,February,2020
 */
interface WeatherRepository: BaseRepository {

    suspend fun getWeatherDetail(zip:String): ResultState<Weather>

    suspend fun getWeatherDetailList(zip:String): ResultState<WeatherDetailsList>

}
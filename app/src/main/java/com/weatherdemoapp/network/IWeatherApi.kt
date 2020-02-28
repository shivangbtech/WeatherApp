package com.weatherdemoapp.network

import com.weatherdemoapp.model.Weather
import com.weatherdemoapp.model.WeatherDetailsList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by shivanggoel on 28,February,2020
 */
interface IWeatherApi {

    @GET("weather")
    suspend fun getWeatherDetails(
        @Query("q") zip: String,
        @Query("units") units: String,
        @Query("APPID") appId: String
    ): Weather

    @GET
    suspend fun getWeatherDetailsList(
        @Url url:String,
        @Query("q") zip: String,
        @Query("appid") appId: String
    ): WeatherDetailsList
}
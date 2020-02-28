package com.weatherdemoapp.network

/**
 * Created by shivanggoel on 19,February,2020
 */
object DataServiceFactory {

    val weatherService: IWeatherApi = NetworkClient.getNetworkClient().create(IWeatherApi::class.java)

}
package com.weatherdemoapp.model

/**
 * Created by shivanggoel on 28,February,2020
 */


data class Weather(var weatherItem: ArrayList<WeatherItem>,
                   var main:Main,
                   var wind:Wind,
                   var sys:Sys,
                   var name:String,
                   var mainWeather:String = "")

data class WeatherItem(var main:String,
                       var description:String,
                       var icon:String)

data class Main(var temp:String = "",
                var feels_like:String,
                var temp_min:String,
                var temp_max:String,
                var pressure:String,
                var humidity:String)

data class Wind(var speed:Double)

data class Sys(var country:String)

data class WeatherDetailsList(var list:List<WeatherDetailItem>)

data class WeatherDetailItem(var dt: Long,
                             var speed: String,
                             var temp:Temp,
                             var humidity: String)
data class Temp(var day:String)
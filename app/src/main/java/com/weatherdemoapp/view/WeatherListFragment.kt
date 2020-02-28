package com.weatherdemoapp.view

import androidx.lifecycle.Observer
import com.weatherdemoapp.BR
import com.weatherdemoapp.R
import com.weatherdemoapp.base.BaseFragment
import com.weatherdemoapp.databinding.FragmentWeatherListBinding
import com.weatherdemoapp.navigation.NavigationContract
import com.weatherdemoapp.viewmodel.WeatherListViewModel

/**
 * Created by shivanggoel
 */
class WeatherListFragment : BaseFragment<FragmentWeatherListBinding, WeatherListViewModel>() {

    override fun provideViewModelClass(): Class<WeatherListViewModel> {
        return WeatherListViewModel::class.java
    }

    override fun layoutId(): Int = R.layout.fragment_weather_list

    override val bindingVariable: Int
        get() = BR.viewmodel

    override val navigationContract: NavigationContract?
        get() = object : NavigationContract {

            override fun subscribeNetworkResponse() {
                super.subscribeNetworkResponse()
            }

            override fun subscribeNavigationEvent() {
                super.subscribeNavigationEvent()
//                viewModel.reloadClick.observe(this@WeatherListFragment, Observer {
//                    viewModel.showError.set(false)
//                    viewModel.showTodoList()
//                })
            }
        }
}
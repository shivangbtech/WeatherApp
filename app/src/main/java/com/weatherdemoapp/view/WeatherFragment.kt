package com.weatherdemoapp.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.weatherdemoapp.BR
import com.weatherdemoapp.R
import com.weatherdemoapp.base.BaseFragment
import com.weatherdemoapp.databinding.FragmentWeatherBinding
import com.weatherdemoapp.navigation.NavigationContract
import com.weatherdemoapp.navigation.TransitionManager
import com.weatherdemoapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by shivanggoel on 28,February,2020
 */
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>() {

    override fun provideViewModelClass(): Class<WeatherViewModel> {
        return WeatherViewModel::class.java
    }

    override fun layoutId(): Int = R.layout.fragment_weather

    override val bindingVariable: Int
        get() = BR.viewmodel

    override val navigationContract: NavigationContract?
        get() = object : NavigationContract {

            override fun subscribeNetworkResponse() {
                super.subscribeNetworkResponse()
                viewModel.clearUI.observe(this@WeatherFragment, Observer {
                    tv_temp.text = ""
                    tv_city.text = ""
                    tv_temp_max.text = ""
                    tv_temp_min.text = ""
                    tv_wind.text = ""
                })
            }

            override fun subscribeNavigationEvent() {
                super.subscribeNavigationEvent()
//                viewModel.reloadClick.observe(this@WeatherListFragment, Observer {
//                    viewModel.showError.set(false)
//                    viewModel.showTodoList()
//                })

                viewModel.searchClick.observe(this@WeatherFragment, Observer {
                    viewModel.showError.set(false)
                    viewModel.getWeatherDetail(edt_zip.text.toString())
                })

                viewModel.forecastClick.observe(this@WeatherFragment, Observer {

                    TransitionManager.replaceFragment(
                        activity as AppCompatActivity,
                        WeatherListFragment(),
                        R.id.fl_container_todo,
                        false,
                        TransitionManager.TransitionAnimation.TRANSITION_NONE
                    )
                })
            }
        }
}
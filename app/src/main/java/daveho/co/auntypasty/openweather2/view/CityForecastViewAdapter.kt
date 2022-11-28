package daveho.co.auntypasty.openweather2.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import daveho.co.auntypasty.openweather2.R
import daveho.co.auntypasty.openweather2.databinding.ForecastListItemBinding
import daveho.co.auntypasty.openweather2.model.Direction
import daveho.co.auntypasty.openweather2.viewmodel.WeatherModel

class CityForecastViewAdapter:
    RecyclerView.Adapter<CityForecastViewAdapter.ForecastViewHolder>() {

    private val weatherList = ArrayList<WeatherModel>()
    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ForecastViewHolder {

        context = viewGroup.context

        val layoutInflator =  LayoutInflater.from(viewGroup.context)
        val binding: ForecastListItemBinding = DataBindingUtil.inflate(layoutInflator, R.layout.forecast_list_item, viewGroup, false)

        return ForecastViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(forecastViewHolder: ForecastViewHolder, position: Int) {

        val weatherForecast = weatherList[position]
        forecastViewHolder.bind(weatherForecast)
    }

    fun setForecastList(weathers: List<WeatherModel>) {
        weatherList.clear()
        weatherList.addAll(weathers)

    }

    class ForecastViewHolder(private val binding: ForecastListItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: WeatherModel) {
            Log.d(TAG, "City weather: " + weather.description)
            with(binding) {
                time.text = weather.date
                windSpeed.text = weather.windSpeed
                val temperature = weather.temperature
                binding.temperature.text = context.getString(R.string.degrees_c, temperature)
                description.text = weather.description

                when (weather.windDirection) {
                    (Direction.NORTH) -> binding.directionIcon.setImageResource(R.drawable.arrow_n)
                    (Direction.NORTHEAST) -> binding.directionIcon.setImageResource(R.drawable.arrow_ne)
                    (Direction.EAST) -> binding.directionIcon.setImageResource(R.drawable.arrow_e)
                    (Direction.SOUTHEAST) -> binding.directionIcon.setImageResource(R.drawable.arrow_se)
                    (Direction.SOUTH) -> binding.directionIcon.setImageResource(R.drawable.arrow_s)
                    (Direction.SOUTHWEST) -> binding.directionIcon.setImageResource(R.drawable.arrow_sw)
                    (Direction.WEST) -> binding.directionIcon.setImageResource(R.drawable.arrow_w)
                    (Direction.NORTHWEST) -> binding.directionIcon.setImageResource(R.drawable.arrow_nw)
                    else -> binding.directionIcon.setImageResource(R.drawable.arrow_n)
                }
            }

        }
    }

    companion object {
        private val TAG = CityListViewAdapter::class.java.simpleName
    }
}
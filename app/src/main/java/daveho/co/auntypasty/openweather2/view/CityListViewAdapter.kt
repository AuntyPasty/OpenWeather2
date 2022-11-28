package daveho.co.auntypasty.openweather2.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import daveho.co.auntypasty.openweather2.model.Direction
import daveho.co.auntypasty.openweather2.R
import daveho.co.auntypasty.openweather2.databinding.CityListItemBinding
import daveho.co.auntypasty.openweather2.viewmodel.CitySummaryModel

/**
 * List adapter for City search list items
 */
internal class CityListViewAdapter(private val clickListener: (CitySummaryModel) -> Unit) :
    RecyclerView.Adapter<CityListViewAdapter.CityViewHolder>() {

    private val citySummaryList = ArrayList<CitySummaryModel>()
    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CityViewHolder {

        context = viewGroup.context

        val layoutInflator =  LayoutInflater.from(viewGroup.context)
        val binding: CityListItemBinding = DataBindingUtil.inflate(layoutInflator, R.layout.city_list_item, viewGroup, false)

        return CityViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return citySummaryList.size
    }

    override fun onBindViewHolder(cityViewHolder: CityViewHolder, position: Int) {

        val citySummary = citySummaryList[position]
        cityViewHolder.bind(citySummary, clickListener)
    }

    fun setList(cities: List<CitySummaryModel>) {
        citySummaryList.clear()
        citySummaryList.addAll(cities)

    }

    class CityViewHolder(private val binding: CityListItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(citySummaryModel: CitySummaryModel, clickListener: (CitySummaryModel) -> Unit) {
            Log.d(TAG, "City Id: " + citySummaryModel.id)
            binding.cityName.text = citySummaryModel.cityName
            binding.country.text = citySummaryModel.country

            binding.time.text = citySummaryModel.weatherTime

            val temperature = citySummaryModel.celciusTemp
            binding.temperature.text = context.getString(R.string.degrees_c, temperature)
            binding.windSpeed.text = citySummaryModel.windSpeed

            binding.description.text = citySummaryModel.weatherDescription

            when (citySummaryModel.windDirection) {
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

            binding.listItemOuter.setOnClickListener {
                clickListener(citySummaryModel)
            }
        }
    }

    companion object {
        private val TAG = CityListViewAdapter::class.java.simpleName
    }
}

package daveho.co.auntypasty.openweather2.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import daveho.co.auntypasty.openweather2.R
import daveho.co.auntypasty.openweather2.databinding.ActivityMainBinding
import daveho.co.auntypasty.openweather2.viewmodel.CityListViewModel
import daveho.co.auntypasty.openweather2.viewmodel.CitySummaryModel

/**
* Main starting activity. Manages the actionbar which has a searchbox.
* Creates and commits a CityListFragment
* Text from the search box is passed to the fragment that it holds.
*/
@AndroidEntryPoint
class MainCityListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CityListViewAdapter
    private val cityListViewModel: CityListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cityList.addItemDecoration(ListDividerItemDecoration(this))

        binding.mainModel = cityListViewModel
        binding.lifecycleOwner = this

        setupSearchBar()
        initialiseCityListView()
    }

    override fun onBackPressed() {
        // If the back button is pressed when the search box is visible, use this button press
        // to hide the search box
        if (binding.searchContainer.visibility == View.VISIBLE) {
            hideSearchBox(true)
        } else {
            super.onBackPressed()
        }
    }

    private fun initialiseCityListView() {
        binding.cityList.layoutManager = LinearLayoutManager(this)
        adapter = CityListViewAdapter { selectedItem: CitySummaryModel -> listItemClicked(selectedItem) }
        binding.cityList.adapter = adapter
        displaySubscriberList()
    }

    private fun displaySubscriberList() {
        cityListViewModel.cityListData.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(citySummary: CitySummaryModel) {
        Toast.makeText(this, "Selected city is ${citySummary.cityName}", Toast.LENGTH_LONG).show()
        val forecastIntent = Intent(this, CityForecastActivity::class.java)
        forecastIntent.putExtra("city_id", citySummary.id)
        startActivity(forecastIntent)
    }


    private fun setupSearchBar() {
        // Stop the text entry from going fullscreen in landscape mode
        binding.searchBox.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI

        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(searchText: Editable) {
                // Make sure that there are at least two characters present before making a query
                cityListViewModel.searchCityWithString(searchText.toString())
            }
        })

        binding.searchIcon.setOnClickListener { hideSearchBox(false) }

        // Start with a search icon only
        hideSearchBox(true)

    }

    private fun hideSearchBox(hideBox: Boolean) {
        if (hideBox) {
            // Hide search box and show search icon and app title
            binding.searchBox.setText("")
            binding.searchBox.visibility = View.GONE
            binding.searchIcon.visibility = View.VISIBLE
            binding.appTitle.visibility = View.VISIBLE
            // Hide the keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.searchBox.windowToken, 0)
        } else {
            // Hide search icon and title, and show search box
            binding.searchIcon.visibility = View.GONE
            binding.appTitle.visibility = View.GONE
            binding.searchBox.visibility = View.VISIBLE
            binding.searchBox.requestFocus()
            // Show the keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.searchBox, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}
package com.example.myapplication.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.domain.models.Result
import com.example.myapplication.presentation.presenters.factories.MainFactory
import com.example.myapplication.presentation.presenters.impl.MainViewModel
import com.example.myapplication.presentation.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: MainFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        initView()
    }

    private fun initView() {
        adapter = CurrencyAdapter(this) { currency: String, price: Float ->
            viewModel.getPrices(currency, price)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        viewModel.ratesLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    progress_circular.visibility = View.GONE
                    adapter.updateList(result.data.prices)
                }
                is Result.Error -> {
                    progress_circular.visibility = View.GONE
                }
                is Result.Loading -> {
                    progress_circular.alpha = 0f
                    progress_circular.visibility = View.VISIBLE
                    progress_circular.animate().alpha(1f).start()
                }
            }
        })
        viewModel.getPrices()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

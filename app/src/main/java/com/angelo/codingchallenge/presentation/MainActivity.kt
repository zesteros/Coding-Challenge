package com.angelo.codingchallenge.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelo.codingchallenge.R
import com.angelo.codingchallenge.databinding.ActivityMainBinding
import com.angelo.codingchallenge.presentation.di.Injector
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val TAG: String =
        MainActivity::class.java.simpleName

    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as Injector).createPageSubcomponent().inject(this)
        mainViewModel =  ViewModelProvider(this, factory).get(MainViewModel::class.java)

        initRecyclerView()
    }

    fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CardAdapter()
        binding.recyclerView.adapter = adapter
        displayPage()
    }

    fun displayPage(){
        val responsePageLiveData =  mainViewModel.getPage()
        responsePageLiveData.observe(this, Observer {
            Log.i(TAG, "displayPage: $it")
            it?.let {
                adapter.setList(it.cards)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
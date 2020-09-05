package com.example.appreminder.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.databinding.ActivityMainBinding
import com.example.appreminder.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),MainAdapter.ItemListener {

    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter:MainAdapter

    private lateinit var dialogs: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        mainViewModel.vReminder.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {

                    it.data?.let { users -> renderList(users) }
                    binding.llData.visibility = View.VISIBLE
                    binding.relNoData.visibility = View.GONE

                }

                Status.ERROR -> {
                    binding.llData.visibility = View.GONE
                    binding.relNoData.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun renderList(lstreminder: List<Reminder>) {
        adapter.setData(lstreminder)
        adapter.notifyDataSetChanged()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this)
        binding.recyclerView.adapter = adapter

    }

    override fun onClicked(Id: Int) {

        Log.e("", "")
    }
}
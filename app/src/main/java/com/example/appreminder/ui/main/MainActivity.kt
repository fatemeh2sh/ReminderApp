package com.example.appreminder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appreminder.R
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.databinding.ActivityMainBinding
import com.example.appreminder.ui.insert.InsertActivity
import com.example.appreminder.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

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
                    it.data?.let { users -> renderList(users.reversed()) }
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


        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var item = adapter.getItemAt(viewHolder.adapterPosition)
                adapter.removeAt(viewHolder.adapterPosition)
                mainViewModel.delete(item.id)
                cancelAlarm(applicationContext,item.alarmId)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        binding.floatAdd.setOnClickListener {
            Intent(this, InsertActivity::class.java).apply {
                startActivity(this)
            }
        }

    }
    override fun onClicked(Id: Int) {

        Log.e("", "")
    }
}
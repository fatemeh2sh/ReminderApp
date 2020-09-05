package com.example.appreminder.ui.insert

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.databinding.ActivityInsertBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class InsertActivity : AppCompatActivity(),HourDialog.onTimeClick {

    private val insertViewModel : InsertViewModel by viewModels()
    private lateinit var binding : ActivityInsertBinding

    private var hour : Int = 0
    private var minute : Int = 0
    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.cardHour.setOnClickListener {
            HourDialog().show(supportFragmentManager,"DialogHour")
        }

        binding.calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            this.year = year
            this.month = month
            this.day = dayOfMonth
        }

        binding.floatOk.setOnClickListener {
            if(binding.title.text!!.isNotEmpty()){
                var time = getTime()
                var idAlarm = time.toInt()
                var item = getItemReminder(time,idAlarm)
                insertViewModel.insert(item)
                Handler(mainLooper).postDelayed({finish()},1000)
                Log.e("mmm",time.toString())}
            else{
                Toast.makeText(this,"Fill Title",Toast.LENGTH_SHORT).show()
            }
        }

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun getTime():Long{
        return Calendar.getInstance().let {
            it.timeInMillis = System.currentTimeMillis()
            if(year != 0 ) {
                it.set(Calendar.YEAR, year)
                it.set(Calendar.MONTH, month)
                it.set(Calendar.DAY_OF_MONTH, day)
            }
            if( hour != 0 || minute != 0) {
                it.set(Calendar.HOUR_OF_DAY, hour)
                it.set(Calendar.MINUTE, minute)
            }
            it.set(Calendar.SECOND,0)
            it.set(Calendar.MILLISECOND,0)

            it.timeInMillis
        }
    }

    private fun getItemReminder(t:Long,idAlarm:Int) : Reminder{
        return Reminder(
            alarmId = idAlarm,
            title = binding.title.text.toString(),
            note = binding.note.text.toString(),
            startDate = t
        )
    }

    override fun onClick(h: Int, m: Int) {
        hour = h
        minute = m
        if(hour != 0 || minute != 0)
            binding.tvHour.text = "$hour : $minute"
        Log.e("hh",h.toString())
        Log.e("mm",m.toString())
    }
}
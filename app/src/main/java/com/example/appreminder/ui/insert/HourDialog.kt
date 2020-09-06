package com.example.appreminder.ui.insert

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.appreminder.databinding.HourDialogBinding
import kotlinx.android.synthetic.main.hour_dialog.view.*
import java.lang.Exception

class HourDialog:DialogFragment() {

    interface onTimeClick{
        fun onClick(h:Int, m:Int)
    }

    lateinit var listener:onTimeClick
    private var hour : Int = 0
    private var minute : Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as onTimeClick
        }catch (e:Exception){
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  HourDialogBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btnOk.setOnClickListener {
            listener.onClick(hour,minute)
            dismiss()
        }

        view.btnCancel.setOnClickListener {
            dismiss()
        }

        view.timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            this.hour = hour
            this.minute = minute
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}
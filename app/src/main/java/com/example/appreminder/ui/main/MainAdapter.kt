package com.example.appreminder.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.databinding.ItemLayoutBinding
import com.example.appreminder.utils.convertTimeToDateString

class MainAdapter(
    private val arrayReminder: ArrayList<Reminder>,
    private val listener: ItemListener
): RecyclerView.Adapter<MainAdapter.holder>() {

    class holder(private val itemBinding: ItemLayoutBinding, private val listener: ItemListener)
        :RecyclerView.ViewHolder(itemBinding.root),View.OnClickListener{

        private lateinit var _reminder: Reminder

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Reminder){
            this._reminder = item
            itemBinding.textViewTitle.text = item.title
            itemBinding.textViewNote.text = item.note
            itemBinding.textViewDate.text = convertTimeToDateString(item.startDate)
        }

        override fun onClick(p0: View?) {
            listener.onClicked(_reminder.id)
        }
    }

    interface ItemListener {
        fun onClicked(Id: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val binding: ItemLayoutBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return holder(binding, listener)
    }

    override fun getItemCount(): Int = arrayReminder.size

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.bind(arrayReminder[position])
    }

    fun setData(lstReminder: List<Reminder>){
        this.arrayReminder.clear()
        this.arrayReminder.addAll(lstReminder)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        this.arrayReminder.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemAt(position: Int): Reminder {
        return this.arrayReminder[position]
    }
}
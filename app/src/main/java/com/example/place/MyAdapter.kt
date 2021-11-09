package com.example.place

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.place.databinding.FlashCardItemBinding

class MyAdapter(private val dataSet: Array<Array<String>>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(binding : FlashCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val englishTv: TextView = binding.englishTv
        val japaneseTv: TextView = binding.japaneseTv
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = FlashCardItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        //dataset[1] -> 英単語，dataset[6] -> 英単語に対する日本語
        viewHolder.englishTv.text = dataSet[position][1]
        viewHolder.japaneseTv.text = dataSet[position][6]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}

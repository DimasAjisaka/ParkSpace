package com.example.parkspace.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkspace.R
import com.example.parkspace.view.onboarding.OnBoarding

class OnBoardingSliderAdapter(private val onBoardings: List<OnBoarding>): RecyclerView.Adapter<OnBoardingSliderAdapter.onBoardingSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingSlideViewHolder {
        return onBoardingSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: onBoardingSlideViewHolder, position: Int) {
        holder.bind(onBoardings[position])
    }

    override fun getItemCount(): Int {
        return onBoardings.size
    }

    inner class onBoardingSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.title)
        private val desc = view.findViewById<TextView>(R.id.desc)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(onBoarding: OnBoarding){
            title.text = onBoarding.title
            desc.text = onBoarding.description
            imageIcon.setImageResource(onBoarding.icon)
        }
    }
}
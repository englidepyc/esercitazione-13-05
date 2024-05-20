package com.android.esercitazione24_05_06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val hotelList: ArrayList<Hotel>) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nome: TextView = view.findViewById(R.id.nome)
        val punteggio: TextView = view.findViewById(R.id.punteggio)
        val recensione: TextView = view.findViewById(R.id.recensioni)
        val posizione: TextView = view.findViewById(R.id.position)
        val starRatin: RatingBar = view.findViewById(R.id.ratingBar)
        val img: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hotel_item_list, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.nome.text = hotel.hotel_name
        holder.punteggio.text = hotel.rating_average.toString()
        holder.posizione.text = hotel.addressline1
        holder.recensione.text = "${hotel.number_of_reviews} recensioni."
        holder.starRatin.rating = hotel.star_rating

        holder.itemView.setOnClickListener {
            if(onClickListener!=null){
                onClickListener!!.onClick(position, hotel)
                notifyDataSetChanged()
            }
        }

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, hotel: Hotel)
    }
}
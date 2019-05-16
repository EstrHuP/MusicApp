package com.example.musicapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.model.Festivales
import kotlinx.android.synthetic.main.rowfestival.view.*

class FestivalAdapter(val context: Context,
                      val layout: Int
                      ): RecyclerView.Adapter<FestivalAdapter.ViewHolder>() {

        private var dataList: List<Festivales> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewlayout = layoutInflater.inflate(layout, parent, false)
            return ViewHolder(viewlayout, context)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        internal fun setFestivales(festivales: List<Festivales>) {
            this.dataList = festivales
            notifyDataSetChanged()
        }


        class ViewHolder(viewlayout: View, val context: android.content.Context) : RecyclerView.ViewHolder(viewlayout) {
            fun bind(dataItem: Festivales){
                // itemview es el item de diseño
                // al que hay que poner los datos del objeto dataItem
                itemView.tvIdFest.text = dataItem.idFest
                itemView.tvNombreFest.text = dataItem.nombre
                itemView.tvPrecioFest.text = "desde ${dataItem.precio}€"

                itemView.tag = dataItem
            }

        }

}
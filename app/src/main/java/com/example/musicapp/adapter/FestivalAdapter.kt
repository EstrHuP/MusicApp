package com.example.musicapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.model.Festivales
import com.example.musicapp.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.view.*
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
            itemView.tvNombreFest.text = dataItem.nombre
            itemView.tvPrecioFest.text = "desde ${dataItem.precio}0€"
            itemView.tvGeneroFest.text = dataItem.genero
            itemView.tvFechaFest.text = "${dataItem.fecha_inicio} al ${dataItem.fecha_final} de 2019"//Fecha sin convertidor porque me gusta así
            itemView.tvUbiFest.text = dataItem.ubicacion
            Picasso.with(context).load(dataItem.icono).into(itemView.ivFest)
            itemView.tag = dataItem
        }
    }
}
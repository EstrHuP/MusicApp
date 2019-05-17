package com.example.musicapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapp.model.Conciertos
import kotlinx.android.synthetic.main.rowconcierto.view.*
import kotlinx.android.synthetic.main.rowfestival.view.*

class ConciertoAdapter (val context: Context,
                      val layout: Int
): RecyclerView.Adapter<ConciertoAdapter.ViewHolder>() {

    private var dataList: List<Conciertos> = emptyList()

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

    internal fun setConciertos(conciertos: List<Conciertos>) {
        this.dataList = conciertos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: android.content.Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Conciertos){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            //itemView.tvIdFest.text = dataItem.idFest
            //itemView.tvFechaFest.text = Utils.formatStringDate(dataItem.fecha_inicio)
            itemView.tvNombreCon.text = dataItem.nombre
            itemView.tvHorarioCon.text = "a partir de las ${dataItem.horario} horas"
            itemView.tvPrecioCon.text = "desde ${dataItem.precio}0€"
            itemView.tvFechaCon.text = dataItem.fecha
            itemView.tvGeneroCon.text = dataItem.genero
            itemView.tag = dataItem
        }
    }
}
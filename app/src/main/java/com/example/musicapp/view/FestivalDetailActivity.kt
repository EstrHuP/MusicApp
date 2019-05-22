package com.example.musicapp.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.musicapp.R
import com.example.musicapp.model.Festivales
import com.example.musicapp.model.UbiMapFestival
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_festival_detail.*
import kotlinx.android.synthetic.main.rowfestival.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import uk.co.senab.photoview.PhotoViewAttacher

class FestivalDetailActivity : AppCompatActivity() {

    private lateinit var storage: FirebaseStorage
    private lateinit var database: FirebaseDatabase
    private lateinit var refFotosFest: DatabaseReference
    private lateinit var festival: Festivales
    private lateinit var ivCartel: ImageView
    private lateinit var mAttacher: PhotoViewAttacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festival_detail)

        festival = intent.getSerializableExtra("festival") as Festivales
        //Log.d("mapa", festival.toString())

        instancias()
        showFestivalPulsado()
        //doAsync { showFotos() }

        //Zoom
        ivCartel = findViewById(R.id.ivCartelDetailFest)
        mAttacher = PhotoViewAttacher(ivCartel)

    }

    private fun showFestivalPulsado(){
        tvNombreDetailFest.text = festival.nombre
        Picasso.get().load(festival.cartel).into(ivCartelDetailFest)
        tvFechaDetailFest.text = "${festival.fecha_inicio} al ${festival.fecha_final} de 2019"
        tvPrecioDetailFest.text = "desde ${festival.precio}0€"
        tvUbiDetailFest.text = festival.ubicacion
        tvGeneroDetailFest.text = festival.genero
        doAsync {
            getFotos()
        }
    }

    fun onClickVerMapFest(view: View){
        val intent = Intent(this, FestivalMapsActivity::class.java)
        intent.putExtra("festival", festival)
        startActivity(intent)
        //val intent = Intent(this, FestivalMapsActivity::class.java)
        //intent.putExtra("festivalm", festivalm)
        //startActivity(intent)
    }

    private fun showFotos(){
        if (festival.fotos != null){
            for (foto in festival.fotos!!){
                val img = ImageView(this)
                Picasso.get().load(foto).into(img)
                linearFotos.addView(img)
                Log.d("fotos", foto)
            }
        }
    }

    private fun getFotos(){
        refFotosFest = database.getReference("festival/${festival.fotos}/")
        refFotosFest.addListenerForSingleValueEvent(object : ValueEventListener{
             override fun onDataChange(dataSnapshot: DataSnapshot){
                dataSnapshot.children.forEach {
                    Log.d("fotillus", it.toString())
                    val festivales = it.getValue(Festivales::class.java)
                    if(festivales != null){
                       for(foto in festivales.fotos) {
                           val img = ImageView(this@FestivalDetailActivity)
                           Picasso.get().load(foto).into(img)
                           linearFotos.addView(img)
                       }
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                toast("NO HA FUNCHISCAO")
            }
        })

    }

    private fun instancias(){
        database = FirebaseDatabase.getInstance()//DataBase
        storage = FirebaseStorage.getInstance()//Storage
    }

//    fun onClickVerMapaFest(view: View){
//       // startActivity(Intent(this, FestivalMapsActivity::class.java))
//        var festivalmapa = view.tag as Festivales
//        val intent = Intent(this, FestivalDetailActivity::class.java)
//        intent.putExtra("festivalmapa", festivalmapa)
//        startActivity(intent)
//    }

}

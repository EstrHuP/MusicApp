package com.example.musicapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.musicapp.adapter.FestivalAdapter
import com.example.musicapp.model.Festivales
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_festival.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlin.math.log

class FestivalActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Esther"
    }
    private lateinit var adapter: FestivalAdapter
    private lateinit var festivales: ArrayList<Festivales>
    private lateinit var refFestivales: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festival)

        getDataBase()

        doAsync { getFestivales()
            uiThread {

             }
        }
    }

    private fun getFestivales(){
        refFestivales = database.getReference("festivales")
        rvFestival.layoutManager = LinearLayoutManager(this)
        festivales = ArrayList()
        adapter = FestivalAdapter(this,R.layout.rowfestival)
        rvFestival.adapter=adapter

        refFestivales.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e(TAG, dataSnapshot.childrenCount.toString())
                festivales.clear()
                for (dataSnapshothijo in dataSnapshot.children) {
                    val festival = dataSnapshothijo.getValue(Festivales::class.java)
                    festivales.add(festival!!)
                }
                adapter.setFestivales(festivales)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e(TAG, "Error de lectura.", error.toException())
            }
        })
    }

    private fun getDataBase(){
        database = FirebaseDatabase.getInstance()
    }
}

package com.example.musicapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.musicapp.adapter.FestivalAdapter
import com.example.musicapp.model.Festivales
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_festival.*

class FestivalActivity : AppCompatActivity() {
    private lateinit var adapter: FestivalAdapter
    private lateinit var festivales: ArrayList<Festivales>
    private lateinit var refFestivales: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festival)

        getInstance()
        showFestivales()
    }

    private fun showFestivales() {
        adapter = FestivalAdapter(this, R.layout.rowfestival)
        rvFestival.layoutManager = LinearLayoutManager(this)
        festivales = ArrayList()
        rvFestival.adapter = adapter

        refFestivales.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("AYUYA", dataSnapshot.childrenCount.toString())
                festivales.clear()
                for (dataSnapshothijo in dataSnapshot.children) {
                    val festival = dataSnapshothijo.getValue(Festivales::class.java)
                    festivales.add(festival!!)
                }
                adapter.setFestivales(festivales)

            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("AYUYA", "Error de lectura.", error.toException())
            }
        })
    }

    private fun getInstance(){
        val database = FirebaseDatabase.getInstance()
        refFestivales = database.getReference("festivales")

    }

}

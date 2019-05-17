package com.example.musicapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.musicapp.adapter.ConciertoAdapter
import com.example.musicapp.adapter.FestivalAdapter
import com.example.musicapp.model.Conciertos
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_concierto.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConciertoActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Esther"
    }

    private lateinit var adapter: ConciertoAdapter
    private lateinit var conciertos: ArrayList<Conciertos>
    private lateinit var refConciertos: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concierto)

        getDataBase()

        doAsync { getConciertos()
            uiThread {

            }
        }
    }
    private fun getConciertos(){
        refConciertos = database.getReference("conciertos")
        rvConcierto.layoutManager = LinearLayoutManager(this)
        conciertos = ArrayList()
        adapter = ConciertoAdapter(this,R.layout.rowconcierto)
        rvConcierto.adapter=adapter

        refConciertos.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e(FestivalActivity.TAG, dataSnapshot.childrenCount.toString())
                conciertos.clear()
                for (dataSnapshothijo in dataSnapshot.children) {
                    val concierto = dataSnapshothijo.getValue(Conciertos::class.java)
                    conciertos.add(concierto!!)
                }
                adapter.setConciertos(conciertos)
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e(FestivalActivity.TAG, "Error de lectura.", error.toException())
            }
        })
    }

    private fun getDataBase(){
        database = FirebaseDatabase.getInstance()
    }
}

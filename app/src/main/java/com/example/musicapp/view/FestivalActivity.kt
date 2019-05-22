package com.example.musicapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import com.example.musicapp.R
import com.example.musicapp.adapter.FestivalAdapter
import com.example.musicapp.model.Festivales
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_festival.*
import kotlinx.android.synthetic.main.activity_festival_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FestivalActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    companion object {
        const val TAG = "Esther"
    }

    private lateinit var storage: FirebaseStorage
    private lateinit var adapter: FestivalAdapter
    private lateinit var festivales: ArrayList<Festivales>
    private lateinit var refFestivales: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var searchView: SearchView
//    private var btnUbiFest: ImageButton = findViewById(R.id.btnUbiMapaFest)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festival)

        storage = FirebaseStorage.getInstance()//Storage

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
        adapter = FestivalAdapter(this, R.layout.rowfestival)
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

    fun onClickFestival(view: View){
        var festival = view.tag as Festivales
        val intent = Intent(this, FestivalDetailActivity::class.java)
        intent.putExtra("festival", festival)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.filtro_festivales, menu)
        // ***** <Filtro> ****
        val searchItem = menu.findItem(R.id.sbMain)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        // ***** </Filtro> ****

        return true
    }

    // ***** <Filtro> ****
    override fun onQueryTextChange(query: String): Boolean {
        adapter.setFestivales(festivales.filter { festivales -> festivales.nombre.toLowerCase().contains(query.toLowerCase()) } )
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
// ***** </Filtro> ****
}

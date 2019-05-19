package com.example.musicapp.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.ImageView
import com.example.musicapp.R
import com.example.musicapp.model.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.robertlevonyan.components.picker.PickerDialog
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        const val TAG="MusicApp"
    }
    private lateinit var pickerDialog: PickerDialog
    private lateinit var ivAvatar: ImageView
    private var usuario: Usuarios? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut() //Usuario logout
            }
            R.id.nav_info -> {
                acercade()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun acercade(){
        alert("Trabajo Fin de Ciclo realizado por: Esther Huecas Pérez en el año 2019", "MusicApp"){
            yesButton {  }
        }.show()
    }

    fun onClickFestivales(view: View){
        startActivity(Intent(this, FestivalActivity::class.java))
        //var festival = view.tag as Festivales
        //val intent = Intent(this, FestivalActivity::class.java)
        //intent.putExtra("festival", (view.tag as Festivales))
        //startActivity(intent)
    }

    fun onClickConcierto(view: View){
        startActivity(Intent(this, ConciertoActivity::class.java))
    }

}

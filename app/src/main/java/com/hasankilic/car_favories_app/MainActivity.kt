package com.hasankilic.car_favories_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hasankilic.car_favories_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {//baglama işlemi yapılcak

        //inflater
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.art_menu,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//menuye tıklanırsa ne yapacak

        if (item.itemId==R.id.add_art_item){

            val intent=Intent(this@MainActivity,ArtActivity::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }

}
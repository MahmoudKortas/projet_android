package com.example.miniprojetunsplash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplicationtp5555.HeroesAdapter
import com.example.myapplicationtp5555.HorizontalViewModel
import com.example.myapplicationtp5555.VerticalAdapter
import com.example.myapplicationtp5555.VerticalViewModel
import com.example.myapplicationtp5555.models.UnsplashPhoto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vertical list
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.itemAnimator = null
        main_recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerVertical = findViewById<RecyclerView>(R.id.main_recycler_view)

        //horizontal list
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)



        val model: HorizontalViewModel =
            ViewModelProviders.of(this).get(HorizontalViewModel::class.java)

        val modelVertical: VerticalViewModel =
            ViewModelProviders.of(this).get(VerticalViewModel::class.java)


        //
        model.getHeroes()?.observe(this,
            { heroList ->
                var heroesAdapter =
                    HeroesAdapter(
                        unsplashPhotos = heroList as List<UnsplashPhoto>,
                        this@MainActivity
                    )
                listRecyclerView.adapter = heroesAdapter
                heroesAdapter.itemClickListener = { position, name ->
                    val intent = Intent(this, PictureActivity::class.java).apply {
                        putExtra("Data", name)
                    }
                    startActivity(intent)
                }
            })



        modelVertical.getHeroes()?.observe(this,
            { heroList ->
                var verticalAdapter =
                    VerticalAdapter(
                        unsplashPhotos = heroList as List<UnsplashPhoto>,
                        this@MainActivity
                    )


                recyclerVertical.adapter = verticalAdapter
                verticalAdapter.itemClickListener = { position, photo, name,smallDesc,longDesc,createAt,updateAt ->
                   /* Toast.makeText(this, "position: $position - name: $name", Toast.LENGTH_SHORT)
                        .show()*/

                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("Data0", photo,)
                        putExtra("Data1", name,)
                        putExtra("Data2", smallDesc,)
                        putExtra("Data3", longDesc,)
                        putExtra("Data4", createAt,)
                        putExtra("Data5", updateAt,)
                    }
                    startActivity(intent)
                }
            })
    }

}
package project.faist.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.faist.movieapp.R.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_movies)

        Log.d("testLogs", getString(string.Movies_onCreate))

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..200) {
            data.add(ItemsViewModel(drawable.show_pwd_icon, "Item $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val apiInterface = ApiInterface.create().getMovies("")
        //apiInterface.enqueue( Callback<ArrayList<ItemsViewModel>>())
        apiInterface.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//                Log.d("testLogs", "onResponse success ${response?.body()?.results}")

//                if(response?.body() != null)
//                    adapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
//                Log.d("testLogs", "onFailure : ${t?.message}")

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}
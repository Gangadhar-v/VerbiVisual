package com.example.verbivisual


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.verbivisual.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.addTextChangedListener {
            if(it!!.count()>0){
                binding.edlyt.error = null
            }
        }

        binding.button.setOnClickListener {
            if(binding.editText.text.toString().isNotEmpty()){
                getImages()
            }else{
                binding.edlyt.error = "this field is mandatory"
            }

        }


    }
    private fun getImages(){

        val q =binding.editText.text.toString()

        val retrofitService = RetrofitInstance.retrofitService.getImages(q=q)

       retrofitService.enqueue(object:Callback<Images>{
           override fun onResponse(call: Call<Images>, response: Response<Images>) {
               val responseBody = response.body()
               val images = responseBody?.images
               binding.tv.visibility = View.INVISIBLE
               val recyclerView = binding.recyclerView
               recyclerView.adapter = images?.let { ImageAdapter(it,this@MainActivity) }
               recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
           }

           override fun onFailure(call: Call<Images>, t: Throwable) {


               binding.tv.visibility = View.VISIBLE
               binding.tv.text = t.message.toString()


           }
       })
    }

}


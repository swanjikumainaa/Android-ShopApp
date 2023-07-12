package dev.wanjikode.myshop

import android.net.DnsResolver.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.wanjikode.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        fetchproduct()
    }
    fun fetchproduct(){
        var apiclient =ApiClient.buildClient(ApiInterface::class.java)
        var request = apiclient.getProducts()

        request.enqueue(object :retrofit2.Callback<ProductsResponse>{
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            )


        {
                if (response.isSuccessful){
                    var product = response.body()?.products
                    var productAdapter = ProductAdapter(product?: emptyList())
//                    binding.rvProduct.layoutManager=LinearLayoutManager(this@MainActivity)
                    binding.rvProduct.layoutManager=GridLayoutManager(this@MainActivity,2)
                    binding.rvProduct.adapter=productAdapter

                    Toast.makeText(
                        baseContext,
                        "fetched ${product?.size} product",
                        Toast.LENGTH_LONG
                    ).show()
                }else{
                    Toast.makeText(baseContext, response.errorBody()?.string(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call:retrofit2.Call<ProductsResponse>,t:
            Throwable){
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }

}
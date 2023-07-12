package dev.wanjikode.myshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.wanjikode.myshop.databinding.ProductsCardViewBinding
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ProductAdapter(var productList: List<Product>):RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       var bindingProductDisplay=ProductsCardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(bindingProductDisplay)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var myProducts= productList[position]
        var binding = holder.binding

        binding.tvtittle.text=myProducts.title
        binding.tvPrice.text= myProducts.price.toString()
        binding.tvDescription.text=myProducts.description

        Picasso
            .get()
            .load(myProducts.thumbnail)
            .resize(300,350)
            .transform(CropCircleTransformation())
            .centerCrop()
            .into(binding.ivProduct)


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

class ProductViewHolder(var binding: ProductsCardViewBinding): RecyclerView.ViewHolder(binding.root)
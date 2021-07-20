package com.s3enterprises.shoppingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsAdapter(private val products:List<Product>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private lateinit var onProductSelectedListener: (Product)->Unit

    inner class ViewHolder(private val mView: View):RecyclerView.ViewHolder(mView){
        private val imageView = mView.findViewById<ImageView>(R.id.product_image)
        private val productName = mView.findViewById<TextView>(R.id.product_name)
        private val productPrice = mView.findViewById<TextView>(R.id.product_price)

        fun setData(product: Product){
            productName.text = product.title
            productPrice.text = "$${product.price}"
            Glide.with(mView.context).load(product.image).into(imageView)
            mView.setOnClickListener { onProductSelectedListener(product) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(products[position])
    }

    override fun getItemCount() = products.size

    fun setOnProductClickListener(listener:(Product)->Unit){
        onProductSelectedListener = listener
    }
}
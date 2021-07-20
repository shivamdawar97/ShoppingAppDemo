package com.s3enterprises.shoppingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartRecyclerAdapter(private val cartItems:List<Product>):RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder>() {

    private lateinit var onRemoveClicked:(Int)->Unit

    inner class ViewHolder(mView: View):RecyclerView.ViewHolder(mView){
        private val image = mView.findViewById<ImageView>(R.id.cart_item_image)
        private val title = mView.findViewById<TextView>(R.id.cart_item_title)
        private val removeBtn = mView.findViewById<TextView>(R.id.remove_btn)
        private val price = mView.findViewById<TextView>(R.id.cart_item_price)

        fun setData(position:Int) = cartItems[position].let{ p ->
            Glide.with(image.context).load(p.image).into(image)
            title.text = p.title
            price.text = "$ ${p.price}"
            removeBtn.setOnClickListener {
                onRemoveClicked(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cart_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount() = cartItems.size

    fun setOnRemoveClickedListener(onRemoveClicked:(Int)->Unit){
        this.onRemoveClicked = onRemoveClicked
    }


}
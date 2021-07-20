package com.s3enterprises.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.s3enterprises.shoppingapp.databinding.FragmentCartBinding
import com.s3enterprises.shoppingapp.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private val viewModel by activityViewModels<MainActivityViewModel>()
    private val args by navArgs<CheckoutFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_checkout, container, false)
        binding.lifecycleOwner = this
        binding.model = viewModel
        binding.summryContainer.removeAllViews()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        if(args.product==null) {
            viewModel.cart.forEach {
                binding.summryContainer.addView(getView(it))
            }
            binding.price.text = "\$ ${viewModel.cartTotal.value}"
            binding.total.text = "\$ ${viewModel.cartTotal.value}"
        }
        else {
            val p = args.product!!
            binding.summryContainer.addView(getView(p))
            binding.price.text = "\$ ${p.price}"
            binding.total.text = "\$ ${p.price}"
        }

        binding.placeOrderButton.setOnClickListener {
            if(binding.address.isNullOrBlank())
                binding.deliveryAddress.error = "Please enter delivery address"
            else viewModel.placeOrder().also {
                Toast.makeText(context,"Order Placed",Toast.LENGTH_LONG).show()
                findNavController().navigate(findNavController().graph.startDestination)
            }
        }

        return binding.root
    }

    private fun getView(it:Product):View{
        val view = LayoutInflater.from(context).inflate(R.layout.card_cart_item,null)
        view.findViewById<TextView>(R.id.remove_btn).visibility = View.INVISIBLE
        view.findViewById<TextView>(R.id.cart_item_title).text = it.title
        view.findViewById<TextView>(R.id.cart_item_price).text = "$ ${it.price}"
        view.findViewById<ImageView>(R.id.cart_item_image).apply {
            Glide.with(context).load(it.image).into(this)
        }
        return view
    }

}
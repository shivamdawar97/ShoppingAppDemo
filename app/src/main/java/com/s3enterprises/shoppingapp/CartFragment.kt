package com.s3enterprises.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.s3enterprises.shoppingapp.databinding.FragmentCartBinding
import com.s3enterprises.shoppingapp.databinding.FragmentProductsBinding

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    private val viewModel by activityViewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        binding.lifecycleOwner = this
        binding.model = viewModel
        binding.cartRecycler.layoutManager = LinearLayoutManager(context)
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.checkoutButton.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
        }
        viewModel.cartCount.observeForever {
            val adapter = CartRecyclerAdapter(viewModel.cart)
            adapter.setOnRemoveClickedListener {
                viewModel.removeFromCart(it)
            }
            binding.cartRecycler.adapter = adapter

        }

        return binding.root
    }

}
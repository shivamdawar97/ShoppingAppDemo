package com.s3enterprises.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.s3enterprises.shoppingapp.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private lateinit var binding : FragmentProductsBinding
    private val viewModel by activityViewModels<MainActivityViewModel>()
    private var animated = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_products, container, false)
        binding.productsRecycler.layoutManager = GridLayoutManager(context,2)
        binding.productsRecycler.isNestedScrollingEnabled = false
        binding.lifecycleOwner = this
        binding.model = viewModel

        viewModel.products.observeForever {
            val adapter = ProductsAdapter(it)
            adapter.setOnProductClickListener { product ->
                val action = ProductsFragmentDirections.gotoProductDetailFragment(product)
                findNavController().navigate(action)
            }
            binding.cartIcon.setOnClickListener {
                findNavController().navigate(ProductsFragmentDirections.gotoCartFragment())
            }
            binding.productsRecycler.adapter = adapter
            if(!animated)
            binding.productsRecycler.animation = AnimationUtils.loadAnimation(context,R.anim.recycler_anim).also { animated = true }
        }
        return binding.root
    }
}
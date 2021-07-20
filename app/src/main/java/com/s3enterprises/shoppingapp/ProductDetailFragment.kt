package com.s3enterprises.shoppingapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.s3enterprises.shoppingapp.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()
    private val viewModel by activityViewModels<MainActivityViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail, container, false)
        binding.lifecycleOwner = this
        binding.desc.movementMethod = ScrollingMovementMethod()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.cartIcon.setOnClickListener {
            findNavController().navigate(ProductDetailFragmentDirections.gotoCartFragment())
        }

        setDetails()
        return binding.root
    }

    private fun setDetails() = args.product.let { product ->
        binding.product = product
        binding.model = viewModel
        Glide.with(requireContext()).load(product.image).into(binding.heroImage)
        binding.buyNowButton.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCheckoutFragment(product)
            findNavController().navigate(action)
        }
    }

}
package com.s3enterprises.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
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
        if(args.product==null){
            viewModel.cart.forEach {

            }
        }

        return binding.root
    }

}
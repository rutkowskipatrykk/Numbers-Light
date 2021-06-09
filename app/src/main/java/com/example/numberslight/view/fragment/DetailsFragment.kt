package com.example.numberslight.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.numberslight.databinding.FragmentDetailsBinding
import com.example.numberslight.di.viewmodel.DetailsViewModelFactory
import com.example.numberslight.di.viewmodel.GenericSavedStateViewModelFactory
import com.example.numberslight.view.listener.ConnectionBackListener
import com.example.numberslight.view.viewmodel.DetailsViewModel
import com.example.numberslight.view.viewmodel.MainActivityViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailsFragment : Fragment(), ConnectionBackListener {

    @Inject
    internal lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var binding: FragmentDetailsBinding

    private val activityViewModel by activityViewModels<MainActivityViewModel>()
    private val viewModel by viewModels<DetailsViewModel> {
        GenericSavedStateViewModelFactory(viewModelFactory, this, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    override fun onConnectionBack() {
        viewModel.loadDetails()
    }

    private fun setObservers() {
        activityViewModel.selectedItem.observe(viewLifecycleOwner, {
            viewModel.loadDetails(it.peekContent().name)
        })
    }


}
package com.example.numberslight.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.numberslight.databinding.FragmentListBinding
import com.example.numberslight.di.viewmodel.GenericSavedStateViewModelFactory
import com.example.numberslight.di.viewmodel.MyViewModelFactory
import com.example.numberslight.utils.ConnectivityUtils
import com.example.numberslight.view.Event
import com.example.numberslight.view.listener.ConnectionBackListener
import com.example.numberslight.view.viewmodel.MainActivityViewModel
import com.example.numberslight.view.viewmodel.ListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ListFragment : Fragment(), ConnectionBackListener {

    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    @Inject
    lateinit var connectivityUtils: ConnectivityUtils

    private lateinit var binding: FragmentListBinding

    private val activityViewModel by activityViewModels<MainActivityViewModel>()

    val viewModel by viewModels<ListViewModel> {
        GenericSavedStateViewModelFactory(viewModelFactory, this, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        if (connectivityUtils.isOnline()) {
            viewModel.loadList(activityViewModel.selectedItem.value?.peekContent())
        }
    }

    override fun onConnectionBack() {
        viewModel.loadList(activityViewModel.selectedItem.value?.peekContent())
    }

    private fun setObservers() {
        viewModel.onClickItem.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { selectedElement ->
                activityViewModel.selectedItem.value = Event(selectedElement)
            }
        })
    }

}
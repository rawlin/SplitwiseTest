package com.rawlincrasto.splitwise.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rawlincrasto.splitwise.R
import com.rawlincrasto.splitwise.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ExpensesViewModel::class.java)

        populateViews()
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun populateViews() {
        binding.owedOne.text = "Harsh owes Rs${viewModel.userMap.getValue(1).owedAmount}"
        binding.owedTwo.text = "Yash owes Rs${viewModel.userMap.getValue(2).owedAmount}"
        binding.owedThree.text = "Neha owes Rs${viewModel.userMap.getValue(3).owedAmount}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
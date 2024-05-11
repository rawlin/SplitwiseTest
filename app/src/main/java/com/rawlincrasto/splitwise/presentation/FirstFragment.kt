package com.rawlincrasto.splitwise.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rawlincrasto.splitwise.R
import com.rawlincrasto.splitwise.data.User
import com.rawlincrasto.splitwise.data.UserStore
import com.rawlincrasto.splitwise.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: ExpensesViewModel

    val participantMap = HashMap<Int, User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity()).get(ExpensesViewModel::class.java)
        setupListeners()

    }

    private fun setupListeners() {
        binding.paidByRadio.setOnCheckedChangeListener { _, _ ->
            val checkedUser = getPaidByUser()
            val unselectedUsers = UserStore.users.filter { it.userId != checkedUser.userId }
            binding.participantOne.text = unselectedUsers.first().name
            binding.participantTwo.text = unselectedUsers.last().name
            participantMap.clear()
            participantMap[binding.participantOne.id] = unselectedUsers.first()
            participantMap[binding.participantTwo.id] = unselectedUsers.last()
        }

        binding.buttonFirst.setOnClickListener {
            val expense = binding.expenseTv.text.toString()
            val amount = binding.totalEv.text.toString().toInt()
            val paidBy = getPaidByUser().userId
            val participants = getParticipants().map { it.userId }
            if (viewModel.isValidData(expense, amount)) {
                viewModel.createExpense(expense, amount, paidBy, participants)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getParticipants(): List<User> {
        val list = mutableListOf<User>()
        if (binding.participantOne.isSelected) {
            list.add(participantMap.getValue(binding.participantOne.id))
        }
        if (binding.participantOne.isSelected) {
            list.add(participantMap.getValue(binding.participantTwo.id))
        }
        return list
    }

    private fun getPaidByUser(): User {
        val checkedRadioButton = binding.paidByRadio.checkedRadioButtonId
        return when (checkedRadioButton) {
            binding.paidByOne.id -> {
                UserStore.users[0]
            }
            binding.paidByTwo.id -> {
                UserStore.users[1]
            }
            else -> {
                UserStore.users[2]
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
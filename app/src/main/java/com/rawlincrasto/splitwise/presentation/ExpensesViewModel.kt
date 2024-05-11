package com.rawlincrasto.splitwise.presentation

import androidx.lifecycle.ViewModel
import com.rawlincrasto.splitwise.data.User
import com.rawlincrasto.splitwise.data.UserStore

class ExpensesViewModel: ViewModel() {

    val userMap = hashMapOf<Int, User>(
        1 to UserStore.users[0],
        2 to UserStore.users[1],
        3 to UserStore.users[2]
    )
    fun isValidData(expense: String, amount: Int): Boolean {
        if (expense.isBlank() || expense.isEmpty()) return false
        if (amount <= 0) return false
        return true
    }

    fun createExpense(expense: String, amount: Int, paidByUserId: Int, participants: List<Int>) {
        val user = userMap.getValue(paidByUserId)
        val numberOfUsers = participants.size + 1
        val amountOwed = (amount / numberOfUsers.toFloat())
        // 100
        participants.forEach { participantId ->
            userMap[participantId]?.owedAmount = userMap[participantId]!!.owedAmount + amountOwed
        }

    }

}
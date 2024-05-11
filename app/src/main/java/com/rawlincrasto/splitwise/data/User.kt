package com.rawlincrasto.splitwise.data

data class User(
    val userId: Int,
    val name: String,
    val amountOwed: HashMap<Int/*userId*/, Float/*amount*/> = hashMapOf(),
    var owedAmount: Float = 0f
)

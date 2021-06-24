package com.vladhanin.myapplication.models

data class User(
    var login: String,
    var name: String,
    var surname: String,
    var passportId: String,
    val incomes: List<Income>,
    val jobs: MutableList<Job>,
    var isAdmin: Boolean
)
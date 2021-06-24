package com.vladhanin.myapplication.models

data class User(
    val login: String,
    val name: String,
    val surname: String,
    val passportId: String,
    val incomes: List<Income>,
    val jobs: MutableList<Job>,
    val isAdmin: Boolean
)
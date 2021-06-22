package com.vladhanin.myapplication.models

data class User(
    val login: String,
    val name: String,
    val surname: String,
    val passportId: String,
    val incomes: List<Income>,
    val jobs: List<Job>,
    val isAdmin: Boolean
)
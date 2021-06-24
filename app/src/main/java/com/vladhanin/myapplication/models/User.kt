package com.vladhanin.myapplication.models

import java.util.*

data class User(
    var login: String,
    var name: String,
    var surname: String,
    var pensionId: String,
    var dateOfBirthday: Date,

    var officialAddress: String,
    var actualAddress: String,

    var incomes: List<Income>,
    var jobs: List<Job>,

    var pension: Pension?,

    var isAdmin: Boolean
)
package com.vladhanin.myapplication.models

import android.provider.ContactsContract

data class Income(
    val position: String,
    val organization: String,
    val date: String,
    val money: String
)
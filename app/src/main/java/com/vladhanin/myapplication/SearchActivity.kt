package com.vladhanin.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.User

class SearchActivity : AppCompatActivity() {

    private lateinit var usersAdapter: UsersAdapter

    val users = setOf<User>(
        User("Дмитро" ,"Ковальчуков"),
        User("Олена", "Ковальчук"),
        User("Олександр", "Ковальчук")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        with(findViewById<RecyclerView>(R.id.usersList)){
            adapter = UsersAdapter(context, users.toTypedArray()).also {
                usersAdapter = it
            }
        }

        findViewById<EditText>(R.id.searchInput).doAfterTextChanged {
            val query = it.toString()

            val filtered = users.filter { user ->
                query.isEmpty() ||
                        user.name.startsWith(prefix = query, ignoreCase = true) ||
                        user.surname.startsWith(prefix = query, ignoreCase = true)
            }

            usersAdapter.dataSet = filtered.toTypedArray()
            usersAdapter.notifyDataSetChanged()
        }
    }
}
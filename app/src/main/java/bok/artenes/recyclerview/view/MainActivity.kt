package bok.artenes.recyclerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import bok.artenes.recyclerview.R
import bok.artenes.recyclerview.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val adapter = UsersAdapter(viewModel, recyclerViewUser)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerViewUser.adapter = adapter
        recyclerViewUser.layoutManager = layoutManager

        viewModel.getUsers().observe(this, Observer {
            adapter.submitList(it)
        })
    }
}

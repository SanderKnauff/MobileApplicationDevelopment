package ooo.sansk.dockerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_server_list.*
import kotlinx.android.synthetic.main.content_server_list.*
import ooo.sansk.dockerapp.adapter.ServerListAdapter
import ooo.sansk.dockerapp.viewmodel.ServerListViewModel

class ServerListActivity : AppCompatActivity() {

    private lateinit var serverListViewModel: ServerListViewModel
    private lateinit var serverListAdapter: ServerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_list)
        setSupportActionBar(toolbar)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        serverListViewModel = ViewModelProviders.of(this).get(ServerListViewModel::class.java)
        serverListAdapter = ServerListAdapter(serverListViewModel)
        serverListViewModel.servers.observe(this, Observer {
            serverListAdapter.servers = it
        })


    }

    private fun initViews() {
        rvServers.adapter = serverListAdapter
        rvServers.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivity(Intent(this, AddServerActivity::class.java))
        }
    }

}

package ooo.sansk.dockerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import ooo.sansk.dockerapp.adapter.ServerListAdapter.Companion.KEY_SERVER_ENTRY
import ooo.sansk.dockerapp.model.ServerEntry
import ooo.sansk.dockerapp.network.DockerWebClient
import ooo.sansk.dockerapp.viewmodel.LoginActivityViewModel
import ooo.sansk.dockerapp.viewmodel.ServerListViewModel
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initViewModel()
        initViews()
    }

    fun initViewModel() {
        loginActivityViewModel = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)
        loginActivityViewModel.server.value = intent!!.extras.getParcelable(KEY_SERVER_ENTRY)
    }

    fun initViews() {
        title = loginActivityViewModel.server.value?.name
        btnLogin.setOnClickListener {
            DockerWebClient.host = loginActivityViewModel.server.value!!.location
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val KEY_SERVER = "KEY_SERVER"
    }

}

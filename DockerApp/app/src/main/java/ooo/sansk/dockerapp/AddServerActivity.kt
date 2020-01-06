package ooo.sansk.dockerapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_add_server.*
import kotlinx.android.synthetic.main.content_add_server.*
import ooo.sansk.dockerapp.viewmodel.AddServerViewModel

class AddServerActivity : AppCompatActivity() {

    private lateinit var addServerViewModel: AddServerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_server)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initViewModels()
        initViews()
    }

    private fun initViewModels() {
        addServerViewModel = ViewModelProviders.of(this).get(AddServerViewModel::class.java)
    }

    private fun initViews() {
        addServerViewModel.error.observe(this, Observer {
            Toast.makeText(this, addServerViewModel.error.value, Toast.LENGTH_SHORT).show()
        })
        btnSaveServer.setOnClickListener {
            addServerViewModel.serverName.value = etServerName.text.toString()
            addServerViewModel.serverUrl.value = etServerUrl.text.toString()
            addServerViewModel.addServerEntry()
        }
        addServerViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
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

}

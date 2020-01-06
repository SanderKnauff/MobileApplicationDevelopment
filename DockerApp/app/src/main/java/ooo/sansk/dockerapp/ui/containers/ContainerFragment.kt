package ooo.sansk.dockerapp.ui.containers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_containers.view.*
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.adapter.ContainerListAdapter

class ContainerFragment : Fragment() {

    private lateinit var containerViewModel: ContainerViewModel
    private val containerListAdapter: ContainerListAdapter = ContainerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_containers, container, false)
        initViewModel(root)
        initView(root)
        return root
    }

    private fun initViewModel(root: View) {
        containerViewModel =
            ViewModelProviders.of(this).get(ContainerViewModel::class.java)
        root.rvContainers.adapter = containerListAdapter
        root.rvContainers.layoutManager = LinearLayoutManager(root.context)
        containerViewModel.refreshContainers()
    }

    private fun initView(root: View) {
        root.switchInactive.isChecked = containerViewModel.includeInactive.value?: false
        root.switchInactive.setOnCheckedChangeListener { _, isChecked ->
            containerViewModel.includeInactive.value = isChecked
        }
        containerViewModel.includeInactive.observe(this, Observer {
            containerViewModel.refreshContainers()
        })
        containerViewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        containerViewModel.containers.observe(this, Observer {
            containerListAdapter.containers = it
        })
    }
}
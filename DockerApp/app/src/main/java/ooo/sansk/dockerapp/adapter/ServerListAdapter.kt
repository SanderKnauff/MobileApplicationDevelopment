package ooo.sansk.dockerapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.server_list_entry.view.*
import ooo.sansk.dockerapp.LoginActivity
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.model.ServerEntry
import ooo.sansk.dockerapp.viewmodel.ServerListViewModel
import java.util.*

class ServerListAdapter(val serverListViewModel: ServerListViewModel) :
    RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {

    var servers = Collections.emptyList<ServerEntry>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.server_list_entry,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return servers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(servers[position])
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bind(serverEntry: ServerEntry) {
            itemView.tvName.text = serverEntry.name
            itemView.tvUrl.text = serverEntry.location
            itemView.btnDeleteServer.setOnClickListener {
                serverListViewModel.deleteServerEntry(serverEntry)
            }
            itemView.btnOpen.setOnClickListener {
                val intent = Intent(context, LoginActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(KEY_SERVER_ENTRY, serverEntry)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        const val KEY_SERVER_ENTRY = "KEY_SERVER_ENTRY"
    }
}
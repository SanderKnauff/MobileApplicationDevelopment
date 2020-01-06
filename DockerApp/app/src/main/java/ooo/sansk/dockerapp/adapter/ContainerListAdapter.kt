package ooo.sansk.dockerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dockerjava.api.model.Container
import kotlinx.android.synthetic.main.container_list_entry.view.*
import kotlinx.android.synthetic.main.server_list_entry.view.tvName
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.model.ServerEntry
import java.util.*

class ContainerListAdapter() :
    RecyclerView.Adapter<ContainerListAdapter.ViewHolder>() {

    var containers = Collections.emptyList<Container>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.container_list_entry,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return containers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(containers[position])
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bind(container: Container) {
            itemView.tvName.text = container.names[0]
            itemView.tvState.text = container.status
            itemView.chipStatus.text = container.state
            itemView.setOnClickListener {
//                val intent = Intent(context, LoginActivity::class.java)
//                val bundle = Bundle()
//                bundle.putParcelable(KEY_SERVER_ENTRY, serverEntry)
//                intent.putExtras(bundle)
//                context.startActivity(intent)
            }
        }
    }

    companion object {
        const val KEY_SERVER_ENTRY = "KEY_SERVER_ENTRY"
    }
}
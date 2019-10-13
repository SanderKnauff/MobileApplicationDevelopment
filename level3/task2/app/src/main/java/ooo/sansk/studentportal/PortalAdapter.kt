package ooo.sansk.studentportal

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portal_view.view.*

class PortalAdapter(val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.portal_view, parent, false))
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bind(portal: Portal) {
            itemView.tvName.text = portal.name
            itemView.tvUrl.text = portal.url
            itemView.setOnClickListener {
                CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(portal.url))
            }
        }
    }
}
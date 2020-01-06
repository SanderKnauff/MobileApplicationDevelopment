package ooo.sansk.dockerapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.dockerjava.api.model.Image
import kotlinx.android.synthetic.main.image_list_entry.view.*
import kotlinx.android.synthetic.main.server_list_entry.view.tvName
import ooo.sansk.dockerapp.ImageDetailsActivity
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.ui.images.ImagesFragment.Companion.REQUEST_CODE_OPEN_IMAGE_DETAILS
import java.text.SimpleDateFormat
import java.util.*

class ImageListAdapter(val fragment: Fragment) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    var images = Collections.emptyList<Image>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.image_list_entry,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bind(image: Image) {
            itemView.tvName.text = image.repoTags[0]
            itemView.tvSize.text = context.getString(R.string.size_mb, (image.size / 1000000))
            itemView.tvAge.text = context.getString(
                R.string.image_created_at,
                format.format(Date(image.created * 1000))
            )
            itemView.setOnClickListener {
                val intent = Intent(context, ImageDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putString(KEY_IMAGE_ID, image.id)
                bundle.putLong(KEY_IMAGE_CREATED_EPOCH, image.created)
                intent.putExtras(bundle)
                fragment.startActivityForResult(
                    intent,
                    REQUEST_CODE_OPEN_IMAGE_DETAILS
                )
            }
        }
    }

    companion object {
        const val KEY_IMAGE_ID = "KEY_IMAGE_ID"
        const val KEY_IMAGE_CREATED_EPOCH = "KEY_IMAGE_CREATED_EPOCH"
        val format = SimpleDateFormat("dd MMMM YYYY")
    }
}
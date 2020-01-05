package ooo.sansk.gamebacklog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_card_view.view.*
import ooo.sansk.gamebacklog.R
import ooo.sansk.gamebacklog.model.Game
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    var games = Collections.emptyList<Game>()
        get() = field
        set(value) {
            field = value.sorted()
            notifyDataSetChanged()
        }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.game_card_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {



        fun bind(game: Game) {
            itemView.tvTitle.text = game.title
            itemView.tvPlatform.text = game.platform
            itemView.tvReleased.text =
                context.getString(R.string.released, format.format(game.releaseDate))
        }

    }

    companion object {
        val format = SimpleDateFormat("dd MMMM YYYY")
    }
}
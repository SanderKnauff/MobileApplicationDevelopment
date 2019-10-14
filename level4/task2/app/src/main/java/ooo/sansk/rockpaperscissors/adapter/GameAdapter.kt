package ooo.sansk.rockpaperscissors.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.view.ivChoiceCPU
import kotlinx.android.synthetic.main.content_main.view.ivChoicePlayer
import kotlinx.android.synthetic.main.content_main.view.tvResult
import kotlinx.android.synthetic.main.game_result_view.view.*
import ooo.sansk.rockpaperscissors.R
import ooo.sansk.rockpaperscissors.model.Game

class GameAdapter(val gameList: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.game_result_view, parent, false))
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(gameList[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvDate.text = game.date.toString()
            itemView.tvResult.setText(game.result.message)
            itemView.ivChoicePlayer.setImageResource(game.playerMove.drawableId)
            itemView.ivChoiceCPU.setImageResource(game.cpuMove.drawableId)
        }

    }
}
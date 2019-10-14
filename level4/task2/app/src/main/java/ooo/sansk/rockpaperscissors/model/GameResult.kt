package ooo.sansk.rockpaperscissors.model

import androidx.annotation.StringRes
import ooo.sansk.rockpaperscissors.R

enum class GameResult(@StringRes val message: Int) {

    WIN(R.string.msg_win),
    LOSE(R.string.msg_lose),
    DRAW(R.string.msg_draw)

}
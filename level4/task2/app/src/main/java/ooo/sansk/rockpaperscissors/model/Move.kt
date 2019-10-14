package ooo.sansk.rockpaperscissors.model

import androidx.annotation.DrawableRes
import ooo.sansk.rockpaperscissors.R

enum class Move(@DrawableRes val drawableId: Int) {
    ROCK(R.drawable.rock),
    PAPER(R.drawable.paper),
    SCISSORS(R.drawable.scissors)
}
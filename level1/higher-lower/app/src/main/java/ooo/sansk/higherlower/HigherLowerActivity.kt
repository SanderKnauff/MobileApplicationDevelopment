package ooo.sansk.higherlower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private var lastThrow = 1
    private var currentThrow = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)
        setSupportActionBar(toolbar)
        initViews()
    }

    fun initViews() {
        buttonLower.setOnClickListener { onLowerClick() }
        buttonEquals.setOnClickListener { onEqualClick() }
        buttonHigher.setOnClickListener { onHigherClick() }
        updateUI()
    }

    fun updateUI() {
        textFieldLastThrow.text = getString(R.string.last_throw, lastThrow)
        imageViewDice.setImageResource(getDrawableForThrow(currentThrow))
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onHigherClick() {
        rollDice()
        if(currentThrow > lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onLowerClick() {
        rollDice()
        if(currentThrow < lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onEqualClick() {
        rollDice()
        if(currentThrow == lastThrow) {
            onAnswerCorrect()
        } else {
            onAnswerIncorrect()
        }
    }

    /**
     * Displays a successful Toast message.
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a incorrect Toast message.
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }

    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun getDrawableForThrow(thrownNumber: Int): Int {
        return when (thrownNumber) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> throw IllegalAccessException("thrownNumber must be between 1 and 6 (inclusive)")
        }
    }
}

package ooo.sansk.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        buttonSubmit.setOnClickListener {
            onSubmit()
        }
    }

    fun onSubmit() {
        val ttResult = getBooleanFromText(inputResultTT.text.toString())
        val tfResult = getBooleanFromText(inputResultTF.text.toString())
        val ftResult = getBooleanFromText(inputResultFT.text.toString())
        val ffResult = getBooleanFromText(inputResultFF.text.toString())

        if (ttResult == null) {
            Toast.makeText(this, getString(R.string.value_incorrect, "TT"), Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (tfResult == null) {
            Toast.makeText(this, getString(R.string.value_incorrect, "TF"), Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (ftResult == null) {
            Toast.makeText(this, getString(R.string.value_incorrect, "FT"), Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (ffResult == null) {
            Toast.makeText(this, getString(R.string.value_incorrect, "FF"), Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (testBoolean(true, true, ttResult) &&
            testBoolean(true, false, tfResult) &&
            testBoolean(false, true, ftResult) &&
            testBoolean(false, false, ffResult)
        ) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
        }
    }

    fun getBooleanFromText(text: String): Boolean? {
        return when (text.toLowerCase()) {
            "true", "t" -> true
            "false", "f" -> false
            else -> null
        }
    }

    fun testBoolean(a: Boolean, b: Boolean, actualResult: Boolean): Boolean {
        println("expected $a and $b to result in ${a && b} but got $actualResult")
        return (a && b) == actualResult
    }
}

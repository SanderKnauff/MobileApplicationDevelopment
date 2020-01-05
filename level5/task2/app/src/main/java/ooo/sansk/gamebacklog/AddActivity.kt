package ooo.sansk.gamebacklog

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import ooo.sansk.gamebacklog.model.Game
import ooo.sansk.gamebacklog.viewmodel.AddViewModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity() {

    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Game"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {

            if (etYear.text.isBlank() || etMonth.text.isBlank() || etDay.text.isBlank()) {
                addViewModel.error.value = "Release date can not be empty"
                return@setOnClickListener
            }

            val date = parseDate(Integer.parseInt(etYear.text.toString()), Integer.parseInt(etMonth.text.toString()), Integer.parseInt(etDay.text.toString()))

            addViewModel.game.value!!.apply {
                title = etTitle.text.toString()
                platform = etPlatform.text.toString()
                releaseDate = date
            }

            addViewModel.addGame()
        }
    }

    private fun initViewModel() {
        addViewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        addViewModel.game.value = Game(null, "", "", null)

        addViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        addViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    private fun parseDate(year: Int, month: Int, day: Int): Date? {
        val sdf: DateFormat = SimpleDateFormat("yyyy/MM/dd")
        sdf.isLenient = false
        try {
            return sdf.parse("${year}/${month}/${day}")
        } catch (e: ParseException) {
            return null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_GAME = "EXTRA_GAME"
    }

}


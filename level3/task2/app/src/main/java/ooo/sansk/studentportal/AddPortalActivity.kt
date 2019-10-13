package ooo.sansk.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_portal.*

const val EXTRA_ADD_PORTAL = "EXTRA_ADD_PORTAL"

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)
        setSupportActionBar(addPortalToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle(R.string.add_portal)

        initViews()
    }

    private fun initViews() {
        btAdd.setOnClickListener {
            if (etName.text.isBlank() || etUrl.text.isBlank()) {
                Toast.makeText(this, getString(R.string.warning_empty_fields), Toast.LENGTH_SHORT).show()
            } else if (!etUrl.text.startsWith("http://") && !etUrl.text.startsWith("https://")) {
                Toast.makeText(this, getString(R.string.no_protocol_warning), Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_ADD_PORTAL, Portal(etName.text.toString(), etUrl.text.toString()))
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

}

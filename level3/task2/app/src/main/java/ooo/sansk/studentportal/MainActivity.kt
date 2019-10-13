package ooo.sansk.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_main.*

const val REQUEST_CODE_ADD_PORTAL = 1

class MainActivity : AppCompatActivity() {

    private val portals = mutableListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.student_portal)

        initViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_ADD_PORTAL && data != null && resultCode == Activity.RESULT_OK) {
            portals.add(data.getParcelableExtra(EXTRA_ADD_PORTAL))
            portalAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {
        rvPortals.adapter = portalAdapter
        rvPortals.layoutManager = GridLayoutManager(this, 2)

        fab.setOnClickListener {
            startActivityForResult(Intent(this, AddPortalActivity::class.java), REQUEST_CODE_ADD_PORTAL)
        }
    }

}

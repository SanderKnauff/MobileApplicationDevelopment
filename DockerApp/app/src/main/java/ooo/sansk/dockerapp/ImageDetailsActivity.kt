package ooo.sansk.dockerapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.content_image_details.*
import kotlinx.android.synthetic.main.server_list_entry.*
import ooo.sansk.dockerapp.adapter.ImageListAdapter
import ooo.sansk.dockerapp.adapter.ImageListAdapter.Companion.KEY_IMAGE_CREATED_EPOCH
import ooo.sansk.dockerapp.adapter.ImageListAdapter.Companion.KEY_IMAGE_ID
import ooo.sansk.dockerapp.viewmodel.ImageDetailsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var imageDetailsViewModel: ImageDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initViewModel()
        initViews()
    }

    fun initViewModel() {
        imageDetailsViewModel = ViewModelProviders.of(this).get(ImageDetailsViewModel::class.java)
    }

    fun initViews() {
        imageDetailsViewModel.imageId.observe(this, Observer {
            imageDetailsViewModel.retrieveImage()
        })
        imageDetailsViewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        imageDetailsViewModel.image.observe(this, Observer {
            tvVersion.text = it.dockerVersion
            tvArch.text = it.arch
            tvSize.text = getString(R.string.size_mb, (it.size!!.div(1000000)))
            tvCreated.text = ImageListAdapter.format.format(
                Date(intent.getLongExtra(KEY_IMAGE_CREATED_EPOCH, 0) * 1000)
            )
            title = it.config?.image
        })
        btnDeleteImage.setOnClickListener {
            imageDetailsViewModel.deleteImage(object :
                Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    finish()
                }
            })
        }
        imageDetailsViewModel.imageId.value = intent.getStringExtra(KEY_IMAGE_ID)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

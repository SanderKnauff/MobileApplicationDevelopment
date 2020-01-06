package ooo.sansk.dockerapp.ui.images

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_images.view.*
import ooo.sansk.dockerapp.R
import ooo.sansk.dockerapp.adapter.ImageListAdapter

class ImagesFragment : Fragment() {

    private lateinit var imagesViewModel: ImagesViewModel
    private val imageListAdapter: ImageListAdapter = ImageListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_images, viewGroup, false)
        initViewModel(root)
        initView(root)
        return root
    }

    private fun initViewModel(root: View) {
        imagesViewModel =
            ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        root.rvImages.adapter = imageListAdapter
        root.rvImages.layoutManager = LinearLayoutManager(root.context)
        imagesViewModel.refreshImages()
    }

    private fun initView(root: View) {
        imagesViewModel.error.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        imagesViewModel.images.observe(this, Observer {
            imageListAdapter.images = it
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(resultCode) {
            RESULT_DELETED -> {
                imagesViewModel.refreshImages()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_OPEN_IMAGE_DETAILS = 0
        const val RESULT_DELETED = 0
    }


}
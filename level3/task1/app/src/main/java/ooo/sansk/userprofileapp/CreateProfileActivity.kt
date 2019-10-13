package ooo.sansk.userprofileapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.android.synthetic.main.content_create_profile.*
import ooo.sansk.userprofileapp.model.ProfileData

const val GALLERY_REQUEST_CODE = 100

class CreateProfileActivity : AppCompatActivity() {

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        setSupportActionBar(toolbar)

        changePictureButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }

        confirmButton.setOnClickListener {
            openProfilePage()
        }
    }

    private fun openProfilePage() {
        if (firstNameInput.text.toString().isBlank()) {
            Toast.makeText(this@CreateProfileActivity, R.string.missingFirstName, Toast.LENGTH_SHORT).show()
            return
        }

        if (lastNameInput.text.toString().isBlank()) {
            Toast.makeText(this@CreateProfileActivity, R.string.missingLastName, Toast.LENGTH_SHORT).show()
            return
        }

        if (descriptionInput.text.toString().isBlank()) {
            Toast.makeText(this@CreateProfileActivity, R.string.missingDescription, Toast.LENGTH_SHORT).show()
            return
        }

        val profileData = ProfileData(
            firstNameInput.text.toString(),
            lastNameInput.text.toString(), descriptionInput.text.toString(),
            imageUri
        )
        val profileIntent = Intent(this@CreateProfileActivity, ProfileActivity::class.java)
        profileIntent.putExtra(EXTRA_PROFILE_DATA, profileData)
        startActivity(profileIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GALLERY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    imageUri = data.data
                    profilePicture.setImageURI(data.data)
                }
            }
        }
    }

}

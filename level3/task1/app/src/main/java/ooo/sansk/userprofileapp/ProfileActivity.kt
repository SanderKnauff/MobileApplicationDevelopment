package ooo.sansk.userprofileapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.content_create_profile.*
import kotlinx.android.synthetic.main.content_create_profile.profilePicture
import kotlinx.android.synthetic.main.content_profile.*
import kotlinx.android.synthetic.main.content_profile.view.*
import ooo.sansk.userprofileapp.model.ProfileData

const val EXTRA_PROFILE_DATA = "EXTRA_PROFILE_DATA"

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "This is your profile!"

        val profileData = intent.getParcelableExtra<ProfileData>(EXTRA_PROFILE_DATA)
        name.text = resources.getString(R.string.firstNameLastName, profileData.firstName, profileData.lastName)
        description.text = profileData.description
        profilePicture.setImageURI(profileData.imageUri)
    }

}

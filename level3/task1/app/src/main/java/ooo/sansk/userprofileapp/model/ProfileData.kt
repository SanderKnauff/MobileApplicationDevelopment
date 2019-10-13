package ooo.sansk.userprofileapp.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData(
    val firstName: String,
    val lastName: String,
    val description: String,
    val imageUri: Uri?
) : Parcelable
package ooo.sansk.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(val name: String, val url: String): Parcelable
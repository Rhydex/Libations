package com.example.ryden.libations

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class MyUserProfile (var userName: String, var userBio: String)
    :Parcelable
{
    @get:Exclude
    var id = ""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(userBio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyUserProfile> {
        override fun createFromParcel(parcel: Parcel): MyUserProfile {
            return MyUserProfile(parcel)
        }

        override fun newArray(size: Int): Array<MyUserProfile?> {
            return arrayOfNulls(size)
        }
    }
}
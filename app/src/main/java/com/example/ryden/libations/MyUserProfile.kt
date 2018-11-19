package com.example.ryden.libations

import android.os.Parcel
import android.os.Parcelable

class MyUserProfile (var userName: String, var userBio: String)
    :Parcelable
{
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
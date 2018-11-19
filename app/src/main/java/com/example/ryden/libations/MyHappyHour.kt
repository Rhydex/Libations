package com.example.ryden.libations

import android.os.Parcel
import android.os.Parcelable

class MyHappyHour (var locName: String, var times: String, var address: String, var description: String)
    : Parcelable
{
    var id = ""

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(locName)
        parcel.writeString(times)
        parcel.writeString(address)
        parcel.writeString(description)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyHappyHour> {
        override fun createFromParcel(parcel: Parcel): MyHappyHour {
            return MyHappyHour(parcel)
        }

        override fun newArray(size: Int): Array<MyHappyHour?> {
            return arrayOfNulls(size)
        }
    }


}

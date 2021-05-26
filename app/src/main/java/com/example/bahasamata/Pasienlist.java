package com.example.bahasamata;

import android.os.Parcel;
import android.os.Parcelable;

public class Pasienlist implements Parcelable {

   public String namapasien;
   public int idpasien;

    public Pasienlist(String namapasien,int idpasien) {
        this.idpasien = idpasien;
        this.namapasien = namapasien;
    }

    protected Pasienlist(Parcel in) {
        namapasien = in.readString();
        idpasien = in.readInt();
    }

    public static final Creator<Pasienlist> CREATOR = new Creator<Pasienlist>() {
        @Override
        public Pasienlist createFromParcel(Parcel in) {
            return new Pasienlist(in);
        }

        @Override
        public Pasienlist[] newArray(int size) {
            return new Pasienlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namapasien);
        dest.writeInt(idpasien);
    }
}
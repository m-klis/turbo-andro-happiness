package com.example.githubuser;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private int pict;
    private String user,name,loca,repo,comp,flwr,flng;

    public Users() {
    }

    public int getPict() {
        return pict;
    }

    public void setPict(int pict) {
        this.pict = pict;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getFlwr() {
        return flwr;
    }

    public void setFlwr(String flwr) {
        this.flwr = flwr;
    }

    public String getFlng() {
        return flng;
    }

    public void setFlng(String flng) {
        this.flng = flng;
    }

    protected Users(Parcel in) {
        pict = in.readInt();
        user = in.readString();
        name = in.readString();
        loca = in.readString();
        repo = in.readString();
        comp = in.readString();
        flwr = in.readString();
        flng = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pict);
        dest.writeString(user);
        dest.writeString(name);
        dest.writeString(loca);
        dest.writeString(repo);
        dest.writeString(comp);
        dest.writeString(flwr);
        dest.writeString(flng);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}

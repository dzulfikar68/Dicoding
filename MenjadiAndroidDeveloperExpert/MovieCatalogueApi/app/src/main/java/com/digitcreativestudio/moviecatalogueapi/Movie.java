package com.digitcreativestudio.moviecatalogueapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String genre;
    private String release;
    private String poster;

    public Movie(){

    }

    public static final String TABLE_NAME = "movie";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "email";
    public static final String COLUMN_DESCRIPTION = "password";
    public static final String COLUMN_RATE = "fullname";
    public static final String COLUMN_RELEASE = "address";
    public static final String COLUMN_POSTER = "timestamp";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_RATE + " TEXT,"
                    + COLUMN_RELEASE + " TEXT,"
                    + COLUMN_POSTER + " TEXT"
                    + ")";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        description = in.readString();
        genre = in.readString();
        release = in.readString();
        poster = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeString(release);
        dest.writeString(poster);
    }
}

package com.example.android.popularmoviesstage1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pedro on 31/03/2018.
 */

public class Movie implements Parcelable{

    String poster_path;
    String overview;
    String release_date;
    int id;
    String original_title;
    String original_language;
    String title;
    String backdrop_path;
    Double popularity;
    int vote_count;
    Boolean video;
    Double vote_average;

    public Movie(String poster_path, String overview, String release_date, int id, String original_title,
            String original_language, String title, String backdrop_path, Double popularity, int vote_count,
            Boolean video, Double vote_average){

        this.backdrop_path=backdrop_path;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    protected Movie(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        id = in.readInt();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readByte() == 0x00 ? null : in.readDouble();
        vote_count = in.readInt();
        byte videoVal = in.readByte();
        video = videoVal == 0x02 ? null : videoVal != 0x00;
        vote_average = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        if (popularity == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(popularity);
        }
        dest.writeInt(vote_count);
        if (video == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (video ? 0x01 : 0x00));
        }
        if (vote_average == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(vote_average);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}

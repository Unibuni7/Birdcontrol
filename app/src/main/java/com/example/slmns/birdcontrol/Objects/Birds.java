package com.example.slmns.birdcontrol.Objects;

/**
 * Created by slmns on 15-04-2018.
 */

public class Birds {

    private String Created;
    private String NameDanish;
    private String NameEnglish;
    private String PhotoURL;
    private int id;





    public Birds(String Created, String NameDanish, String NameEnglish, String PhotoURL, int id){
        this.Created = Created;
        this.NameDanish = NameDanish;
        this.NameEnglish = NameEnglish;
        this.PhotoURL = PhotoURL;
        this.id = id;
    }


    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        this.Created = created;
    }

    public String getNameDanish() {
        return NameDanish;
    }

    public void setNameDanish(String nameDanish) {
        this.NameDanish = nameDanish;
    }

    public String getNameEnglish() {
        return NameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.NameEnglish = nameEnglish;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.PhotoURL = photoURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}

package com.example.tanut.mapsearch.data.db.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.tanut.mapsearch.data.db.backend.AppDatabase;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

import static android.R.attr.priority;

/**
 * Created by Abhimanyu on 10/21/2017.
 */
@Entity(tableName = AppDatabase.TABLE_NAME)
public class MapItem implements ClusterItem,Parcelable{



    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;

    @SerializedName("geometry")
    @Expose
    @Ignore
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("name")
    @Expose
    private String name;
    private String tag;



    private Double lat;
    private Double lng;




    public MapItem(int id, String formattedAddress, Double lat,Double lng, String icon, String name,String tag) {
        this.id = id;
        this.formattedAddress = formattedAddress;
        this.lat = lat;
        this.lng = lng;
        this.icon = icon;
        this.name = name;
        this.tag = tag;
    }



    protected MapItem(Parcel in) {
        id = in.readInt();
        formattedAddress = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        icon = in.readString();
        name = in.readString();
        tag = in.readString();
    }

    public static final Creator<MapItem> CREATOR = new Creator<MapItem>() {
        @Override
        public MapItem createFromParcel(Parcel in) {
            return new MapItem(in);
        }

        @Override
        public MapItem[] newArray(int size) {
            return new MapItem[size];
        }
    };

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(formattedAddress);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(icon);
        dest.writeString(name);
        dest.writeString(tag);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public LatLng getPosition() {
        return new LatLng(geometry.getLocation().getLat(),geometry.getLocation().getLng());
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return formattedAddress;
    }

    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }



    public static class ItemBuilder {

        private int id;
        private String formattedAddress;
        private Double lat;
        private Double lng;
        private String icon;
        private String name;
        private String tag = "default";


        public ItemBuilder setId(int id){
        this.id = id;
         return this;
        }

        public ItemBuilder setAdress(String add){
            this.formattedAddress = add;
            return this;
        }

        public ItemBuilder setLatitude(Double lat){
            this.lat = lat;
            return this;
        }

        public ItemBuilder setLongitude(Double lng){
            this.lng = lng;
            return this;
        }
        public ItemBuilder setIcon(String icon){
            this.icon = icon;
            return this;
        }
        public ItemBuilder setName(String name){
            this.name = name;
            return this;
        }
        public ItemBuilder setTag(String tag){
            this.tag = tag;
            return this;
        }

        public MapItem build(){
            return new MapItem(id,formattedAddress,lat,lng,icon,name,tag);
        }

    }

    @Override
    public String toString() {
        return "MapItem{" +
                "id=" + id +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", geometry=" + geometry +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}

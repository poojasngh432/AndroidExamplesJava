
package com.example.tutorialsproject.database.model.gifModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;
    @SerializedName("created")
    @Expose
    private Double created;
    @SerializedName("shares")
    @Expose
    private Integer shares;
    @SerializedName("itemurl")
    @Expose
    private String itemurl;
    @SerializedName("composite")
    @Expose
    private Object composite;
    @SerializedName("hasaudio")
    @Expose
    private Boolean hasaudio;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private String id;

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getItemurl() {
        return itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public Object getComposite() {
        return composite;
    }

    public void setComposite(Object composite) {
        this.composite = composite;
    }

    public Boolean getHasaudio() {
        return hasaudio;
    }

    public void setHasaudio(Boolean hasaudio) {
        this.hasaudio = hasaudio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

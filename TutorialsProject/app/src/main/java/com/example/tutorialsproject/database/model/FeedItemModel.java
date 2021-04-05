package com.example.tutorialsproject.database.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class FeedItemModel extends GeneralResponseModel {

    @SerializedName("is_posting_enable")
    private boolean isPostingEnabled;

    @SerializedName("contact_available")
    private boolean isContactAvailable;

    @SerializedName("has_next")
    private Boolean hasNext;

    @SerializedName("next_url")
    private String nextUrl;

    @SerializedName("user_deeplink")
    private String userReferralLink;

    @SerializedName("item_serve")
    private String itemServe;

    @SerializedName("next_page")
    private int nextPageCount;

    @SerializedName("feed")
    private ArrayList<FeedItem> feedItems;

    public ArrayList<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(ArrayList<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isPostingEnabled() {
        return isPostingEnabled;
    }

    public boolean isContactAvailable() {
        return isContactAvailable;
    }

    public String getUserReferralLink() {
        return userReferralLink;
    }

    public String getItemServe() {
        return itemServe;
    }

    public int getNextPageCount() {
        return nextPageCount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FeedItemModel{");
        sb.append("feedItems=").append(feedItems == null ? "null" : Arrays.asList(feedItems).toString());
        sb.append('}');
        return sb.toString();
    }

}
package com.example.tutorialsproject.database.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeedItem implements Serializable {

    @SerializedName("c_count")
    private int commentsCount;
    @SerializedName("l_count")
    private int likesCount;
    @SerializedName("f_count")
    private int facebookCount;
    @SerializedName("w_count")
    private int wappCount;
    @SerializedName("can_follow")
    private boolean canFollow;
    @SerializedName("is_commented")
    private boolean isCommented;
    @SerializedName("is_liked")
    private boolean isLiked;
    @SerializedName("is_following")
    private boolean isFollowing;

    @SerializedName("hash_id")
    private String itemHashId;
    @SerializedName("url_str")
    private String urlStr;
    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private int itemType;

    @SerializedName("video")
    private String video;
    @SerializedName("image")
    private String image;
    @SerializedName("text")
    private String text;
    @SerializedName("url")
    private String url;
    @SerializedName("meta_title")
    private String metaTitle;
    @SerializedName("time")
    private String time;
    @SerializedName("timestamp")
    private String timeStamp;
    @SerializedName("source")
    private String contentSource;

    @SerializedName("user_hash_id")
    private String userHashId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("user_photo")
    private String userPhoto;
    @SerializedName("bio")
    private String userBio;
    @SerializedName("user_name")
    private String userName;

//    @SerializedName("crop_tags")
//    private ArrayList<FeedItemTagModel> cropTags;
//
//    @SerializedName("data")
//    private ArrayList<ExpertData> expertData;
//
//    @SerializedName("topic_tags")
//    private ArrayList<FeedItemTagModel> topicTags;
//
//    @SerializedName("social_data")
//    private FeedSocialDataModel socialData;
//
//    @SerializedName("weather_data")
//    private WeatherItem weather;
//
//    @SerializedName("suggested_users")
//    private ArrayList<SuggestedFriendDataModel> suggestedUsers;
//
//    @SerializedName("contacts")
//    private ArrayList<SuggestedFriendDataModel> contacts;

    @SerializedName("line1")
    private String line1;

    @SerializedName("line2")
    private String line2;

    @SerializedName("sms")
    private String sms;

    @SerializedName("referral_url")
    private String referralUrl;

    @SerializedName("duration")
    private long duration;

    @SerializedName("view_count")
    private long viewCount;

//    @SerializedName("user_crops")
//    private ArrayList<CropsDataModel> myCrops;
//
//    @SerializedName("famous_crops")
//    private ArrayList<CropsDataModel> famousCrops;

    @SerializedName("user_referral_count")
    private int referralCount;

//    @SerializedName("shop_details")
//    private ProfileShopDataModel shopDataModel;
//
//    @SerializedName("merchants")
//    private List<ProfileShopDataModel> nearByStores;
//
//    @SerializedName("pesticides")
//    private List<PesticidesModel> pesticides;
//
//    @SerializedName("diseases")
//    private List<FeedDiseaseDataModel> diseases;
//
//    @SerializedName("badge")
//    private FeedItemBadgeModel badge;

    @SerializedName("network_contacts_count")
    private int networkContactsCount;

    @SerializedName("activated")
    private boolean isActivated;
//    @SerializedName("crop")
//    private FeedUserActivationCropModel crops;
//    @SerializedName("user")
//    private FeedUserActivationUserModel users;
    @SerializedName("c_thld")
    private int cropsThreshold;
    @SerializedName("ctf")
    private int cropsToFollow;
    @SerializedName("u_thld")
    private int usersFollowThreshold;
    @SerializedName("utf")
    private int usersToFollow;
//    @SerializedName("engg")
//    private FeedUserActivationEngagementModel engagementModel;
    @SerializedName("goal")
    private long goal;
//    private List<FeedTrendingTopicModel> trendingTopicTags;

    private int permissionType;
    private boolean showActionButton = true;
    private String actionButtonText;
    private int page;
    private boolean showThankYou;

//    public FeedItem(Integer wappCount, Integer facebookCount, Integer likesCount, Integer commentsCount, boolean canFollow,
//                    boolean isCommented, boolean isLiked, String itemHashId, String title, Integer itemType, String video,
//                    String metaTitle, String image, String text, String url, String urlStr, String contentSource,
//                    String time, String timeStamp, String userHashId, Integer userId, String userPhoto, String userBio, String userName,
//                    ArrayList<FeedItemTagModel> cropTags) {
//        this.wappCount = wappCount;
//        this.facebookCount = facebookCount;
//        this.likesCount = likesCount;
//        this.commentsCount = commentsCount;
//
//        this.isCommented = isCommented;
//        this.isLiked = isLiked;
//        this.itemType = itemType;
//        this.canFollow = canFollow;
//
//        this.itemHashId = itemHashId;
//        this.metaTitle = metaTitle;
//        this.title = title;
//        this.video = video;
//        this.image = image;
//        this.text = text;
//        this.url = url;
//        this.urlStr = urlStr;
//        this.time = time;
//        this.timeStamp = timeStamp;
//        this.contentSource = contentSource;
//
//        this.userHashId = userHashId;
//        this.userId = userId;
//        this.userPhoto = userPhoto;
//        this.userBio = userBio;
//        this.userName = userName;
//
//        this.cropTags = cropTags;
//    }

//    public ArrayList<ExpertData> getExpertData() {
//        return expertData;
//    }
//
//    public void setExpertData(ArrayList<ExpertData> expertData) {
//        this.expertData = expertData;
//    }

    public FeedItem() {

    }

//    public ArrayList<FeedItemTagModel> getCropTags() {
//        return cropTags;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContentSource() {
        return contentSource;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getItemHashId() {
        return itemHashId;
    }

    public void setItemHashId(String itemHashId) {
        this.itemHashId = itemHashId;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserBio() {
        return userBio;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(int type) {
        this.itemType = type;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUserHashId() {
        return userHashId;
    }

    public void setUserHashId(String userHashId) {
        this.userHashId = userHashId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getFacebookCount() {
        return facebookCount;
    }

    public void setFacebookCount(int facebookCount) {
        this.facebookCount = facebookCount;
    }

    public Integer getWappCount() {
        return wappCount;
    }

    public void setWappCount(Integer wappCount) {
        this.wappCount = wappCount;
    }

    public boolean getCanFollow() {
        return canFollow;
    }

    public void setCanFollow(boolean canFollow) {
        this.canFollow = canFollow;
    }

    public boolean getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(boolean isCommented) {
        this.isCommented = isCommented;
    }

    public boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

//    public ArrayList<FeedItemTagModel> getTopicTags() {
//        return topicTags;
//    }
//
//    public FeedSocialDataModel getSocialData() {
//        return socialData;
//    }
//
//    public ArrayList<SuggestedFriendDataModel> getSuggestedUsers() {
//        return suggestedUsers;
//    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
    }

    public boolean isShowActionButton() {
        return showActionButton;
    }

    public void setShowActionButton(boolean showActionButton) {
        this.showActionButton = showActionButton;
    }

    public String getActionButtonText() {
        return actionButtonText;
    }

    public void setActionButtonText(String actionButtonText) {
        this.actionButtonText = actionButtonText;
    }

//    public ArrayList<SuggestedFriendDataModel> getContacts() {
//        return contacts;
//    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getSms() {
        return sms;
    }

    public boolean isCanFollow() {
        return canFollow;
    }

    public boolean isCommented() {
        return isCommented;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public long getDuration() {
        return duration;
    }

    public long getViewCount() {
        return viewCount;
    }

//    public WeatherItem getWeather() {
//        return weather;
//    }
//
//    public ArrayList<CropsDataModel> getMyCrops() {
//        return myCrops;
//    }
//
//    public ArrayList<CropsDataModel> getFamousCrops() {
//        return famousCrops;
//    }
//
//    public void setMyCrops(ArrayList<CropsDataModel> myCrops) {
//        this.myCrops = myCrops;
//    }

    public int getReferralCount() {
        return referralCount;
    }

    public String getReferralUrl() {
        return referralUrl;
    }

//    public ProfileShopDataModel getShopDataModel() {
//        return shopDataModel;
//    }
//
//    public List<ProfileShopDataModel> getNearByStores() {
//        return nearByStores;
//    }
//
//    public List<PesticidesModel> getPesticides() {
//        return pesticides;
//    }
//
//    public List<FeedDiseaseDataModel> getDiseases() {
//        return diseases;
//    }
//
//    public FeedItemBadgeModel getBadge() {
//        return badge;
//    }

    public int getNetworkContactsCount() {
        return networkContactsCount;
    }

    public boolean isActivated() {
        return isActivated;
    }

//    public FeedUserActivationCropModel getCrops() {
//        return crops;
//    }
//
//    public FeedUserActivationUserModel getUsers() {
//        return users;
//    }

    public int getCropsThreshold() {
        return cropsThreshold;
    }

    public int getCropsToFollow() {
        return cropsToFollow;
    }

    public int getUsersFollowThreshold() {
        return usersFollowThreshold;
    }

    public int getUsersToFollow() {
        return usersToFollow;
    }

    public void setUsersToFollow(int usersToFollow) {
        this.usersToFollow = usersToFollow;
    }

    public void setCropsToFollow(int cropsToFollow) {
        this.cropsToFollow = cropsToFollow;
    }

//    public FeedUserActivationEngagementModel getEngagementModel() {
//        return engagementModel;
//    }
//
//    public List<FeedTrendingTopicModel> getTrendingTopicTags() {
//        return trendingTopicTags;
//    }
//
//    public void setTrendingTopicTags(List<FeedTrendingTopicModel> trendingTopicTags) {
//        this.trendingTopicTags = trendingTopicTags;
//    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getGoal() {
        return goal;
    }

    public boolean isShowThankYou() {
        return showThankYou;
    }

    public void setShowThankYou(boolean showThankYou) {
        this.showThankYou = showThankYou;
    }
}

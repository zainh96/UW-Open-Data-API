package Events;

import java.util.ArrayList;

/**
 * Created by ZainH on 08/09/2015.
 */
public class Event {
    private int eventId = 0;
    private String eventTitle = null;
    private String eventDescription = null;
    private String eventDescriptionRaw = null;

    private ArrayList<EventTime> times = new ArrayList<>();

    private String cost = null;
    private ArrayList<String> audience = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();

    private String websiteTitle = null;
    private String websiteUrl = null;

    private String hostTitle = null;
    private String hostUrl = null;

    // image information
    private int imageId = 0;
    private String imageFile = null;
    private String imageAlt = null;
    private String imageMime = null;
    private int imageSize = 0;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private String imageUrl = null;

    // location information
    private int locationId = 0;
    private String locationName = null;
    private String street = null;
    private String additional = null;
    private String city = null;
    private String province = null;
    private String postalCode = null;
    private String country = null;
    private double longitude = 0;
    private double latitude = 0;

    // other information
    private String siteName = null;
    private String siteId = null;
    private String site = null;
    private int revisionId = 0;
    private String link = null;
    private String calendarLink = null;
    private String updated = null;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescriptionRaw() {
        return eventDescriptionRaw;
    }

    public void setEventDescriptionRaw(String eventDescriptionRaw) {
        this.eventDescriptionRaw = eventDescriptionRaw;
    }

    public ArrayList<EventTime> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<EventTime> times) {
        this.times = times;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public ArrayList<String> getAudience() {
        return audience;
    }

    public void setAudience(ArrayList<String> audience) {
        this.audience = audience;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getHostTitle() {
        return hostTitle;
    }

    public void setHostTitle(String hostTitle) {
        this.hostTitle = hostTitle;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getImageMime() {
        return imageMime;
    }

    public void setImageMime(String imageMime) {
        this.imageMime = imageMime;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCalendarLink() {
        return calendarLink;
    }

    public void setCalendarLink(String calendarLink) {
        this.calendarLink = calendarLink;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}

package bagasekaz.projects.restapi.model;

import com.google.gson.annotations.SerializedName;

public class Recruitment {
    public Recruitment() {
    }

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("company")
    private String company;

    @SerializedName("location")
    private String location;

    @SerializedName("title")
    private String title;

    @SerializedName("company_logo")
    private String company_logo;

    @SerializedName("description")
    private String description;

    @SerializedName("company_url")
    private String company_url;

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCompany_url() {
        return company_url;
    }

    public void setCompany_url(String company_url) {
        this.company_url = company_url;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

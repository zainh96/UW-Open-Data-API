package Resources;

/**
 * Created by ZainH on 02/09/2015.
 */
public class Tutor {
    private String subject = null;
    private String catalogNumber = null;
    private String title = null;
    private int tutorsCount = 0;
    private String contactUrl = null;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTutorsCount() {
        return tutorsCount;
    }

    public void setTutorsCount(int tutorsCount) {
        this.tutorsCount = tutorsCount;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }
}

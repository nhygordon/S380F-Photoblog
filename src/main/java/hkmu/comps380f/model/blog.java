package hkmu.comps380f.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class blog {
    private long id;
    private String customerName;
    private String subject;
    private String body;
    private Map<String, Photo> photos = new ConcurrentHashMap<>();
    // Getters and Setters of id, customerName, subject, body (not attachments)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Photo getPhoto(String name) {
        return this.photos.get(name);
    }
    public Collection<Photo> getPhotos() {
        return this.photos.values();
    }
    public void addPhoto(Photo attachment) {
        this.photos.put(attachment.getId(), attachment);
    }
    public int getNumberOfPhotos() {
        return this.photos.size();
    }
}

package hkmu.comps380f.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ticket {
    private long id;
    private String customerName;
    private String subject;
    private String body;
    private Map<String, Photo> attachments = new ConcurrentHashMap<>();
    // Getters and Setters of id, customerName, subject, body (not attachments)
    public Photo getPhoto(String name) {
        return this.attachments.get(name);
    }
    public Collection<Photo> getPhotos() {
        return this.attachments.values();
    }
    public void addPhoto(Photo attachment) {
        this.attachments.put(attachment.getId(), attachment);
    }
    public int getNumberOfPhotos() {
        return this.attachments.size();
    }
}

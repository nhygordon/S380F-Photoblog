package hkmu.comps380f.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
public class Comment {@Id
@GeneratedValue
@ColumnDefault("random_uuid()")
private UUID id;
    @Column(name = "comment")
    private static String comment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static String getcomment() {
        return comment;
    }

    public void setcomment(String comment) {
        this.comment = comment;
    }
}

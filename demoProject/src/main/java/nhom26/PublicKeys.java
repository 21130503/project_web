package nhom26;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
@Data
@Getter
@Setter

public class PublicKeys {
    private int id ;
    private int userId;
    private String publicKey;
    private LocalDateTime createTime;
    private LocalDateTime endTime;

    public PublicKeys() {
    }

    public PublicKeys(int id, int userId, String publicKey, LocalDateTime createTime, LocalDateTime endTime) {
        this.id = id;
        this.userId = userId;
        this.publicKey = publicKey;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

package nhom26;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportKeys {
    private int id; // Khóa chính
    private int userId; // Khóa ngoại đến bảng users
    private int publicKeyId; // Khóa ngoại đến bảng publickeys
    private LocalDate date; // Ngày báo cáo
    private LocalTime time; // Giờ báo cáo
    private String reason; // Lý do báo cáo (có thể null)

    public ReportKeys() {}

    public ReportKeys(int id, int userId, int publicKeyId, LocalDate date, LocalTime time, String reason) {
        this.id = id;
        this.userId = userId;
        this.publicKeyId = publicKeyId;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    // Getter và Setter cho từng thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPublicKeyId() {
        return publicKeyId;
    }

    public void setPublicKeyId(int publicKeyId) {
        this.publicKeyId = publicKeyId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

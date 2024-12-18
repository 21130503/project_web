package nhom26;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReportKeys {
    private int id; // Khóa chính
    private int reportUserID; // Khóa ngoại đến bảng users
    private int publicKeysId; // Khóa ngoại đến bảng publickeys
    private LocalDate date; // Ngày báo cáo
    private LocalTime time; // Giờ báo cáo
    private String reason; // Lý do báo cáo (có thể null)

    public ReportKeys() {}

    public ReportKeys(int id, int userId, int publicKeysId, LocalDate date, LocalTime time, String reason) {
        this.id = id;
        this.reportUserID = userId;
        this.publicKeysId = publicKeysId;
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

    public int getReportUserID() {
        return reportUserID;
    }

    public void setReportUserID(int reportUserID) {
        this.reportUserID = reportUserID;
    }

    public int getPublicKeysId() {
        return publicKeysId;
    }

    public void setPublicKeysId(int publicKeysId) {
        this.publicKeysId = publicKeysId;
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

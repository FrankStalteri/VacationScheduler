package entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Vacations")
public class Vacation {

    @PrimaryKey(autoGenerate = true)
    private int vacationId;
    private String vacationTitle;
    private String vacationLodging;
    private String vacationStartDate;
    private String vacationEndDate;

    @Ignore
    public Vacation() {}

    public Vacation(int vacationId, String vacationTitle, String vacationLodging, String vacationStartDate,
                    String vacationEndDate) {
        this.vacationId = vacationId;
        this.vacationTitle = vacationTitle;
        this.vacationLodging = vacationLodging;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
    }

    public int getVacationId() {
        return vacationId;
    }

    public void setVacationId(int vacationId) {
        this.vacationId = vacationId;
    }

    public String getVacationTitle() {
        return vacationTitle;
    }

    public void setVacationTitle(String vacationTitle) {
        this.vacationTitle = vacationTitle;
    }

    public String getVacationLodging() {
        return vacationLodging;
    }

    public void setVacationLodging(String vacationLodging) {
        this.vacationLodging = vacationLodging;
    }

    public String getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(String vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public String getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(String vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "vacationId=" + vacationId +
                ", vacationTitle='" + vacationTitle + '\'' +
                ", vacationLodging='" + vacationLodging + '\'' +
                ", vacationStartDate='" + vacationStartDate + '\'' +
                ", vacationEndDate='" + vacationEndDate + '\'' +
                '}';
    }
}

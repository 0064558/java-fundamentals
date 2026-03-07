package application.entities;

import java.util.Date;
import java.util.Objects;

public class LogEntry {
    private String ursername;
    private Date moment;

    public LogEntry(String ursername, Date moment) {
        this.ursername = ursername;
        this.moment = moment;
    }

    public String getUrsername() {
        return ursername;
    }

    public void setUrsername(String ursername) {
        this.ursername = ursername;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;
        return Objects.equals(ursername, logEntry.ursername);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ursername);
    }
}

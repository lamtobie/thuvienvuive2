package thuvienvuive.Book;

import java.time.LocalDate;

public class compensationSlipDTO {
    String IDMember, IDIssue, note, IDCompensation;
    LocalDate date;

    public compensationSlipDTO() {
        this.IDMember = "";
        this.IDIssue = "";
        this.note = "";
        this.IDCompensation = "";
        this.date = null;
    }

    public compensationSlipDTO(String IDCompensation, String IDMember, LocalDate date, String note, String IDIssue) {
        this.IDMember = IDMember;
        this.IDIssue = IDIssue;
        this.note = note;
        this.IDCompensation = IDCompensation;
        this.date = date;
    }

    public String getIDMember() {
        return IDMember;
    }

    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    public String getIDIssue() {
        return IDIssue;
    }

    public void setIDIssue(String IDIssue) {
        this.IDIssue = IDIssue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIDCompensation() {
        return IDCompensation;
    }

    public void setIDCompensation(String IDCompensation) {
        this.IDCompensation = IDCompensation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

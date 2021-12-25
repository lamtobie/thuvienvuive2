package thuvienvuive.Author;

public class Author {
public String ID;
public String fistName;
public String lastName;
public String exString;
public String aboutString;
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getFistName() {
	return fistName;
}
public void setFistName(String fistName) {
	this.fistName = fistName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getExString() {
	return exString;
}
public void setExString(String exString) {
	this.exString = exString;
}
public String getAboutString() {
	return aboutString;
}
public void setAboutString(String aboutString) {
	this.aboutString = aboutString;
}
public Author(String iD, String fistName, String lastName, String exString, String aboutString) {
	super();
	ID = iD;
	this.fistName = fistName;
	this.lastName = lastName;
	this.exString = exString;
	this.aboutString = aboutString;
}

}
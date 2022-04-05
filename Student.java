public class Student {

    private String name;
    private String registration;
    private String situation;
    private String birthDate;
    private String yearOfEntry;
    private Course course;

    public Student(){

    }

    public Student(String name, String registration, String situation, String birthDate, String yearOfEntry, Course course) {
        this.name = name;
        this.registration = registration;
        this.situation = situation;
        this.birthDate = birthDate;
        this.yearOfEntry = yearOfEntry;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getRegistration() {
        return registration;
    }

    public String getSituation() {
        return situation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getYearOfEntry() {
        return yearOfEntry;
    }

    public Course getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setYearOfEntry(String yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

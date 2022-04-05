import java.util.HashSet;

public class Course {

    private String name;
    private String area;
    private String acronym;
    private HashSet<Student> students = new HashSet<>();

    //Não faz sentido ter estudantes duplicados em um curso, por isso o Set

    public Course(){

    }

    public Course(String name, String area, String acronym) {
        this.name = name;
        this.area = area;
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    //CRUD de students
    //public Set<Student> getStudents()
    //public Student getStudent()
    //public void addStudent(Student s)
    //public void setStudent()
    //public void removeStudent()
}

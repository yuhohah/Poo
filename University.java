import java.util.*;


public class University {

    private String name;
    private Location local;
    //private Course courses;
    private HashSet<Course> courses = new HashSet<>();

    //Se a universidade tem uma collection com os cursos e cada curso tem uma     
    //collection com os alunos, é possível acessar os alunos através dos 
    //cursos, e os cursos através da universidade:     
    //University.getCourse().getStudent(); - pega um estudante específico 
    //de um curso específico.
    //University.getCourse().getStudents(); - pega todos os estudantes de 
    //um curso específico.

    public University(){
        //Criar as "listas"
        this.local = new Location();

    }

    public University(Location local, HashSet<Course> courses) {
        this.local = local;
        this.courses.addAll(courses);

    }

    public String toJSON(){

        return "{\"nome\":\" + name + ,\"Cursos}";
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLocationString(){

        return (local.toString());
    }

    public void setLocal(Location loc){


        this.local.setCity(loc.getCity());
        this.local.setDistrict(loc.getDistrict());
        this.local.setStreet(loc.getStreet());
        this.local.setState(loc.getState());
    }

    public void addCurses(Course c){
        this.courses.add(c);
    }

    //CRUD de courses
    //public Set<Course> getCourses()
    //public Course getCourse()
    //public void addCourse(Course c)
    //public void setCourse()
    //public void removeCourse()
}

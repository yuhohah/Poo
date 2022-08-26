import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student>{
    private List<Student> students = new ArrayList<>();

    public StudentDao(){

    }

    public Student get(long id) {
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).getId() == id)
                return students.get(i);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
      return students;
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public void update(Student student, String[] params) {
      student.setName(params[0]);
      student.setUniversity(params[1]);
      student.setCourse(params[2]);
    }

    @Override
    public void delete(Student student) {
        students.remove(student);
    }
}

import java.util.ArrayList;
import java.util.List;

public class VacancyDao implements Dao<Vacancy>{
    private List<Vacancy> vacancies = new ArrayList<>();

    public VacancyDao(){

    }

    @Override
    public Vacancy get(long id) {
        for (int i = 0; i < vacancies.size(); i++){
            if (vacancies.get(i).getId() == id)
                return vacancies.get(i);
        }
        return null;
    }

    @Override
    public List<Vacancy> getAll() {
        return vacancies;
    }

    @Override
    public void save(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    @Override
    public void update(Vacancy vacancy, String[] params) {
      vacancy.setName(params[0]);
      vacancy.setDescription(params[1]);
      vacancy.setMaxCapacity(Integer.parseInt(params[2]));
      vacancy.setSalary(Double.parseDouble(params[3]));
    }

    @Override
    public void delete(Vacancy vacancy) {
        vacancies.remove(vacancy);
    }

    public void listToStringScreen(){
      for (Vacancy vacancy: vacancies) {
        System.out.println(vacancy.toStringScreen());
      }
    }

    public void removeStudent(Student student){
      for (Vacancy vacancy: vacancies) {
        vacancy.removeInterested(student.getId());
      }
    }
}
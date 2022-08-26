import java.util.ArrayList;
import java.util.List;

public class Student extends Entity{
  private String university;
  private String course;
  private List<Vacancy> myVacancies = new ArrayList<>();

  public Student(String name, String university, String course){
    super(name);
    this.university = university;
    this.course = course;
  }

  public Student(long id, String name, String university, String course){
    super(name, id);
    this.university = university;
    this.course = course;
  }

  public String getUniversity(){
    return university;
  }

  public void setUniversity(String university){
    this.university = university;
  }

  public String getCourse(){
    return course;
  }

  public void setCourse(String course){
    this.course = course;
  }
  
  public void addVacancy(Vacancy vacancy){
    myVacancies.add(vacancy);
  }

  public void removeVacancy(Vacancy vacancy){
    myVacancies.remove(vacancy);
  }
  
  public void removeVacancy(Long id){
    for(Vacancy vacancy: myVacancies){
      if(vacancy.getId() == id){
        removeVacancy(vacancy);
      }
    }
  }

  public String toStringFormatPrint(){
    return ("{" + this.getName() + ";" + this.getId() + ";" + this.getUniversity() + ";" + this.getCourse() + "}");
  }
  public String myVacanciesIdList(){
    String string = "";
    for(Vacancy c: myVacancies){
      string += c.getId() + ",";
    }

    return string;
  }
  public String toStringWrite(){
    return (getId() + ";" + this.getName() + ";" + this.university + ";" + this.course + ";" + myVacanciesIdList() + ";");
  }
  
  public boolean myVacanciesListToScreen(){

    int counter = 0;

    for (Vacancy vacancy: myVacancies) { 
      System.out.println(vacancy.toStringScreen());
      counter ++;
    }

    if(counter == 0){
    System.out.println("\nVocê não possui nenhuma aplicação a vagas de estágio.\n");
      return false;
    }
    else{
      return true;
    }
  }

  public boolean containsVacancy(Vacancy vacancy){
    if(myVacancies.contains(vacancy)){
      return true;
    }
    else{
      return false;
    }
  }

  public String toStringScreen(){
    return ("\nID: " + getId() + "\nNome: " + this.getName() + "\nUniversidade: " + this.university + "\nCurso: " + this.course);
  }
}


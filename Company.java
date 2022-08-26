import java.util.ArrayList;
import java.util.List;

public class Company extends Entity{
  private Address address;
  private List<Vacancy> vacancies = new ArrayList<>();
  
  public Company(String name, Address address){
    super(name);
    this.address = address;
  }
  
  public Company(long id, String name, String district, String city, String state, String country){
    super(name, id);
    this.address = new Address(district, city, state, country);

  }

  public Address getAddress(){
    return address;
  }

  public void setAddress(Address address){
    this.address = address;
  }

  public void addVacancy(Vacancy vacancy){
    vacancies.add(vacancy);
  }

  public void removeVacancy(Vacancy vacancy){
    vacancies.remove(vacancy);
  }
  
  public void removeVacancy(Long id){
    for(Vacancy vacancy: vacancies){
      if(vacancy.getId() == id){
        removeVacancy(vacancy);
        return;
      }
    }
  }

  public void vacanciesListToScreen(){

    int counter = 0;

    for (Vacancy vacancy: vacancies) { 
      System.out.println(vacancy.toStringScreen());
      counter ++;
    }

    if(counter == 0){
      System.out.println("Sua empresa não possui vagas de estágio.");
    }
  }

  public String vacanciesIdList(){

    String s = "";
    for(Vacancy v: vacancies){
      s += v.getId() + ",";
    }
    
    return s;
  }

  public String toStringWrite(){
    return (getId() + ";" + this.getName() + ";" + address.toStringWrite() + ";" + vacanciesIdList() + ";");
  }

}
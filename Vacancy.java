import java.util.ArrayList;
import java.util.List;

public class Vacancy extends Entity{
  private String description;
  private int maxCapacity;
  private double salary;
  private List<Student> interested = new ArrayList<>();

  public Vacancy(String name, String description, int maxCapacity, double salary){
    super(name);
    this.description = description;
    setMaxCapacity(maxCapacity);
    //this.maxCapacity = maxCapacity;
    this.salary = salary;
  }
  
  public Vacancy(long id, String name, String description, int maxCapacity, double salary){
    super(name, id);
    this.description = description;
    setMaxCapacity(maxCapacity);
    //this.maxCapacity = maxCapacity;
    this.salary = salary;
  }

  public Vacancy(){
    super();
  }

  public String getDescription() {
        return description;
    }

  public void setDescription(String description) {
        this.description = description;
    }

  public int getMaxCapacity() {
        return maxCapacity;
    }

  public void setMaxCapacity(int maxCapacity) {
      if(maxCapacity > 0){
        this.maxCapacity = maxCapacity;
      }
      if(maxCapacity == -1){
        this.maxCapacity = 10000;
      }
      /*else{
        System.out.println("Não é possivel adicionar valores negativos!");
        return;
      }*/
    }

  public double getSalary() {
        return salary;
    }

  public void setSalary(double salary) {
        this.salary = salary;
    }

  public void addInterested(Student student) throws MaxCapacityException{
    if(interested.contains(student)){
      System.out.println("\nAluno já aplicado na vaga!\n");
    }
    else {
      if (interested.size() >= maxCapacity){
        throw new MaxCapacityException();
      }
      interested.add(student);
      System.out.println("Parabens, você se canditou para a vaga.");
    }
  }

  public void addInterestedRead(Student student) throws MaxCapacityException{
    if(interested.contains(student)){
      return;
    }
    else {
      if (interested.size() >= maxCapacity)
        throw new MaxCapacityException();
      interested.add(student);
    }
  }

  public void removeInterested(Student student){
    interested.remove(student);
  }
  public void removeInterested(Long id){
    for(Student student: interested){
      if(student.getId() == id){
        removeInterested(student);
        return;
      }
    }
  }

  public String toStringScreen(){
    return ("{ID:" + getId() + "\nTítulo: " + this.getName() + "\nDescrição: " + this.description + "\nCapacidade Máxima de aplicações: " + this.maxCapacity + "\nSalario: " + this.salary + "}\n");
  }

  public String studentsIdList(){

    String s = "";
    for(Student v: interested){
      s += v.getId() + ",";
    }
    
    return s;
  }

  public String toStringWrite(){
    return (getId() + ";" + this.getName() + ";" + this.description + ";" + this.maxCapacity + ";" + this.salary + ";" + studentsIdList() + ";");
  }

  public void toStringInterested(){
    for(Student student: interested){
      System.out.println(student.toStringScreen());
    }
  }
}

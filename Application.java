class Application {
  public static void main(String[] args) {

    System.out.println("Teste!");
    Location loca = new Location("Juarapo", "Jardim", "goias", "Goiania");
    Course cour = new Course("Ciencia","Inf", "CC");

    University uni = new University();

    uni.setName("UFG");
    uni.setLocal(loca);
    uni.addCurses(cour);

    Crud cru = new Crud<University>();

    cru.addToJSON(uni);
  }
}
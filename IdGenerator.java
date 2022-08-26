public class IdGenerator {
    private static long counter = 0;

  //Funcao para salvar a ID no generator e Funcao para pegar ID
    public static void setCounter(long id){
      counter = id;
    } 

    public static Long getCounter(){
      return counter;
    }
  
    public static synchronized long nextId() {
        return ++counter;     
    } 
}

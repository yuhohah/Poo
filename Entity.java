public abstract class Entity{
    private long id;
    private String name;

    public Entity(String name){
        this.id = IdGenerator.nextId();
        this.name = name;
    }
    public Entity(String name, Long id){
      this.name = name;
      this.id = id;
    }
  public Entity(){
    
  }

  public void setId(long id){
        this.id = id;
  }


    public long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
}
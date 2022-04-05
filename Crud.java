import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Crud <Data> {
    
    public void addToJSON(Data obj){

        JSONObject jsonObj = new JSONObject();


        jsonObj.put("name", obj.getName());
        jsonObj.put("location", obj.getLocationString());
        jsonObj.put("curses", obj.getCurses());

        University uni = new University();




    }
    
    public void removeFromJSON(Data obj){
        
    }
    
    public University searchUniversity(String word){
      University obj = new University();
      
      return obj;
    }
    
    public void change(Data obj){
        
    }


    
}
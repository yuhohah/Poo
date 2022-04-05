public class Location {

    private String street;
    private String district;
    private String state;
    private String city;

    public Location(){

    }

    public Location(String street, String district, String state, String city) {
        this.street = street;
        this.district = district;
        this.state = state;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return  "[" + "Rua:" + street + "\nBairro:" + district + "\nEstado:" + state + "\nCidade" + city + "]";
     }
}

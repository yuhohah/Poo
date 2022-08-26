public class Address{
    private String district;
    private String city;
    private String state;
    private String country;

    public Address(String district, String city, String state, String country) {
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

  public String toStringWrite(){
    return (this.district + ";" + this.city + ";" + this.state + ";" + this.country);
  }
}
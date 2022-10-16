package com.example.pizzadatabse;
// Filip Polakowski-Karol Rafael Tesema
// i6282811					i6280873
//Group 81


public class DeliveryAddress {
    private String street;
    private String city;
    private String country;
    private String zipcode;

    public DeliveryAddress(String street , String city , String country , String zipcode){
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String toString(){
        String answer = "";
        answer+="\"street\": "+this.street+",\n";
        answer+="\"city\": "+this.city+",\n";
        answer+="\"country\": "+this.country+",\n";
        answer+="\"zipcode\": "+this.zipcode+",x`\n";
        return answer;
    }

    public String getStreet(){ return this.street;}
    public String getCity(){ return this.city;}
    public String getCountry(){ return this.country;}
    public String getZipcode(){ return this.zipcode;}

}

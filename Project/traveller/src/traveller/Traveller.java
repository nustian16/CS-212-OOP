    /*******************************************
    *   Group:                                 *
    *           Muhammad Rizwan Khalid(180459) *
    *           Abdullah Rafaqat(146905)       *  
    *   Section:                               * 
    *           BSCS-6A                        *
    *   Project:                               *
    *           Traveller Software             *
    *******************************************/

//  Main Class

package traveller;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Traveller {
    
    public static void main(String[] args) {
        //Popping up the main starting window
        Login l = new Login();
        l.setVisible(true);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setResizable(false);
    }
}
//Class for administartor to provide data for its object
class Administrator{
    
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}

class User{
    
    private String name;
    private int id;
    Plan p;
    
    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
//Plan class containing the dynamic array of other classes
class Plan implements Serializable{
    //dynamic arrays
    ArrayList<Events> events = new ArrayList<>();
    ArrayList<Hotels> hotels = new ArrayList<>();
    ArrayList<Food> food = new ArrayList<>();
    ArrayList<Places> places = new ArrayList<>();
    
    public void addEvents(Events e){
        events.add(e);
    }
    public void addHotels(Hotels h){
        hotels.add(h);
    }
    public void addFood(Food f){
        food.add(f);
    }
    public void addPlaces(Places p){
        places.add(p);
    }
}
//class events for storing the information of particular event
class Events implements Serializable{
    
    private String name;
    private String type;

    public Events() {
    }
    
    public Events(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
}
//class hotel for storing the event of particular hotel
class Hotels implements Serializable{
    
    private String name;
    private String address;

    public Hotels() {
    }
    
    public Hotels(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
}
// class food for storing the information of particular restaurant
class Food implements Serializable{
    
    private String name;
    private String address;

    public Food() {
    }
    
    public Food(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
}
//this class stores the information of landmarks of the city
class Places implements Serializable{
    
    private String name;
    private String description;

    public Places() {
    }
    
    public Places(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
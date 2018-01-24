/********************************************
*   Group Members:                          *
*       1. Abdul Wahab(196560).             *
*       2. Muhammad Rizwan Khalid(180459).  *
*   Class:                                  *
*       BSCS-6A                             *
*   Instructor:                             *
*       Dr.Anis-ur-Rehman                   *
*   Assignment:                             *
*       03                                  *
*********************************************/

package assignment;
public class Assignment {
    public static void main(String[] args) {
        SecuritySystem s = new SecuritySystem();
        s.components.setCompany("TpLink");
        s.components.setProductId(118);
        s.components.setState(true);
        System.out.println("Components have started detection:- ");
        System.out.print("\t1.");
        s.cctvCamera.recordFootage();
        System.out.print("\t2.");
        s.doorSensor.detection();
        System.out.print("\t3.");
        s.metalSensor.detection();
        System.out.print("\t4.");
        s.motionSensor.detection();
        System.out.print("\t5.");
        s.smokeSensor.detection();
        System.out.print("\t6.");
        s.soundSensor.detection();
        System.out.println("Above mentioned detections respond as follows:- ");
        System.out.print("\t1.");
        s.doorSensor.unique();
        System.out.print("\t2.");
        s.metalSensor.unique();
        System.out.print("\t3.");
        s.motionSensor.unique();
        System.out.print("\t4.");
        s.smokeSensor.unique();
        System.out.print("\t5.");
        s.soundSensor.unique();
    }
}

//Class Security system
class SecuritySystem{
    //creating objects which come under security system
    Components components = new Components();
    ControlPanel controlPanel = new ControlPanel();
    CctvCamera cctvCamera = new CctvCamera();
    Sensor smokeSensor = new Smoke();
    Sensor soundSensor = new Sound();
    Sensor doorSensor = new Door();
    Sensor motionSensor = new Motion();
    Sensor metalSensor = new Metal();
    //default constructor
    public SecuritySystem() {
        System.out.println("Security System has been Operational.");
    }
}

//Class components
class Components{
    //This class contains common attributes and methods for
    //class sensor and controlpanel
    private String company;
    private int productId;
    private boolean state;
    public Components() {
    }

    public Components(String company, int productId, boolean state) {
        this.company = company;
        this.productId = productId;
        this.state = state;
    }
    
     public void setProductId(int productId){
        this.productId =  productId;
    }
    
    public int getProductId(){
        return this.productId;//return the value of Product id
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    
    public boolean getState(){
        return this.state;// return the on/off state of the components
    }
    
    public void setCompany(String company){
        this.company =  company;
    }
    
    public String getCompany(){
        return this.company;//return the name of company
    }
    
}

//class CctvCamera having is-a relation with component class
class CctvCamera extends Components{

    public CctvCamera() {
        System.out.println("Camera is installed");
    }
    //three argument constructor
    public CctvCamera(String company, int productId, boolean state){
        super(company, productId, state);//call to component constructor
    }
    //virtual funtion for recording the footage
    public void recordFootage(){
        System.out.println("Footage is recording");
    }
    //virtual funtion to get the footage
    public void getFootage(){
        System.out.println("Footage is playing on LCD");
    }
    //virtual funtion to switch on the camera
    public void switchOn(){
        System.out.println("Camera is switched on.");
    }
    //virtual function to switch off camera
    public void switchOf(){
        System.out.println("Camera is switched off.");
    }
    //virtual function to reset camera
    public void reset(){
        System.out.println("Camera is resetted.");
    }
}

//class control panel having is-a relation with super class component
class ControlPanel extends Components{
    //extended attributes of the control panel
    private int numberOfButtons;
    private int size;
    
    public ControlPanel() {
        System.out.println("Control Panel is installed.");
    }
    //construction initiallinzing extended attributes
    public ControlPanel(int numberOfButtons, int size) {
        this.numberOfButtons = numberOfButtons;
        this.size = size;
    }
    //constructor using super constructor to initialize inherited attributes
    public ControlPanel(String company, int productId, boolean state) {
        super(company, productId, state);
    }
    //here are setters and getters
    public void setNumberOfButtons(int numberOfButtons){
        this.numberOfButtons = numberOfButtons;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getNumberOfButtons(){
        return this.numberOfButtons;
    }
    public int getSize(){
        return this.size;
    }
    //additional method for sirening the alarm.
    public void sireningAlarm(){
        System.out.println("The alarm is started.");
    }
    //virtual function for switching on lights
    public void lightsOn(){
        System.out.println("Lights are turned On.");
    }
    //virtual function for switching off lights
    public void lightsOff(){
        System.out.println("Lights are turned Off.");
    }
}

//making sensor class abstract, having is-a relation with component class
abstract class Sensor extends Components{
    //additional attributes
    private String date;
    private String place;

    public Sensor() {
    }
    //constructor for initializing the additional atributes
    public Sensor(String date, String place) {
        this.date = date;
        this.place = place;
    }
    //constructor using super constructor to initialize inherited attributes
    public Sensor(String company, int productId, boolean state) {
        super(company, productId, state);
    }
    //here are the settter and getters
    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public void setPlace(String place){
        this.place = place;
    }
    
    public String getPlace(){
        return this.place;
    }
    //over-riding the super class methods so that sub-class of sensor
    //can access
    @Override
    public void setCompany(String company){
        super.setCompany(company);
    }
    @Override
    public String getCompany(){
        return super.getCompany();
    }
    @Override
    public void setProductId(int productId){
        super.setProductId(productId);
    }
    @Override
    public int getProductId(){
        return super.getProductId();
    }
    @Override
    public void setState(boolean state){
        super.setState(state);
    }
    @Override
    public boolean getState(){
        return super.getState();
    }
    //virtual function for switching on the sensor
    public void switchOn(){
        System.out.println("Sensor is switched on.");
    }
    //virtal method for switching off the sensor
    public void switchOf(){
        System.out.println("Sensor is switched off.");
    }
    //virtual method for resetting the sensor.
    public void reset(){
        System.out.println("Sensor is resetted.");
    }
    //abstract methods having diffferent functionality for different sensors
    public abstract void detection();
    public abstract void unique();
}

class Smoke extends Sensor{
    public Smoke() {
        System.out.println("Smoke sensor is installed.");
    }
    //accessing indirect super class to initialize inherited members
    public Smoke(String company, int productId, boolean state){
        super(company,productId,state);
    }
    
    @Override
    public void detection(){
        System.out.println("Detecting amount of CO2.");
    }
    @Override
    public void unique(){
        System.out.println("Contacting the Fire Brigade.");
    }
}

class Motion extends Sensor{

    public Motion() {
        System.out.println("Motion sensor is installed.");
    }
    
    public Motion(String company, int productId, boolean state){
        super(company,productId,state);
    }
    
    @Override
    public void detection(){
        System.out.println("Detecting the heat of body.");
    }    
    @Override
    public void unique(){
        System.out.println("Doors have been Locked.");
    }
}

class Sound extends Sensor{

    public Sound() {
        System.out.println("Sound sensor is installed.");
    }
    //accessing indirect super class to initialize inherited members
    public Sound(String company, int productId, boolean state){
        super(company,productId,state);
    }
    
    @Override
    public void detection(){
        System.out.println("Detecting the voice.");
    }
    @Override
    public void unique(){
        System.out.println("Contacting the Police.");
    }
}

class Door extends Sensor{

    public Door() {
        System.out.println("Door sensor is installed.");
    }
    //accessing indirect super class to initialize inherited members
    public Door(String company, int productId, boolean state){
        super(company,productId,state);
    }
    
    @Override
    public void detection(){
        System.out.println("Detecting the magnetic field.");
    }
    
    @Override
    public void unique(){
        System.out.println("Cloasing the Door.");
    }
}

class Metal extends Sensor{

    public Metal() {
        System.out.println("Metal sensor is installed.");
    }
    //accessing indirect super class to initialize inherited members
    public Metal(String company, int productId, boolean state){
        super(company,productId,state);
    }
    
    @Override
    public void detection(){
        System.out.println("Detecting frequecy Beats");
    }
    @Override
    public void unique(){
        System.out.println("Beep,Beep...");
    }
}
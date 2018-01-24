    /*******************************************
    *   Group:                                 *
    *           Muhammad Rizwan Khalid(180459) *
    *           Abdullah Rafaqat(146905)       *  
    *   Section:                               * 
    *           BSCS-6A                        *
    *   Project:                               *
    *           Traveller Software             *
    *******************************************/

//  Main class is traveller.java
// In this frame user can add or remove different places from its file

package traveller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main extends JFrame implements ItemListener, ActionListener {
    int s=0;
    Plan p = new Plan();
    public Main() {
        super("Traveller");
        initComponents();
    }
    
    public Main(int q) {
        initComponents();
        s = q;
        //combo box 2 is the list of events, places, hotels and restaurants
        jComboBox2.addItemListener(this);
        //based on combo box 2, specific file be read and its materials will appear in combo box 1
        jComboBox1.addItemListener(this);
        //button is used for adding the events, places etc to the file of user
        jButton1.addActionListener(this);
        //button 3 is the back button which will pop up the previous window
        jButton3.addActionListener(this);
        //button 4 is for viewing the events stored in the file of user.
        jButton4.addActionListener(this);
        //button 5 is for removing the event
        jButton5.addActionListener(this);
    }
    
    @Override // Event handling for the combo boxes
    public void itemStateChanged(ItemEvent e){
        // this block sets the model for combo box 1 and link that box with specific file
        if(e.getSource() == jComboBox2)
        {    //getting the path of file through indicies of combo boxes
            String folder = "City\\"+s+"\\"+jComboBox2.getSelectedIndex()+".txt";
            File f = new File(folder);
            try { 
                BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                // following array list stores the whole line from the file
                ArrayList<String> lines = new ArrayList<>();
                // following array list stores the first word of line from the file
                ArrayList<String> temp = new ArrayList<>();
                String line = br.readLine();
                temp.add(getFirstWord(line));
                while(line != null){
                    lines.add(line);
                    line = br.readLine();
                    temp.add(getFirstWord(line));
                }
                String a[] = new String[temp.size()];
                //getting all the first words(i.e. names of events, places, hotels etc)
                for(int i = 0; i < temp.size(); i++){
                    a[i] = temp.get(i);
                }
                //setting the model of combo box 1
                DefaultComboBoxModel ourModel = new DefaultComboBoxModel(a);
                jComboBox1.setModel(ourModel);
            }
            catch(IOException i){
                i.printStackTrace();
            }
       }
        //this code block will display the content of particular event in text area
        else if(e.getSource() == jComboBox1){
            // getting the absolute path of file based on the indicies of combo boxes
            String temp = "City\\"+s+"\\"+jComboBox2.getSelectedIndex()+".txt";
            int lineNum = jComboBox1.getSelectedIndex();
            File f = new File(temp);
            try { 
                BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
               // following array list stores the whole line as a string
                ArrayList<String> lines = new ArrayList<>();
                String line = br.readLine();
                while(line != null){
                    lines.add(line);
                    line = br.readLine();
                }
                // information of selected event will be displayed
                jTextArea1.setText(lines.get(lineNum));
            }
            catch(IOException a){
                a.printStackTrace();
            }
        }
    }
    
    @Override //Event handling for the buttons
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == jButton3){ // if user presses back button then previous window will pop-up
            this.setVisible(false);
            USERNAME u = new USERNAME();
            u.setVisible(true);
            u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            u.setResizable(false);
        }
        // button 1 is for adding the events to the user file
        if(e.getSource() == jButton1){
            int a1 = jComboBox1.getSelectedIndex();
            int a2 = jComboBox2.getSelectedIndex();
            // getting the apsolute path of specific file using indicies of combo boxes
            String temp = "City\\"+s+"\\"+a2+".txt";
            // if user selects events tab from first combo box then event will be added to dynamic array in class plan
            if(a2 == 0){
                //creating instance of class Events
                Events e1 = new Events();
                int lineNum = a1;
                File f = new File(temp);
                try { 
                    BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    //following arraylist stores the whole line 
                    ArrayList<String> lines = new ArrayList<>();
                    String line = br.readLine();
                    while(line != null){
                        lines.add(line);
                        line = br.readLine();
                    }
                    //this line sets the name of event 
                    e1.setName(getFirstWord(lines.get(lineNum)));
                    //this line sets the event type and ignores name of the event
                    e1.setType(getRestString(lines.get(lineNum)));
                    //this line adds the event
                    p.addEvents(e1);
                    JOptionPane.showMessageDialog(null,"Event Added Successfully","Event Added Successfully",JOptionPane.PLAIN_MESSAGE);
                    //here comes the serialization
                    FileOutputStream fileOut =
                    new FileOutputStream("User.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(p);
                    out.close();
                    fileOut.close();
                }
                catch(IOException a){
                    a.printStackTrace();
                }
            }
            // if user selects places tab from first combo box then landmark will be added to dynamic array in class plan
            if(a2 == 1){
                //creating instance of class place
                Places p1 = new Places();
                int lineNum = a1;
                File f = new File(temp);
                try { 
                    BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    //following arraylist stores the whole line from the file of places of selected city
                    ArrayList<String> lines = new ArrayList<>();
                    String line = br.readLine();
                    while(line != null){
                        lines.add(line);
                        line = br.readLine();
                    }
                    //following line sets the name of the place
                    p1.setName(lines.get(lineNum));
                    //follwoing line add the place to the dynamic arraylist
                    p.addPlaces(p1);
                    JOptionPane.showMessageDialog(null,"Place Added Successfully","Place Added Successfully",JOptionPane.PLAIN_MESSAGE);
                    //here comes the serialization
                    FileOutputStream fileOut =new FileOutputStream("User.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(p);
                    out.close();
                    fileOut.close();
                }
                catch(IOException a){
                    a.printStackTrace();
                }
            }
            // if user selects food tab from first combo box then restaurant will be added to dynamic array in class plan
            if(a2 == 2){
                //creating the instance of the class food
                Food f1 = new Food();
                int lineNum = a1;
                File f = new File(temp);
                try { 
                    BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    //following arraylist stores the whole line from the file of restaurant of selected city
                    ArrayList<String> lines = new ArrayList<>();
                    String line = br.readLine();
                    while(line != null){
                        lines.add(line);
                        line = br.readLine();
                    }
                    //following line set the name of the restaurant
                    f1.setName(getFirstWord(lines.get(lineNum)));
                    //following line set the address of the restaurant
                    f1.setAddress(getRestString(lines.get(lineNum)));
                    //following line add the restaurant to the dynamic array list
                    p.addFood(f1);
                    JOptionPane.showMessageDialog(null,"Restaurant Added Successfully","Restaurant Added Successfully",JOptionPane.PLAIN_MESSAGE);
                    //here comes the serialization of the class
                    FileOutputStream fileOut =new FileOutputStream("User.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(p);
                    out.close();
                    fileOut.close();
                }
                catch(IOException a){
                    a.printStackTrace();
                }
            }
            // if user selects hotels tab from first combo box then hotel will be added to dynamic array in class plan
            if(a2 == 3){
                //creating the instance of the class hotels
                Hotels h1 = new Hotels();
                int lineNum = a1;
                File f = new File(temp);
                try { 
                    BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
                    //following arraylist stores the whole line from the file of hotels of selected city
                    ArrayList<String> lines = new ArrayList<>();
                    String line = br.readLine();
                    while(line != null){
                        lines.add(line);
                        line = br.readLine();
                    }
                    //following line sets the name of the hotel
                    h1.setName(getFirstWord(lines.get(lineNum)));
                    //following line sets the address of the hotel
                    h1.setAddress(getRestString(lines.get(lineNum)));
                    //following line add hotels to the dynamic arraylist
                    p.addHotels(h1);
                    JOptionPane.showMessageDialog(null,"Hotel Added Successfully","Hotel Added Successfully",JOptionPane.PLAIN_MESSAGE);
                    // here comes the serialization of the class plan
                    FileOutputStream fileOut =new FileOutputStream("User.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(p);
                    out.close();
                    fileOut.close();
                }
                catch(IOException a){
                    a.printStackTrace();
                }
            }
            
        }
        if(e.getSource() == jButton5){
            JOptionPane.showMessageDialog(null,"Removed","Removed",JOptionPane.INFORMATION_MESSAGE);
        }
        // button 4 (view button) pops up the reader frame and displays the content of user file
        if(e.getSource() == jButton4){
            // temporary string to be used for displaying information of text Area
            String t1 = "", t2 = "", t3 = "", t4 = "";
            this.setVisible(false);
            Reader r = new Reader();
            r.setVisible(true);
            r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            r.setResizable(false);
            try {
                // deserialization of the plan
                FileInputStream fileIn = new FileInputStream("User.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                p   = (Plan) in.readObject();
                //for loops for formatting temporary string and then updating the text area
                for(int i = 0 ; i < p.events.size(); i++){
                    t1 += String.format(p.events.get(i).getName()+"\t\t"+p.events.get(i).getType()+"\n");
                }
                for(int i = 0 ; i < p.places.size(); i++){
                    t2 += String.format(p.places.get(i).getName()+"\n");
                }
                for(int i = 0 ; i < p.food.size(); i++){
                    t3 += String.format(p.food.get(i).getName()+"\t\t"+p.food.get(i).getAddress()+"\n");
                }
                for(int i = 0 ; i < p.hotels.size(); i++){
                    t4 += String.format(p.hotels.get(i).getName()+"\t\t"+p.hotels.get(i).getAddress()+"\n");
                }
                r.jTextArea1.setText(t1+t2+t3+t4);
                in.close();
                fileIn.close();
            }catch(IOException i) {
                i.printStackTrace();
            }catch(ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }
    // following method returns the first word of the string
    public String getFirstWord(String t){
        String temp = "";
        try{int n = 0;
        while(t.charAt(n) != '\t'){
            temp = temp + t.charAt(n);
            n++;
        }}catch(Exception as){
        
        }
        
        return temp;
    }
    // following method returns the string except first word
    public static String getRestString(String t){
        int n = 0, var=0;
        String temp = "";
        for(int u=0; u< t.length(); u++){
            if(t.charAt(u) == '\t'){
                var = u;
            }
        }var++;
        while(var< t.length()){
            temp = temp+t.charAt(var);
            var++;
        }
        return temp;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(18, 168, 157));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("USER");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/traveller/MALE1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(18, 168, 157));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("BACK");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("LIST: ");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EVENTS", "PLACES", "FOOD", "HOTELS" }));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ADD");

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("SHOW");

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("REMOVE");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("CHOICE: ");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/traveller/Travellinng1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addGap(12, 12, 12)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>                        
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;//button1 for adding 
    private javax.swing.JButton jButton3;//button3 for back
    private javax.swing.JButton jButton4;//button4 for viewing
    private javax.swing.JButton jButton5;////button5 for removing
    private javax.swing.JComboBox<String> jComboBox1;//1 for default
    private javax.swing.JComboBox<String> jComboBox2;//2 for choice
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;   //label5 for default
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
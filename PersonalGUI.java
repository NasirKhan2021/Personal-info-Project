import javafx.scene.Group;
import javafx.scene.Scene;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
* This is a database management program which takes Personal Information
* from the user, stores it permanently which can then be viewed later.
*
* @author  Nasir Khan
* @author  Heet thakkar
* @version 1.0
* @since   2022-07-27
*/

//GUI Class which has all the Graphic user Elements
public class PersonalGUI extends Application {

    PersonalRecord personal= new PersonalRecord();
    TextField name= new TextField();
    TextField gender= new TextField();
    TextField dofb= new TextField();
    TextField age= new TextField();
    TextField mobile= new TextField();
    
    
    Scene scene, scene2;
    Stage OneStage;

    public int e;
    String data="";
       
    
    @Override
    public void start(Stage OneStage){   // This is the main opening page of the application
        
        Text text= new Text("Personal Details");
        text.setFont(Font.font("Calibri",FontWeight.BOLD,15));
        text.setTextAlignment(TextAlignment.CENTER);
              
        GridPane gridpane= new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(10,10,10,10));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        //Fields of the "Personal Details" page

        gridpane.add(new Label("Full Name: "), 0 ,0);
        gridpane.add(name, 1,0);
        gridpane.add(new Label("Gender: "), 0,1);
        gridpane.add(gender, 1,1);
        gridpane.add(new Label("Date of Birth: "),0,2);
        gridpane.add(dofb, 1,2);
        gridpane.add(new Label("Age:"),0,3);
        gridpane.add(age, 1,3);
        gridpane.add(new Label("Mobile: " ),0,4);
        gridpane.add(mobile, 1,4);
   

        //Buttons of "Personal Details" page
        Button add= new Button ("Add");
        add.setOnAction(e -> 
        {
            addition();
            start(OneStage);

        });
        
        //This Button takes you to the display page
        Button display= new Button ("Display");
        display.setOnAction(e ->
        {
            OneStage.close();
            new display(OneStage);
        
            
        }); 

        //This button clear the written text
        Button clear = new Button ("Clear");
        clear.setOnAction(e -> 
        {
            name.setText("");
            gender.setText("");
            dofb.setText("");
            age.setText("");
            mobile.setText("");
        });
       
        //This button saves changes to the file and exits the program
        Button exit = new Button ("Exit");
        exit.setOnAction(e -> exit());


        //Alignment of all buttons horizontally
        HBox hbox= new HBox(8);
        hbox.getChildren().addAll(add,display,clear,exit);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setSpacing(10);
        
        
        //Stacks all the pane one over another
        StackPane spane= new StackPane();
        spane.getChildren().addAll(text,gridpane,hbox);
        StackPane.setAlignment(text, Pos.TOP_CENTER);
        StackPane.setAlignment(gridpane, Pos.CENTER);
        spane.setPadding(new Insets(10, 10,10,10));

    

        //putting the scene on the stage
        Scene scene= new Scene(spane,400,300); 
        OneStage.setTitle("Personal Details ");
        OneStage.setScene(scene);
        OneStage.show();
  
    }

    //Addition class which adds data to the txt File through arraylist.
    private void addition()
    {
        try {
        personal.setName(this.name.getText());
        personal.setGender(this.gender.getText());
        personal.setDob(this.dofb.getText());
        personal.setAge(Integer.parseInt(this.age.getText()));
        personal.setMobNo(Long.parseLong(this.mobile.getText()));
        }
        catch (Exception exception)
        {
            personal.setMobNo(Long.parseLong(this.mobile.getText()));
            System.out.println("Invalid input");
        }
        name.setText("");
        gender.setText("");
        dofb.setText("");
        age.setText("");
        mobile.setText("");
               
    }

    //Overwrites the file with the new ArrayList then closes the program
    public void exit()
    {
        try {
            personal.save();
        } catch (FileNotFoundException e) {
          
            System.out.println("File not found!");

        }
        System.exit(0);

    }

   //main method
    public static void main(String[] args)
    {
        launch(args);
    }

    // Class which displays the content of the section
     public class display extends Stage{ 
        Scene scene; 
        static String data="";

        display(Stage OneStage)
        {

        TextField searchfield= new TextField();

        GridPane gpane= new GridPane();
        gpane.setAlignment(Pos.CENTER);
        gpane.setPadding(new Insets(10,10,10,10));
        gpane.setHgap(10);
        gpane.setVgap(10);

        //Fields of the display scene
        gpane.add(new Label("Name: "),0,0);
        gpane.add(new Label("Gender: "),0,1);
        gpane.add(new Label("Date of Birth: "),0,2);
        gpane.add(new Label("Age: "),0,3);
        gpane.add(new Label("Mobile No; "),0,4);        
        
        //Retriving and printing data from the txt File
        try {
            Scanner sc= new Scanner(personal.toString());
            for (int i=0;i<=4;i++)
            {
                gpane.add(new Label((i==0) ? data=sc.next() : sc.next()),1,i);
            }
        }
        catch(Exception IndexOutOfBoundsException)
        {
            new window("End of File reached",OneStage);
        }

         //This Button will take you back to the first output page of addition
         Button add= new Button ("Add");
         add.setOnAction(e ->
         { 
            personal= new PersonalRecord();
            start(OneStage);
         } );
                 
         Button delete = new Button ("Delete");
         delete.setOnAction(e -> delete());
 
         Button search = new Button ("Search");
         search.setOnMousePressed(e -> search(searchfield.getText()));
         gpane.add(search,2,5);
         gpane.add(searchfield,1,5);
        
         
         Button exiButton = new Button ("Exit");
         exiButton.setOnAction(e ->exit());
 
        //Prompts the next section of data to appear
        Button frontarrow= new Button (">");
        frontarrow.setOnAction(e -> {
            gpane.getChildren().clear();
            OneStage.close();
            new display(OneStage);
        });
        
        HBox hbox= new HBox(8);
        hbox.getChildren().addAll(add, delete, exiButton);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setSpacing(10);

        StackPane spane= new StackPane();
        spane.getChildren().addAll(gpane,hbox,frontarrow);

        StackPane.setAlignment(frontarrow, Pos.CENTER_RIGHT);
        spane.setPadding(new Insets(10, 20,10,20));
        Scene scene2= new Scene(spane,400,300);
        OneStage.setTitle("Personal Information");
        OneStage.setScene(scene2);
        OneStage.show();
        
        
  
      }
    
      // This section deletes the currernt displaying page
      public void delete()
      {
        personal.delete(data);

      }
    }
    
    //This class is used for exceptional handling
    public class window
    { 
        window(String str,Stage OneStage)
        {
            GridPane gridpane= new GridPane();
            gridpane.add(new Label(str),0,0);
            
            Button exiButton = new Button ("Exit");
            exiButton.setOnAction(e ->exit());
            
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(gridpane,exiButton);
            stackpane.setAlignment(Pos.CENTER);

            Group group= new Group(stackpane);

            Scene windowscene= new Scene(group,200,200);
            OneStage.setTitle("Window");
            OneStage.setScene(windowscene);
            OneStage.show();
        }
    }

    //This his function prompts a search of the database
      public void search(String field) 
      {
        personal.search(field);
        new display(OneStage);
      }
      
  
} 
 

    
    
import java.io.FileNotFoundException;

public class PersonalRecord extends PersonalRecordList {
    //assigning variable to particualar data-type
    private String name;
    private String dob;
    private int age;
    private String gender;
    private long mobNo;

    PersonalRecordList RecordList= new PersonalRecordList();
    //Constructor

    PersonalRecord() 
    {
        RecordList= new PersonalRecordList();
        name="";
        dob="";
        age=0;
        gender="";
        mobNo=0;

    }
    

    //Accessor & mutators
    public void setName(String name){  //setter class for name
        this.name=name;
        RecordList.addName(name);
    }

    public String getName(){  // getter class for name
        return RecordList.getName();
    }

    public void setDob(String dob){ //setter class for Date of birth
        this.dob=dob;
        RecordList.addDOB(this.dob);
    }

    public String getDob(){  //getter class for date of birth
        return RecordList.getDOB();
    }

    public void setGender(String gender){ // setter class for gender
        this.gender=gender;
        RecordList.addGender(this.gender);
    }

    public String getGender(){  // getter class for gender
        return RecordList.getGender();
    }

    public void setAge(int age){  // setter class for gender
        this.age=age;
        RecordList.addAge(this.age);
    }

    public int getAge(){  // getter class for age
        return RecordList.getAge();
    }

    public void setMobNo(Long mobNo){  // setter class for mobile No
        this.mobNo=mobNo;
        RecordList.addMobile(this.mobNo);
    }

    public int getMobNo(){  // getter class for Mobile No
        return RecordList.getMobNo();
    }

    //This function is invoked if a user presses the exit button
    public void save() throws FileNotFoundException{ 
        RecordList.save();
    }

    public void delete(String data) {  // Delete function
        RecordList.delete(data);
    
    }

    public void search(String field){  // Search function
        RecordList.search(field);
    }

    //Print personal information
    public String toString(){
        return String.format(" %s %s %s %d %d",
         RecordList.getName(), RecordList.getGender(), RecordList.getDOB(),  RecordList.getAge(), RecordList.getMobNo());
    }
}
    
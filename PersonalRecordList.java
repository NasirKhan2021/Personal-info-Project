import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
 
public class PersonalRecordList {

//This is a buffer arraylist in the which the data would be stored temprorily
List<String> record= new ArrayList<String>(); 
static int counter=0; 

PersonalRecordList() 
{
  //Importing .txt file and passing it through Scanner class to chop sections of text
  try (Scanner sc = new Scanner(new java.io.File("Personal_Info/SchoolRecord.txt"))) {
    while (sc.hasNext())
    {
      record.add(sc.next());
    }
  } catch (FileNotFoundException e) {
    System.out.println("File not Found");
    e.printStackTrace();
  } 
  
}
 
public void addName(String name) {  // adding name in the arraylist
  record.add(name);
}

public void addGender(String gender) { // adding gender in the arrayList
  record.add(gender);
}

public void addMobile(Long mobile) {  // adding mobile in the arrayList
  record.add(Long.toString(mobile));
}

public void addAge(int age) {  // adding age in the arrayList
  record.add(Integer.toString(age));
}

public void addDOB(String DOB) {  // adding date of birth in the arrayList
  record.add(DOB);
}

public String getName() { // retrieving name from the arrayList
  return record.get(counter++);
}
 
public String getGender() { //retrieving gender from the arrayList
  return record.get(counter++);
}

public int getAge(){  // retreiving age from the arrayList
  return Integer.parseInt(record.get(counter++));
}
 
public String getDOB(){   // retreiving date of birth from the arrayList
  return record.get(counter++);
}

public int getMobNo(){  // retreiving Mobile No. from the arrayList
  return Integer.parseInt(record.get(counter++));
}

public void delete(String data){  // Removing from the arrayList

  for (int i=0;i<record.size();i++)
  {
    if ((record.get(i)).equalsIgnoreCase(data)) 
    { 
      for (int c=0;c<5;c++) {  
        record.remove(i);
      }
      break;
    }
  }  
  
}


/**
 * After making all the changes, this sections write the new arraylist to the .txt file
 * @throws FileNotFoundException
 */
public void save() throws FileNotFoundException{ 
 
  java.io.File file = new java.io.File("Personal_Info/SchoolRecord.txt");
  int i=0;
  try (java.io.PrintWriter output = new java.io.PrintWriter(file)) 
  {
    while (i<record.size())
    {

      output.printf(" %s",record.get(i++));
      if (i%5==0)
      {
        output.print("\n");
      }

    }
  } 
   
   catch (Exception e) {
    System.out.println("File not found !");
  }

  
}

//This section search the whole arraylist for the specified word passed
public void search(String field){ 
  for (int i=0;i<record.size();)
  {
    if ((record.get(i)).equalsIgnoreCase(field));
    { counter=i;
    break;
    }
  }

}

}





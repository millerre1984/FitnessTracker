/* object class for cardio machine
 * will be part of fitness tracker
 * backed up 3/16/16
 * */
import java.util.*;

public class CardioMachine
{
  // fields
  private String name;
  private ArrayList<Date> date; // date, arraylist of Date objects
  private ArrayList<Integer> time; // time on machine
  private int entries;
  
  // constructors
  public CardioMachine()
  {
    name = "";
    time = new ArrayList<Integer>();
    date = new ArrayList<Date>();
    entries = -1; // start there to track new entries properly, act as flag for no entries
  }
  
  public CardioMachine(String n)
  {
    name = n;
    time = new ArrayList<Integer>();
    date = new ArrayList<Date>();
    entries = -1;
  }
  
  // methods
  // set name
  public void setName(String n)
  {
    name = n;
  }
  
  // set time
  public void setTime(int t)
  {
    time.add(t);
    Date tempDate = new Date();
    date.add(tempDate);
    entries++;
  }
  
  public void setTime(int t, long d)
  {
    time.add(t);
    Date tempDate = new Date(d);
    date.add(tempDate);
    entries++;
  }
  
  // get name
  public String getName()
  {
    return name;
  }
  
  //get workout variables
  public int getTime(int e)
  {
    return time.get(e);
  }
  
  public long getDate(int e)
  {
    return date.get(e).getTime();
  }
  
  // get last time
  public void getCurrentTime()
  {
    int t;
    String d;
    t = time.get(entries);
    d = date.get(entries).toString();
    System.out.printf("%s -- %d minutes", d, t);
  }
  
  // get all times
  public void printTimes()
  {
    int i, t;
    String d;
    for (i = 0; i <= entries; i++)
    {
      t = time.get(entries);
      d = date.get(entries).toString();
      System.out.printf("%d. %s -- %d minutes\n", i + 1, d, t);
    }
  }
  
  // get entries
  public int getEntries()
  {
    return entries;
  }
}
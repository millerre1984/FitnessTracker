/* class for circuit machine tracking
 * Will be part of fitness tracker program
 * Backed up 3/16/16
 * */

import java.util.*;
public class CircuitMachine
{
  // fields
  private String name; // name of machine
  private ArrayList<Date> date; // date, arraylist of Date objects
  private ArrayList<Integer> reps; // # reps
  private ArrayList<Integer> weight; // weight lifted
  private int entries; // tracks total number of entries
  
  // constructors
    public CircuitMachine() // basic
  {
    name = "";
    date = new ArrayList<Date>();
    reps = new ArrayList<Integer>();
    weight = new ArrayList<Integer>();
    entries = -1; // start there to track new entries properly, act as flag for no entries
  }
  
  public CircuitMachine(String n) // enter name directly
  {
    name = n;
    date = new ArrayList<Date>();
    reps = new ArrayList<Integer>();
    weight = new ArrayList<Integer>();
    entries = -1;
  }
  
  // methods
  // set name
  public void setName(String n)
  {
    name = n;
  }
  
  // set reps (two versions)
  public void setReps(int r, int w)
  {
    Date tempDate = new Date();
    date.add(tempDate);
    reps.add(r);
    weight.add(w);
    entries++;
  }
  
  public void setReps(int r, int w, long d)
  {
    Date tempDate = new Date(d);
    date.add(tempDate);
    reps.add(r);
    weight.add(w);
    entries++;
  }
  
  // get name
  public String getName()
  {
    return name;
  }
  
  //get workout variables (e is index of workout)
  public int getReps(int e)
  {
    return reps.get(e);
  }
  
  public int getWeight(int e)
  {
    return weight.get(e);
  }
  
  public long getDate(int e)
  {
    return date.get(e).getTime();
  }
  
  // get current reps
  public void getCurrentReps()
  {
    int r, w;
    String d;
    r = reps.get(entries);
    w = weight.get(entries);
    d = date.get(entries).toString();
    System.out.printf("%s -- %d reps at %d lbs", d, r, w);
  }
  
  // get all reps
  public void printReps()
  {
    int i, r, w;
    String d;
    for (i = 0; i <= entries; i++)
    {
      r = reps.get(i);
      w = weight.get(i);
      d = date.get(i).toString();
      System.out.printf("%d. %s -- %d reps at %d lbs\n", i + 1, d, r, w);
    }
  }
  
  // get entries
  public int getEntries()
  {
    return entries;
  }
}
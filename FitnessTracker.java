/* Fitness Tracker program
 * This will be the main program
 * Will use CardioMachine and CircuitMachine classes
 * use while loop for menu
 * choices - add machine, list machines, add reps, display reps, exit
 * use arraylist to contain machine objects
 * Use Scanner and Print Writer to import and export data to .txt
 * 
 * backed up 3/16/16
 * 3/16/16 - replaced do-while loops with while loops in non-menu methods
 * 
 * Ideas for future improvement:
 * Reorganize menu (create submenus), improve UI messages
 * create machine abstract class, use single list for both types
 * Reorganize classes and methods - add arraylists as arguments to methods (rather than initialize outside of main)?
 * Create separate class for various methods called in main?
 * Add error-proofing where possible
 * Add way to rearrange or delete entries?
 * */
import java.util.*; // will need Scanner for user input
import java.io.*; // import buffered reader

public class FitnessTracker
{
  static Scanner sc1, sc2; // for use with import method (can reuse single?)
  static Scanner sc = new Scanner(System.in); // for use with menu and other methods
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // for use creating machine objects
  static List<CircuitMachine> circuitList = new ArrayList<CircuitMachine>(); // for storing machine objects
  static List<CardioMachine> cardioList = new ArrayList<CardioMachine>(); // for storing machine objects
  static PrintWriter pw1, pw2; // for use with data export method (can reuse single?)
  
  public static void main(String[] args) throws IOException, FileNotFoundException // for br, file reading/writing
  {
    int mainMenuChoice;
        
    do // want to run menu first, then check for result, run appropriate method
    {
      mainMenuChoice = mainMenu();
      if (mainMenuChoice == 1)
        addCircuitMachines();
      else if (mainMenuChoice == 2)
        listCircuitMachines();
      else if (mainMenuChoice == 3)
        addCardioMachines();
      else if (mainMenuChoice == 4)
        listCardioMachines();
      else if (mainMenuChoice == 5)
        addRepsCircuit();
      else if (mainMenuChoice == 6)
        addRepsCardio();
      else if (mainMenuChoice == 7)
        getLatestReps();
      else if (mainMenuChoice == 8)
        getLatestTime();
      else if (mainMenuChoice == 9)
        printCircuitWorkouts();
      else if (mainMenuChoice == 10)
        printCardioWorkouts();
      else if (mainMenuChoice == 11)
        exportData();
      else if (mainMenuChoice == 12)
        importData();
    }
    while (mainMenuChoice != -1); // use -1 as exit value
  }
  
  public static int mainMenu() // outer-most menu
  {
    int choice;
    do
    {
      System.out.println(" Make a selection:");
      System.out.println(" 1. Add circuit machines");
      System.out.println(" 2. List current circuit machines");
      System.out.println(" 3. Add cardio machines");
      System.out.println(" 4. List current cardio machines");
      System.out.println(" 5. Add workout to a circuit machine");
      System.out.println(" 6. Add workout to a cardio machine");
      System.out.println(" 7. See last workout on a circuit machine");
      System.out.println(" 8. See last workout on a cardio machine");
      System.out.println(" 9. See all workout on a circuit machine");
      System.out.println("10. See all workout on a cardio machine");
      System.out.println("11. Export data");
      System.out.println("12. Import data");
      System.out.println("-1. Exit program");
      choice = sc.nextInt();
      return choice;
    }
    while (choice != -1);
  }
  
  // adding machine objects
  public static void addCircuitMachines() throws IOException
  {
    boolean more = true; // track whether user wants to repeat method
    String name;
    System.out.println("Adding circuit machines");
    while (more)
    {
      System.out.print("What is the name of the new machine?");
      name = br.readLine();  
      CircuitMachine machine = new CircuitMachine(name); // calls constructor (defined in class)
      circuitList.add(machine); // adds to List
      System.out.print("Do you want to add more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  public static void addCardioMachines() throws IOException // all circuit methods have equivalent cardio methods
  {
    boolean more = true;
    String name;
    System.out.println("Adding cardio machines");
    while (more)
    {
      System.out.print("What is the name of the new machine?");
      name = br.readLine();  
      CardioMachine machine = new CardioMachine(name);
      cardioList.add(machine);
      System.out.print("Do you want to add more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }

  // listing machines
  public static void listCircuitMachines()
  {
    System.out.println("List of circuit machines");
    for (int i = 0; i < circuitList.size(); i++) // index of objects in List
    {
      System.out.println("Index: " + i + " - Item: " + circuitList.get(i).getName());
    }
    System.out.println();
  }
  
  public static void listCardioMachines()
  {
    System.out.println("List of cardio machines");
    for (int i = 0; i < cardioList.size(); i++)
    {
      System.out.println("Index: " + i + " - Item: " + cardioList.get(i).getName());
    }
    System.out.println();
  }
  
  // adding reps
  public static void addRepsCircuit()
  {
    System.out.println("Adding workouts on circuit machines");
    boolean more = true;
    int index, reps, weight;
    while (more)
    {
      listCircuitMachines();
      System.out.print("Enter the index number of the machine you used: ");
      index = sc.nextInt();
      System.out.print("Enter the number of reps: ");
      reps = sc.nextInt();
      System.out.print("Enter the weight: ");
      weight = sc.nextInt();
      circuitList.get(index).setReps(reps, weight); // uses current date
      System.out.print("Do you want to add workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  public static void addRepsCardio()
  {
    System.out.println("Adding workouts on cardio machines");
    boolean more = true;
    int index, time;
    while (more)
    {
      listCardioMachines();
      System.out.print("Enter the index number of the machine you used: ");
      index = sc.nextInt();
      System.out.print("Enter the number of minutes: ");
      time = sc.nextInt();
      cardioList.get(index).setTime(time);
      System.out.print("Do you want to add workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  // get current workouts
  public static void getLatestReps()
  {
    System.out.println("Latest workouts on circuit machines");
    boolean more = true;
    int i;
    while (more)
    {
      listCircuitMachines(); // i = index of List, entries = # of workouts in object (equivalent to index of workout in object)
      System.out.print("Select the machine: ");
      i = sc.nextInt();
      if (circuitList.get(i).getEntries() == -1) // -1 means no workouts added
        System.out.println("No workouts with this machine yet\n");
      else
      {
        circuitList.get(i).getCurrentReps();
        System.out.println("\n");
      }
      System.out.print("Do you want to see latest workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  public static void getLatestTime()
  {
    System.out.println("Latest workouts on cardio machines");
    boolean more = true;
    int i;
    while (more)
    {
      listCardioMachines();
      System.out.print("Select the machine: ");
      i = sc.nextInt();
      if (cardioList.get(i).getEntries() == -1)
        System.out.println("No workouts with this machine yet\n");
      else
      {
        cardioList.get(i).getCurrentTime();
        System.out.println("\n");
      }
      System.out.print("Do you want to see latest workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  // print all workouts
  public static void printCircuitWorkouts()
  {
    System.out.println("All workouts on circuit machines");
    boolean more = true;
    int i;
    while (more)
    {
      listCircuitMachines();
      System.out.print("Select the machine: ");
      i = sc.nextInt();
      if (circuitList.get(i).getEntries() == -1)
        System.out.println("No workouts with this machine yet\n");
      else
      {
        circuitList.get(i).printReps();
        System.out.println("\n");
      }
      System.out.print("Do you want to see workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  public static void printCardioWorkouts()
  {
    System.out.println("All workouts on cardio machines");
    boolean more = true;
    int i;
    while (more)
    {
      listCardioMachines();
      System.out.print("Select the machine: ");
      i = sc.nextInt();
      if (cardioList.get(i).getEntries() == -1)
        System.out.println("No workouts with this machine yet\n");
      else
      {
        cardioList.get(i).printTimes();
        System.out.println("\n");
      }
      System.out.print("Do you want to see workouts for more machines? (true/false)");
      more = sc.nextBoolean();
    }
  }
  
  // print all data to file
  public static void exportData() throws FileNotFoundException // can add method to classes?
  {
    // for circuit machines
    pw1 = new PrintWriter("circuit.txt");
    for (int i = 0; i < circuitList.size(); i++) // goes through list of machines
    {
      pw1.printf("%s \n", circuitList.get(i).getName());
      if (circuitList.get(i).getEntries() != -1) // only looks for reps if have some entered
      {
        for (int j = 0; j <= circuitList.get(i).getEntries(); j++) // entries variable stores # of workouts
        {
          int r = circuitList.get(i).getReps(j); // have to use individual get methods
          int w = circuitList.get(i).getWeight(j);
          long d = circuitList.get(i).getDate(j); // date stored as long, not int
          pw1.printf("%d %d %d \n", r, w, d);
        }
      }
      pw1.printf("-1 \n"); // acts as flag for end of machine object
    }
    pw1.close();
    // for cardio machines
    pw2 = new PrintWriter("cardio.txt");
    for (int i = 0; i < cardioList.size(); i++)
    {
      pw2.printf("%s \n", cardioList.get(i).getName());
      if (cardioList.get(i).getEntries() != -1)
      {
        for (int j = 0; j <= cardioList.get(i).getEntries(); j++)
        {
          int t = cardioList.get(i).getTime(j);
          long d = cardioList.get(i).getDate(j);
          pw2.printf("%d %d \n", t, d);
        }
      }
      pw2.printf("-1 \n");
    }
    pw2.close();
    System.out.println("Data exported");
  }
  
  // read data from file
  public static void importData() throws FileNotFoundException
  {
    String name;
    int r, w, t; // variables for both circuit and cardio machines
    long d;
    int i = 0; // counter for index of objects in List
    //import circuit machines
    sc1 = new Scanner(new FileReader("circuit.txt"));
    while (sc1.hasNext())
    {
      // create object with string
      name = sc1.nextLine();
      CircuitMachine machine = new CircuitMachine(name);
      circuitList.add(machine);
      // get next int, check to see if -1. If not, add workouts
      r = sc1.nextInt();
      while (r != -1)
      {
        w = sc1.nextInt();
        d = sc1.nextLong();
        circuitList.get(i).setReps(r, w, d);
        r = sc1.nextInt();
      }
      sc1.nextLine(); // to clear line break before reading next machine
      i++; // iterates counter for next object
    }
    sc1.close();
    // import cardio machines
    i = 0;
    sc2 = new Scanner(new FileReader("cardio.txt"));
    while (sc2.hasNext())
    {
      // create object with string
      name = sc2.nextLine();
      CardioMachine machine = new CardioMachine(name);
      cardioList.add(machine);
      // get next int, check to see if -1. If not, add workouts
      t = sc2.nextInt();
      while (t != -1)
      {
        d = sc2.nextLong();
        cardioList.get(i).setTime(t, d);
        t = sc2.nextInt();
      }
      sc2.nextLine(); // to clear line break before reading next machine
      i++; 
    }
    sc2.close();
    System.out.println("Data imported");
  }
}
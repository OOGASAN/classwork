// Tiger version

import java.util.*;
public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>(); 
    private int numOfGuesses = 0; 

   private void setUpGame() {   
      // first make some dot coms and give them locations
      DotCom one = new DotCom();
      one.setName("Pets.com");
      DotCom two = new DotCom();
      two.setName("eToys.com");
      DotCom three = new DotCom();
      three.setName("Go2.com");
      dotComsList.add(one);
      dotComsList.add(two);
      dotComsList.add(three);

      System.out.println("Your goal is to sink three dot coms.");
      System.out.println("Pets.com, eToys.com, Go2.com");
      System.out.println("Try to sink them all in the shortest amount of guesses");
     
       for (DotCom dotComToSet : dotComsList) {   
          ArrayList<String> newLocation = helper.placeDotCom(3);
          //DotCom dotComToSet = (DotCom) dotComsList.get(i);
          dotComToSet.setLocationCells(newLocation);
     
      }
   }


   private void startPlaying() { 
   
     while(!dotComsList.isEmpty()) {
       
        String userGuess = helper.getUserInput("Enter a guess"); 
        checkUserGuess(userGuess);
        
      } // close while
      finishGame();
    } // close startPlaying method


   private void checkUserGuess(String userGuess) {
      numOfGuesses++;
      String result  = "miss"; // assume a miss until told otherwise

      for (DotCom dotComToTest : dotComsList) {

         //DotCom dotComToTest = (DotCom) dotComsList.get(i);
         result = dotComToTest.checkYourself(userGuess);           
          
         if (result.equals("hit")) {
               
               break;
         }
        if (result.equals("kill")) {
               
               dotComsList.remove(dotComToTest); // he's gone
               break;
         }  

       } // close for

      System.out.println(result);
   }
 
 

  private void finishGame() {
     System.out.println("All Dot Coms are dead! Your stock is now worthless");
     if (numOfGuesses <= 9) {
        System.out.println("It only took you " + numOfGuesses + " guesses.  You get the Enron award!");
     } else {
        System.out.println("Took you long enough. "+ numOfGuesses + " guesses.");
        System.out.println("Too bad you didn't get out before your options sank.");
    }
 }
   
  
    public static void main (String[] args) {
      DotComBust game = new DotComBust();
      game.setUpGame();
      game.startPlaying();
    }
}
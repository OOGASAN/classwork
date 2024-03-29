// chapter 13

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox {

    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", 
       "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap", 
       "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", 
       "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", 
       "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    

    public static void main (String[] args) {
        new BeatBox().buildGUI();
    }
  
    public void buildGUI() {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkboxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);         
          
        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton serializeItBtn = new JButton("serializeIt");
        serializeItBtn.addActionListener(new MySerializeItBtnListener());
        buttonBox.add(serializeItBtn);

        JButton restoreBtn = new JButton("restore");
        restoreBtn.addActionListener(new MyRestoreBtnListener());
        buttonBox.add(restoreBtn);

        JButton scrambleBtn = new JButton("scramble");
        scrambleBtn.addActionListener(new MyScrambleBtnListener());
        buttonBox.add(scrambleBtn);        

        JButton clearBtn = new JButton("clear");
        clearBtn.addActionListener(new MyClearBtnListener());
        buttonBox.add(clearBtn);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
           nameBox.add(new Label(instrumentNames[i]));
        }
        
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);
          
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {                    
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);            
        } // end loop

        setUpMidi();

        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
    } // close method


    public void setUpMidi() {
      try {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequence = new Sequence(Sequence.PPQ,4);
        track = sequence.createTrack();
        sequencer.setTempoInBPM(120);
        
      } catch(Exception e) {e.printStackTrace();}
    } // close method

    public void buildTrackAndStart() {
      int[] trackList = null;
    
      sequence.deleteTrack(track);
      track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
          trackList = new int[16];
 
          int key = instruments[i];   

          for (int j = 0; j < 16; j++ ) {         
              JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
              if ( jc.isSelected()) {
                 trackList[j] = key;
              } else {
                 trackList[j] = 0;
              }                    
           } // close inner loop
         
           makeTracks(trackList);
           track.add(makeEvent(176,1,127,0,16));  
       } // close outer

       track.add(makeEvent(192,9,1,0,15));      
       try {
           sequencer.setSequence(sequence); 
	     sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);                   
           sequencer.start();
           sequencer.setTempoInBPM(120);
       } catch(Exception e) {e.printStackTrace();}
    } // close buildTrackAndStart method
           
    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
        }
    } // close inner class

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.stop();
        }
    } // close inner class

    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
	     float tempoFactor = sequencer.getTempoFactor(); 
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
     } // close inner class

     public class MyDownTempoListener implements ActionListener {
         public void actionPerformed(ActionEvent a) {
	      float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * .97));
        }
    } // close inner class

    public class MySerializeItBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            
            boolean[] checkboxState = new boolean[256];

            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);
//                JCheckBox check = checkboxList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            }
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Checkbox.ser"));
                os.writeObject(checkboxState);
                os.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class MyRestoreBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent a ) {
            boolean[] checkboxState = new boolean[256];
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream("Checkbox.ser"));
                checkboxState = (boolean[]) is.readObject();
            } catch (Exception ex){ ex.printStackTrace();}

            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if (checkboxState[i]) {
                    check.setSelected(true);
                } else {
                    check.setSelected(false);
                }
            }

            sequencer.stop();
            buildTrackAndStart();
        }
    }

    public void clear() {
        sequencer.stop();
        buildGUI();
    }

    public class MyClearBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            clear();
        }
    }

    // not actually a getter!
    private boolean[][] getTrackStates() {
        boolean[][] array = new boolean[16][16];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                JCheckBox check = (JCheckBox) checkboxList.get(k);
                if (check.isSelected()) {
                    array[i][j] = true;
                } else {
                    array[i][j] = false;
                }
                k++;
            }
        }

        return array;
    }

    private void setupTrackStates(boolean array[][]) {
        int k = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                JCheckBox check = (JCheckBox) checkboxList.get(k);
                if (array[i][j]) {
                    check.setSelected(true);
                } else {
                    check.setSelected(false);
                }
                k++;
            }
        }     
    }

    public class MyScrambleBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            boolean[][] trackStates;
            trackStates = getTrackStates();
            clear();

            // scramble function here
            // for each track
            for (int i = 0; i < 16; i++) {
                int numChecks = 0;
                // get count of number of beats in a track and clear track
                for (int j = 0; j < 16; j++) {
                    if (trackStates[i][j]) {
                        numChecks++;
                        trackStates[i][j] = false;
                    }
                }
//                System.out.println("Init Num Checks: Track " + i + " " + numChecks);
                // for each beat in track
                // generate [numChecks] random numbers between 0 and 15
                ArrayList<Integer> newBeats = new ArrayList<Integer>();
                for (int x = 0; x < numChecks; x++) {
//                    System.out.println("Times thru for loop:  " + x);
                    int randNum = (int) (Math.random() * 15);
//                    System.out.println("random num: " + randNum);
                    // if this value is not already in newBeats - 
//                    System.out.println(newBeats);
                    while (Collections.binarySearch(newBeats, randNum) >= 0) {
                        randNum = (int) (Math.random() * 15);
//                        System.out.println("new random num: " + randNum);
                    }
                    newBeats.add(randNum);
                    trackStates[i][randNum] = true;
//                    System.out.println("Checking track " + i + " beat " + randNum);
                }
            }

            // set checkboxes
            setupTrackStates(trackStates);     
            
            sequencer.stop();
            buildTrackAndStart();
        }
    }

    public void makeTracks(int[] list) {        
       
       for (int i = 0; i < 16; i++) {
          int key = list[i];

          if (key != 0) {
             track.add(makeEvent(144,9,key, 100, i));
             track.add(makeEvent(128,9,key, 100, i+1));
          }
       }
    }
        
    public  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);

        } catch(Exception e) {e.printStackTrace(); }
        return event;
    }

} // close class

        
             
          
          
          

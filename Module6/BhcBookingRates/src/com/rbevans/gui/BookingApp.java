package com.rbevans.gui;

import com.rbevans.bookingrate.Booking;
import com.rbevans.bookingrate.BookingDay;
import com.rbevans.bookingrate.Booking.HIKE;
import com.rbevans.bookingrate.BookingIO;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Calendar;


public class BookingApp {

    private static BookingIO bookingIO = new BookingIO();

	private static Booking bookingModel = new Booking();
	
	private static JComboBox<Booking.HIKE> hikeCombo;
	
	private static JComboBox<Integer> durationCombo;
	
	private static JDatePicker datePicker;

    private static JLabel resultLabel;

	public BookingApp() { }
	
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BHC Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextArea instructions = new JTextArea("Welcome to Beartooth Hiking Company!\nTo book a trip, please select a hike, trip duration, and start date.\nThe BHC season runs from June 1st to October 1st.");
        instructions.setEditable(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        centerPanel.setLayout(new GridLayout(3,2, 5, 5));
 
        //Create hike selection
        JLabel hikeLabel = new JLabel("Select a hike:");
        hikeLabel.setHorizontalAlignment(JLabel.RIGHT);

        DefaultComboBoxModel<Booking.HIKE> hikeModel = new DefaultComboBoxModel<>(Booking.HIKE.values());
        hikeCombo = new JComboBox<>(hikeModel);
        hikeCombo.addItemListener( e -> handleHikeSelection(e));
        hikeCombo.setSelectedIndex(-1);
        
        //Create duration selection
        JLabel durationLabel = new JLabel("Hike duration (days):");
        durationLabel.setHorizontalAlignment(JLabel.RIGHT);

        durationCombo = new JComboBox<>();
        durationCombo.addItemListener(e -> handleDurationSelection(e));
        
        //Create date components
        JLabel startLabel = new JLabel("Start date:");
        startLabel.setHorizontalAlignment(JLabel.RIGHT);

        datePicker = new JDatePicker();

        //Add components to gridLayout
        centerPanel.add(hikeLabel);
        centerPanel.add(hikeCombo);

        centerPanel.add(durationLabel);
        centerPanel.add(durationCombo);

        centerPanel.add(startLabel);
        centerPanel.add(datePicker);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleSubmit(e));
        submitPanel.add(submitButton);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resultLabel = new JLabel(" ");
        resultPanel.add(resultLabel);

        mainPanel.add(instructions);
        mainPanel.add(centerPanel);
        mainPanel.add(submitPanel);
        mainPanel.add(resultPanel);

        frame.getContentPane().add(mainPanel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Handles the hike selection by instantiating the rate calculator and 
     * filling the durationCombo combo box with valid durations
     * @param e - ActionEvent
     */
    public static void handleHikeSelection(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED && hikeCombo.getSelectedIndex() != -1) {
            bookingModel.setHike((HIKE) hikeCombo.getSelectedItem());
            setDurationCombo(bookingModel.getDurations());
        }
    }
    
    public static void handleDurationSelection(ItemEvent e) {
    	if(e.getStateChange() == ItemEvent.SELECTED && durationCombo.getSelectedItem() != null) {
    		int duration = (Integer)durationCombo.getSelectedItem();
	    	bookingModel.setDuration(duration);
    	}
    }
    
    public static void handleStartDateSelection() {
    	DateModel<Calendar> model = (DateModel<Calendar>)datePicker.getModel();
    	BookingDay day = new BookingDay(model.getYear(), model.getMonth() + 1, model.getDay());
    	bookingModel.setBeginDate(day);
    }

    public static void handleSubmit(ActionEvent e){
        handleStartDateSelection(); //get selected start date

        String request = bookingModel.getRequestString();
        double cost = -0.01;
        String details = "Error forming request";

        if(request != null) {
            String result = bookingIO.sendRequest(request);
            if(result != null) {
                cost = bookingIO.getCost(result);
                details = bookingIO.getDetails(result);
            }
            else {
                details = "No response from server";
            }
        }

        if (cost >= 0) {
            resultLabel.setForeground(Color.BLACK);
            resultLabel.setText("Cost: $" + cost);
        } else {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Error: " + details);
        }
    }
    
    /**
     * Sets the duration options in the durationCombo combo box
     * @param items - the valid durations for the selected hike
     */
    private static void setDurationCombo(int[] items) {
    	durationCombo.removeAllItems();
    	
    	for(int i: items) {
    		durationCombo.addItem(i);
    	}
    	durationCombo.setSelectedIndex(0);
    }
 
    public static void main(String[] args) {
		setUIFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,18));

        //Schedule a job for the event-dispatching thread:
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }

}    


package com.rbevans.gui;

import com.rbevans.bookingrate.BookingDay;
import com.rbevans.bookingrate.Rates;
import com.rbevans.bookingrate.Rates.HIKE;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.constraints.RangeConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class BookingApp {
	
	private static Rates rateCalculator = new Rates();
	
	private static JComboBox<Rates.HIKE> hikeCombo;
	
	private static JComboBox<Integer> durationCombo;
	
	private static JDatePicker datePicker;
	
	private static JLabel endDateDisplay;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

    private static JLabel resultLabel;

	public BookingApp() {
		Font newFont = new Font("Arial", Font.PLAIN, 26);
		
		UIManager.getLookAndFeelDefaults().put(
    			"defaultFont", newFont);
		
		UIManager.getLookAndFeelDefaults().put("ComboBox.font", newFont);
		UIManager.getLookAndFeelDefaults().put("Label.font",newFont);
	}
	
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
        centerPanel.setLayout(new GridLayout(4,2, 5, 5));
 
        //Create hike selection
        JLabel hikeLabel = new JLabel("Select a hike:");
        hikeLabel.setHorizontalAlignment(JLabel.RIGHT);

        DefaultComboBoxModel<Rates.HIKE> hikeModel = new DefaultComboBoxModel<>(Rates.HIKE.values());
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
        datePicker.addActionListener(e -> handleStartDateSelection(e));
        datePicker.setEnabled(false);
        
        Calendar minDate = new GregorianCalendar();
        minDate.set(2007, 1, 1);
        
        Calendar maxDate = new GregorianCalendar();
        maxDate.set(2020,12,31);
        
        RangeConstraint constraint = new RangeConstraint(minDate, maxDate);
        datePicker.addDateSelectionConstraint(constraint);

        JLabel endLabel = new JLabel("End date:");
        endLabel.setHorizontalAlignment(JLabel.RIGHT);

        endDateDisplay = new JLabel("(select start time and duration)");

        //Add components to gridLayout
        centerPanel.add(hikeLabel);
        centerPanel.add(hikeCombo);

        centerPanel.add(durationLabel);
        centerPanel.add(durationCombo);

        centerPanel.add(startLabel);
        centerPanel.add(datePicker);

        centerPanel.add(endLabel);
        centerPanel.add(endDateDisplay);

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
            rateCalculator.setHike((HIKE) hikeCombo.getSelectedItem());
            setDurationCombo(rateCalculator.getDurations());
            setEndDate();
        }
    }
    
    public static void handleDurationSelection(ItemEvent e) {
    	if(e.getStateChange() == ItemEvent.SELECTED && durationCombo.getSelectedItem() != null) {
    		int duration = (Integer)durationCombo.getSelectedItem();
	    	rateCalculator.setDuration(duration);
	    	setEndDate();
            datePicker.setEnabled(true);
    	}
    }
    
    public static void handleStartDateSelection(ActionEvent e) {
    	DateModel<Calendar> model = (DateModel<Calendar>)datePicker.getModel();
    	BookingDay day = new BookingDay(model.getYear(), model.getMonth(), model.getDay());
    	if(day.isValidDate()) {
    		rateCalculator.setBeginDate(day);
    		setEndDate();
    	}
    }

    public static void handleSubmit(ActionEvent e){
        if(rateCalculator.isValidDates()){
            resultLabel.setForeground(Color.BLACK);
            resultLabel.setText("Cost: $" + rateCalculator.getCost());
        }
        else {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Error: " + rateCalculator.getDetails());
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
    
    /**
     * Calculates end date based on start date and duration, adds to rateCalculator
     */
    private static void setEndDate() {
    	rateCalculator.calculateEndDate();
    	if(rateCalculator.getEndDate() != null) {
    		endDateDisplay.setText(dateFormat.format(rateCalculator.getEndDate().getTime()));
    	}
    }
 
    public static void main(String[] args) {
    	Font newFont = new Font("Arial", Font.PLAIN, 20);
		
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


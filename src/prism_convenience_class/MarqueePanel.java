package prism_convenience_class;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MarqueePanel extends JPanel {
	final Rotator_Maequee rotator;
	private JLabel label = new JLabel();
	private boolean status;

	public MarqueePanel() {
		this.add(label);
		setBorder(null);
		rotator = new Rotator_Maequee(this);
		start();
	}

	public JLabel get_lable() {
		return label;
	}

	public void start() {
		if (!rotator.isRunning()) {
			rotator.start();
			label.setVisible(true);
			revalidate();
			repaint();
			status = true;
		}
	}

	public void stop() {
		if (rotator.isRunning()) {
			rotator.stop();
			label.setVisible(false);
			revalidate();
			repaint();
			status = false;
		}
	}

	public boolean is_text_running() {
		return status;
	}
	
	
	private class Rotator_Maequee extends Timer implements ActionListener {
		private JLabel label;
		private String s = "";		// remember that s is not allow to be null any time
		private int n = 250;		// the length of text in the JLabel, if less --> fill with space. Remember that n is not allow to be <= 1 any time
		private int index;
		private List<String> maequee_list;
		
		private ScheduledExecutorService executor;
		private Runnable task;

		Rotator_Maequee(final MarqueePanel panel) {
			super(0, null);
			
			this.label = panel.get_lable();
			File file_maequee = FilesHandle.get_file_maequee();
			try {
				maequee_list = Files.readAllLines(Paths.get(file_maequee.getAbsolutePath()), StandardCharsets.UTF_8);	// All lines to be in a string list
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
			
			task =  new Runnable() {
                public void run() {
                	index++;
        			if (index > s.length() - n) {
        				index = 0;
        				
        				int random_number = new Random().nextInt(maequee_list.size());
        				s = maequee_list.get(random_number);	// reset with a new line, remember that s is not allow to be null any time 	
        				if (s == null || n < 1) {
        					throw new IllegalArgumentException("Null string or n < 1");
        				}
        				
        				StringBuilder sb = new StringBuilder(n);
        				for (int i = 0; i < n; i++) {
        					sb.append(' ');
        				}
        				s = sb + s + sb;
        				label.setText(sb.toString());
        			}
        			
        			if (index == n - 1 && executor != null) {
        				 executor.shutdown(); // shutdown will allow the final iteration to finish executing where shutdownNow() will kill it immediately
        			}
        			
        			label.setText(s.substring(index, index + n));
                }
            };
			
			
			setInitialDelay(0);		// no delay the first time the text appeared
			setDelay(30000);		// after that, renew the sentence text every 30 seconds
			addActionListener(this);
		}
		
		public void actionPerformed(final ActionEvent event) {
			if (executor != null) executor.shutdown();
		    int initialDelay = 0;
		    int period = 1;	// change this number would make the text run slower or faster
		    executor = Executors.newScheduledThreadPool(1);
		    executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
		}
	}
}












//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES



//package prism_convenience_class;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Random;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//
//public class MarqueePanel extends JPanel {
//	final Rotator_Maequee rotator;
//	private JLabel label = new JLabel();
//	private boolean status;
//
//	public MarqueePanel() {
//		this.add(label);
//		setBorder(null);
//		rotator = new Rotator_Maequee(this);
//		start();
//	}
//
//	public JLabel get_lable() {
//		return label;
//	}
//
//	public void start() {
//		if (!rotator.isRunning()) {
//			rotator.start();
//			label.setVisible(true);
//			revalidate();
//			repaint();
//			status = true;
//		}
//	}
//
//	public void stop() {
//		if (rotator.isRunning()) {
//			rotator.stop();
//			label.setVisible(false);
//			revalidate();
//			repaint();
//			status = false;
//		}
//	}
//
//	public boolean is_text_running() {
//		return status;
//	}
//	
//	
//	private class Rotator_Maequee extends Timer implements ActionListener {
//		private JLabel label;
//		private String s = "";		// remember that s is not allow to be null any time
//		private int n = 250;		// the length of text in the JLabel, if less --> fill with space. Remember that n is not allow to be <= 1 any time
//		private int index;
//		private List<String> maequee_list;
//
//		Rotator_Maequee(final MarqueePanel panel) {
//			super(1, null);
//			this.label = panel.get_lable();
//			
//			File file_maequee = FilesHandle.get_file_maequee();
//			try {
//				maequee_list = Files.readAllLines(Paths.get(file_maequee.getAbsolutePath()), StandardCharsets.UTF_8);	// All lines to be in a string list
//			} catch (IOException e) {
//				System.err.println(e.getClass().getName() + ": " + e.getMessage());
//			}
//			
//			addActionListener(this);
//		}
//		
//		public void actionPerformed(final ActionEvent event) {
//			index++;
//			if (index > s.length() - n) {
//				index = 0;
//				
//				int random_number = new Random().nextInt(maequee_list.size());
//				s = maequee_list.get(random_number);	// reset with a new line, remember that s is not allow to be null any time 	
//				if (s == null || n < 1) {
//					throw new IllegalArgumentException("Null string or n < 1");
//				}
//				
//				StringBuilder sb = new StringBuilder(n);
//				for (int i = 0; i < n; i++) {
//					sb.append(' ');
//				}
//				s = sb + s + sb;
//				label.setText(sb.toString());
//			}
//			
//			if (index == n - 1) {
//				setDelay(30000);
//			} else {
//				if (getDelay() != 1) setDelay(1);
//			}
//			
//			label.setText(s.substring(index, index + n));
//		}
//	}
//}














//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES
//WORKING SOLUTION BUT VERY SLOW SOMETIMES



//package prism_convenience_class;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Random;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//public class MarqueePanel extends JPanel implements ActionListener {
//	private Timer timer = new Timer(1, this);
//	private JLabel label = new JLabel();
//	private String s;
//	private int n;
//	private int index;
//	private List<String> maequee_list;
//	private boolean status;
//
//	public MarqueePanel(int n) {
//		File file_maequee = FilesHandle.get_file_maequee();
//		try {
//			maequee_list = Files.readAllLines(Paths.get(file_maequee.getAbsolutePath()), StandardCharsets.UTF_8);	// All lines to be in a string list
//		} catch (IOException e) {
//			System.err.println(e.getClass().getName() + ": " + e.getMessage());
//		}
//
//		// Initialize the first text
//		this.n = n;
//		int random_number = new Random().nextInt(maequee_list.size());
//		s = maequee_list.get(random_number);
//		if (s == null || n < 1) {
//			throw new IllegalArgumentException("Null string or n < 1");
//		}
//		StringBuilder sb = new StringBuilder(n);
//		for (int i = 0; i < n; i++) {
//			sb.append(' ');
//		}
//		s = sb + s + sb;
//		label.setText(sb.toString());
//		
//		this.add(label);
//		setBorder(null);
//	}
//
//	public void start() {
//		timer.start();
//		label.setVisible(true);
//		revalidate();
//		repaint();
//		status = true;
//	}
//
//	public void stop() {
//		timer.stop();
//		label.setVisible(false);
//		revalidate();
//		repaint();
//		status = false;
//	}
//	
//	public boolean is_text_running() {
//		return status;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		index++;
//		if (index > s.length() - n) {
//			index = 0;
//			
//			// reset with a new line
//			int random_number = new Random().nextInt(maequee_list.size());
//			s = maequee_list.get(random_number);
//			if (s == null || n < 1) {
//				throw new IllegalArgumentException("Null string or n < 1");
//			}
//			StringBuilder sb = new StringBuilder(n);
//			for (int i = 0; i < n; i++) {
//				sb.append(' ');
//			}
//			s = sb + s + sb;
//			label.setText(sb.toString());
//		}
//		
//		if (index == n - 1) {
//			timer.setDelay(30000);
//		} else {
//			if (timer.getDelay() != 1) timer.setDelay(1);
//		}
//		label.setText(s.substring(index, index + n));
//	}
//}

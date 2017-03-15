package fileReNamer;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class frameReNamer {

	private JFrame frame;
	private JTextField fileNameTextField;
	private JTextField paddingTextField;
	private JTextField extTextField;
	private JTextField startInputTextField;
	private JTextField endInputTextField;
	private JTextField startOutputTextField;
	private JTextField fileLocationTextField;
	private JLabel lblFileLocation;
	private JLabel lblExCtmpfilepng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameReNamer window = new frameReNamer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int currentNumCalc(int currentNum)
	{
		int count = 0;
		while (currentNum >= 1)
		{
			
			count++;
			currentNum = currentNum / 10;
		}
		
		return count;
		
	}
	
	public String paddingTextCalcFunction(int padding, int currentNum)
	{
		String paddingTextCalc = "";
		currentNum = currentNumCalc(currentNum);
		for (int i = currentNum; i < padding; i++)
		{
			
			paddingTextCalc = (paddingTextCalc + "0");
			
		}
		return paddingTextCalc;
	}

	/**
	 * Create the application.
	 */
	public frameReNamer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Text Re-Namer");
		
		JLabel lblFileName = new JLabel("File Name:");
		lblFileName.setBounds(6, 112, 73, 16);
		frame.getContentPane().add(lblFileName);
		
		fileNameTextField = new JTextField();
		fileNameTextField.setText("file");
		fileNameTextField.setBounds(91, 106, 134, 28);
		frame.getContentPane().add(fileNameTextField);
		fileNameTextField.setColumns(10);
		
		paddingTextField = new JTextField();
		paddingTextField.setText("3");
		paddingTextField.setBounds(91, 146, 134, 28);
		frame.getContentPane().add(paddingTextField);
		paddingTextField.setColumns(10);
		
		JLabel lblPadding = new JLabel("Padding:");
		lblPadding.setBounds(6, 152, 61, 16);
		frame.getContentPane().add(lblPadding);
		
		extTextField = new JTextField();
		extTextField.setText(".png");
		extTextField.setBounds(91, 186, 134, 28);
		frame.getContentPane().add(extTextField);
		extTextField.setColumns(10);
		
		JLabel lblExt = new JLabel("Ext.:");
		lblExt.setBounds(6, 192, 61, 16);
		frame.getContentPane().add(lblExt);
		
		JLabel lblStartInput = new JLabel("Start input:");
		lblStartInput.setBounds(251, 112, 78, 16);
		frame.getContentPane().add(lblStartInput);
		
		startInputTextField = new JTextField();
		startInputTextField.setText("1");
		startInputTextField.setBounds(341, 106, 134, 28);
		frame.getContentPane().add(startInputTextField);
		startInputTextField.setColumns(10);
		
		JLabel lblEndInput = new JLabel("End input:");
		lblEndInput.setBounds(251, 152, 78, 16);
		frame.getContentPane().add(lblEndInput);
		
		endInputTextField = new JTextField();
		endInputTextField.setText("12");
		endInputTextField.setBounds(341, 146, 134, 28);
		frame.getContentPane().add(endInputTextField);
		endInputTextField.setColumns(10);
		
		JLabel lblStartOutput = new JLabel("Start Output:");
		lblStartOutput.setBounds(251, 192, 90, 16);
		frame.getContentPane().add(lblStartOutput);
		
		startOutputTextField = new JTextField();
		startOutputTextField.setText("1");
		startOutputTextField.setBounds(341, 186, 134, 28);
		frame.getContentPane().add(startOutputTextField);
		startOutputTextField.setColumns(10);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean fail = false;
				
				//Test vars
				String fileLocation = fileLocationTextField.getText();
				String fileName = fileNameTextField.getText();
				String paddingText = paddingTextField.getText();
				int padding = 0;
				try{
					padding = Integer.parseInt(paddingText);   
				}
				catch(NumberFormatException ex){
					lblExCtmpfilepng.setText("Ilegal padding input");
					fail = true;
				}  
				String fileExt = extTextField.getText();
				String startInputText = startInputTextField.getText();
				int startInput = 0;
				try{
					startInput = Integer.parseInt(startInputText);   
				}
				catch(NumberFormatException ex){
					lblExCtmpfilepng.setText("Ilegal Starting input input");
					fail = true;
				}  
				String endInputText = endInputTextField.getText();
				int endInput = 0;
				try{
					endInput = Integer.parseInt(endInputText);   
				}
				catch(NumberFormatException ex){
					lblExCtmpfilepng.setText("Ilegal Ending input input");
					fail = true;
				}  
				String startOutputText = startOutputTextField.getText();
				int startOutput = 0;
				try{
					startOutput = Integer.parseInt(startOutputText);   
				}
				catch(NumberFormatException ex){
					lblExCtmpfilepng.setText("Ilegal Starting output input");
					fail = true;
				}  
				
				//output file name and path
				//System.out.println(fileLocation + fileName + padding + startInput + endInput + startOutput + fileExt + fail);				
				
				if (fail == true)
				{
					//output error
					
				}
				else
				{
					
					//continue with program
					//check if file is there

					//convert padding
					
					String paddingTextCalc = paddingTextCalcFunction(padding, 1);
					String paddingTextCalc2 = "";
					//paddintTextCalc();
					
						File f = null;
						f = new File(fileLocation + fileName + paddingTextCalc + startInput + fileExt);
						if (f.exists() && !f.isDirectory())
						{
							
							//Continue with program
							//set up counting vars
							int outputNum = startOutput;
							
								for (int i = 1; i < endInput + 1; i++)
								{
									
									paddingTextCalc = paddingTextCalcFunction(padding, i);
									f = null;
									f = new File(fileLocation + fileName + paddingTextCalc + i + fileExt);
									if (f.exists() && !f.isDirectory())
									{
										paddingTextCalc2 = paddingTextCalcFunction(padding, outputNum);
										f = null;
										f = new File(fileLocation + fileName + paddingTextCalc + outputNum + fileExt);
										if (f.exists() && !f.isDirectory())
										{
												
											lblExCtmpfilepng.setText("Current File" + i + "/" + endInput);
											
											outputNum++;
											
											//System.out.println("Both files exist");
										
										}
										else
										{
											
											//rename file
											//set locations of both files
											File file1 = new File(fileLocation + fileName + paddingTextCalc + i + fileExt);
											File file2 = new File(fileLocation + fileName + paddingTextCalc2 + outputNum + fileExt);				
											
											//rename files
											
											file1.renameTo(file2);
											//System.out.println("file renamed");
											
											lblExCtmpfilepng.setText("Current File" + i + "/" + endInput);
											//System.out.println(i + "--> " + (outputNum));
											outputNum++;
											
										}
									
									}	
								}
								lblExCtmpfilepng.setText("Ex:  /tmp/file001.png  to file012.png");
												
						}
						else
						{
							
							lblExCtmpfilepng.setText("Could not find file and or location of file");
							
						}
				}
				//Check file location and name to see if there is a file there that matches
			}
		});
		btnConvert.setBounds(190, 226, 90, 29);
		frame.getContentPane().add(btnConvert);
		
		fileLocationTextField = new JTextField();
		fileLocationTextField.setText("/tmp/");
		fileLocationTextField.setBounds(91, 66, 384, 28);
		frame.getContentPane().add(fileLocationTextField);
		fileLocationTextField.setColumns(10);
		
		lblFileLocation = new JLabel("File Location:");
		lblFileLocation.setBounds(6, 72, 90, 16);
		frame.getContentPane().add(lblFileLocation);
		
		lblExCtmpfilepng = new JLabel("Ex:  /tmp/file001.png  to file012.png");
		lblExCtmpfilepng.setBounds(91, 27, 270, 16);
		frame.getContentPane().add(lblExCtmpfilepng);
		
		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		JMenu helpMain = new JMenu("Help");
		
		menuBar.add(file);
		menuBar.add(helpMain);
		
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenuItem about = new JMenuItem("About");
		//JMenuItem help = new JMenuItem("Help");
		
		file.add(exit);
		helpMain.add(about);
		//helpMain.add(help);
		
		class exitAction implements ActionListener{
			
			public void actionPerformed (ActionEvent e){
				
				System.exit(0);
				
			}
		}
		
		exit.addActionListener(new exitAction());
		
		class aboutAction implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				
				JOptionPane.showMessageDialog(frame,"File Re-namer V1.0 \nProgram by Matt Reishman \nLast update: 3/29/2015");				
			}			
		}
		about.addActionListener(new aboutAction());
	}
}

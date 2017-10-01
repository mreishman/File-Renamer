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
	private int modeValue = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					frameReNamer window = new frameReNamer();
					window.frame.setVisible(true);
				}
				catch (Exception e) 
				{
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
	public frameReNamer() 
	{
		initialize();
	}
	
	public void buttonConvertDirAction(ActionEvent e)
	{
		
	}
	
	public void removeFolderFromIgnore(ActionEvent e)
	{
		
	}
	
	public void addFolderToIgnore(ActionEvent e)
	{
		
	}
	
	public void buttonConvertAction(ActionEvent e) 
	{
		
		boolean fail = false;
		
		//Test vars
		String fileLocation = fileLocationTextField.getText();
		String fileName = fileNameTextField.getText();
		String paddingText = paddingTextField.getText();
		String fileExt = extTextField.getText();
		String startInputText = startInputTextField.getText();
		String endInputText = endInputTextField.getText();
		String startOutputText = startOutputTextField.getText();
		int padding = 0;
		int startInput = 0;
		int endInput = 0;
		int startOutput = 0;
		
		
		try
		{
			padding = Integer.parseInt(paddingText);   
		}
		catch(NumberFormatException ex)
		{
			lblExCtmpfilepng.setText("Ilegal padding input");
			fail = true;
		}
		try
		{
			startInput = Integer.parseInt(startInputText);   
		}
		catch(NumberFormatException ex)
		{
			lblExCtmpfilepng.setText("Ilegal Starting input input");
			fail = true;
		}
		try
		{
			endInput = Integer.parseInt(endInputText);   
		}
		catch(NumberFormatException ex)
		{
			lblExCtmpfilepng.setText("Ilegal Ending input input");
			fail = true;
		}
		try
		{
			startOutput = Integer.parseInt(startOutputText);   
		}
		catch(NumberFormatException ex)
		{
			lblExCtmpfilepng.setText("Ilegal Starting output input");
			fail = true;
		}  
		
		//output file name and path
		//System.out.println(fileLocation + fileName + padding + startInput + endInput + startOutput + fileExt + fail);				
		
		if (!fail)
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
	
	public void createSceneOne(JFrame frame)
	{		
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
		btnConvert.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				buttonConvertAction(e) ;
				
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
		lblExCtmpfilepng.setBounds(6, 27, 450, 16);
		frame.getContentPane().add(lblExCtmpfilepng);
	}
	
	public void createSceneTwo(JFrame frame)
	{
		fileLocationTextField = new JTextField();
		fileLocationTextField.setText("/tmp/");
		fileLocationTextField.setBounds(120, 66, 350, 28);
		frame.getContentPane().add(fileLocationTextField);
		fileLocationTextField.setColumns(10);
		
		lblFileLocation = new JLabel("Folder Location:");
		lblFileLocation.setBounds(6, 72, 120, 16);
		frame.getContentPane().add(lblFileLocation);
		
		lblExCtmpfilepng = new JLabel("Ex:  /tmp/main/file001.png  to /output/main_file001.png");
		lblExCtmpfilepng.setBounds(6, 27, 450, 16);
		frame.getContentPane().add(lblExCtmpfilepng);
		
		JLabel lblFileName = new JLabel("Dir. Indicator:");
		lblFileName.setBounds(6, 112, 120, 16);
		frame.getContentPane().add(lblFileName);
		
		fileNameTextField = new JTextField();
		fileNameTextField.setText("/");
		fileNameTextField.setBounds(120, 106, 134, 28);
		frame.getContentPane().add(fileNameTextField);
		fileNameTextField.setColumns(10);
		
		
		paddingTextField = new JTextField();
		paddingTextField.setText("_");
		paddingTextField.setBounds(120, 146, 134, 28);
		frame.getContentPane().add(paddingTextField);
		paddingTextField.setColumns(10);
		
		JLabel lblPadding = new JLabel("Dir Replace Char.");
		lblPadding.setBounds(6, 152, 120, 16);
		frame.getContentPane().add(lblPadding);
		
		
		
		extTextField = new JTextField();
		extTextField.setText(".git");
		extTextField.setBounds(120, 186, 134, 28);
		frame.getContentPane().add(extTextField);
		extTextField.setColumns(10);
		
		JLabel lblExt = new JLabel("Folder ignore:");
		lblExt.setBounds(6, 192, 120, 16);
		frame.getContentPane().add(lblExt);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				addFolderToIgnore(e) ;
				
			}
		});
		btnAdd.setBounds(251, 186, 90, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				removeFolderFromIgnore(e) ;
				
			}
		});
		btnRemove.setBounds(335, 186, 90, 29);
		frame.getContentPane().add(btnRemove);
		
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				buttonConvertDirAction(e) ;
				
			}
		});
		btnConvert.setBounds(190, 226, 90, 29);
		frame.getContentPane().add(btnConvert);
		
	}
	
	public void clearScreen(JFrame frame)
	{
		frame.getContentPane().removeAll();
		frame.repaint();
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
		
		final JPanel panel = new JPanel();
		frame.add(panel);
		
		createSceneOne(frame);
		
		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		JMenu helpMain = new JMenu("Help");
		JMenu modeMain = new JMenu("Mode");
		
		menuBar.add(file);
		menuBar.add(helpMain);
		menuBar.add(modeMain);
		
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenuItem about = new JMenuItem("About");
		//JMenuItem help = new JMenuItem("Help");
		
		JMenuItem mode = new JMenuItem("Switch Mode");
		
		file.add(exit);
		helpMain.add(about);
		//helpMain.add(help);
		modeMain.add(mode);
		
		class exitAction implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0);
			}
		}
		exit.addActionListener(new exitAction());
		
		class aboutAction implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(frame,"File Re-namer V2.0 \nProgram by Matt Reishman \nLast update: 9/30/2017");				
			}			
		}
		about.addActionListener(new aboutAction());
		
		class modeSwitch implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//clear screen
				clearScreen(frame);
				
				if(modeValue == 1)
				{
					createSceneTwo(frame);
					modeValue = 2;
				}
				else
				{
					createSceneOne(frame);
					modeValue = 1;
				}
			}
		}
		
		mode.addActionListener(new modeSwitch());
	}
}
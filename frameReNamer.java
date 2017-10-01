package fileReNamer;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class frameReNamer {

	private JFrame frame;
	private JTextField fileNameTextField;
	private JTextField directoryIndicatorTextField;
	private JTextField folderLocationTextField;
	private JTextField dirReplaceCharTextField;
	private JTextField paddingTextField;
	private JTextField extTextField;
	private JTextField ignoreFolderTextField;
	private JTextField ignoreFileTextField;
	private JTextField startInputTextField;
	private JTextField endInputTextField;
	private JTextField startOutputTextField;
	private JTextField fileLocationTextField;
	private JTextField folderOutputTextField;
	private JLabel lblExCtmpfilepng;
	private int modeValue = 1;
	private ArrayList<String> ignoreFiles = new ArrayList<String>();
	private ArrayList<String> ignoreFolders = new ArrayList<String>();
	
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
	
	public void copyFilesInFolder(final File folder) 
	{
		String currentSplit = directoryIndicatorTextField.getText();
		String newSplit = dirReplaceCharTextField.getText();
		String folderInputLocation = folderLocationTextField.getText();
		String folderOutputLocation = folderOutputTextField.getText();
		ArrayList<String> folderInputLocationExplode = new ArrayList<String>(Arrays.asList(folderInputLocation.split(currentSplit)));
		
	    for (final File fileEntry : folder.listFiles()) 
	    {
	        if (fileEntry.isDirectory() && !Arrays.asList(ignoreFolders).contains(fileEntry)) 
	        {
	        	copyFilesInFolder(fileEntry);
	        }
	        else 
	        {
	        	String filename = fileEntry.getName();
	        	String filepath = fileEntry.getPath();
	        	String fileIgnore = ".DS_Store";
	        	if((!Arrays.asList(ignoreFiles).contains(filename)) && (!filename.equals(fileIgnore)))
	        	{
	        		System.out.println(filename);
	        		ArrayList<String> fileExplode = new ArrayList<String>(Arrays.asList(filepath.split(currentSplit)));
	        		for(int i = 0; i < folderInputLocationExplode.size(); i++)
	        		{
	        			fileExplode.remove(0);
	        		}
	        		String newFileName = "";
	        		Boolean first = true;
	        		for(int i = 0; i < fileExplode.size(); i++)
	        		{
	        			if(!first)
	        			{
	        				newFileName += newSplit;
	        			}
	        			else
	        			{
	        				first = false;
	        			}
	        			newFileName += fileExplode.get(i);
	        		}
	        		newFileName = folderOutputLocation + newFileName;
	        		System.out.println(newFileName);
	        	}
	        }
	    }
	}
	
	public void buttonConvertDirAction(ActionEvent e)
	{
		
		//vars
		
		String folderInputLocation = folderLocationTextField.getText();
		String folderOutputLocation = folderOutputTextField.getText();
		
		File folderInput = null;
		folderInput = new File(folderInputLocation);
		
		File folderOutput = null;
		folderOutput = new File(folderOutputLocation);
		
		
		if ((folderInput.exists() && folderInput.isDirectory()) && (folderOutput.exists() && folderOutput.isDirectory()))
		{
			copyFilesInFolder(folderInput);
		}
		else
		{
			if(folderOutput.exists() && folderOutput.isDirectory())
			{
				lblExCtmpfilepng.setText("Could not find output folder");
			}
			else
			{
				lblExCtmpfilepng.setText("Could not find input folder");
			}
			
		}
			
	}
	
	public void removeFolderFromIgnore(ActionEvent e)
	{
		String ignoreFolderString = ignoreFolderTextField.getText();
		
		
		if(Arrays.asList(ignoreFolders).contains(ignoreFolderString))
		{
			ignoreFolders.remove(ignoreFolderString); 
		}
	}
	
	public void addFolderToIgnore(ActionEvent e)
	{
		String ignoreFolderString = ignoreFolderTextField.getText();
		
		ignoreFolders.add(ignoreFolderString);
	}
	
	public void viewFolderInIgnore(ActionEvent e)
	{
		
	}
	
	public void removeFileFromIgnore(ActionEvent e)
	{
		String ignoreFileString = ignoreFileTextField.getText();
		
		
		if(Arrays.asList(ignoreFiles).contains(ignoreFileString))
		{
			ignoreFiles.remove(ignoreFileString); 
		}
	}
	
	public void addFileToIgnore(ActionEvent e)
	{
		String ignoreFileString = ignoreFileTextField.getText();
		
		ignoreFiles.add(ignoreFileString);
	}
	
	public void viewFileInIgnore(ActionEvent e)
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
		
		JLabel lblFileLocation = new JLabel("File Location:");
		lblFileLocation.setBounds(6, 72, 90, 16);
		frame.getContentPane().add(lblFileLocation);
		
		lblExCtmpfilepng = new JLabel("Ex:  /tmp/file001.png  to file012.png");
		lblExCtmpfilepng.setBounds(6, 27, 450, 16);
		frame.getContentPane().add(lblExCtmpfilepng);
	}
	
	public void createSceneTwo(JFrame frame)
	{
		frame.setTitle("File Path Expand Re-Namer");
		folderLocationTextField = new JTextField();
		folderLocationTextField.setText("/tmp/");
		folderLocationTextField.setBounds(120, 66, 350, 28);
		frame.getContentPane().add(folderLocationTextField);
		folderLocationTextField.setColumns(10);
		
		JLabel  lblFolderLocation = new JLabel("Folder Location:");
		lblFolderLocation.setBounds(6, 72, 120, 16);
		frame.getContentPane().add(lblFolderLocation);
		
		lblExCtmpfilepng = new JLabel("Ex:  /tmp/main/file001.png  to /output/main_file001.png");
		lblExCtmpfilepng.setBounds(6, 27, 450, 16);
		frame.getContentPane().add(lblExCtmpfilepng);
		
		JLabel lblFileName = new JLabel("Dir. Indicator:");
		lblFileName.setBounds(6, 112, 120, 16);
		frame.getContentPane().add(lblFileName);
		
		directoryIndicatorTextField = new JTextField();
		directoryIndicatorTextField.setText("/");
		directoryIndicatorTextField.setBounds(120, 106, 134, 28);
		frame.getContentPane().add(directoryIndicatorTextField);
		directoryIndicatorTextField.setColumns(10);
		
		
		dirReplaceCharTextField = new JTextField();
		dirReplaceCharTextField.setText("_");
		dirReplaceCharTextField.setBounds(120, 146, 134, 28);
		frame.getContentPane().add(dirReplaceCharTextField);
		dirReplaceCharTextField.setColumns(10);
		
		JLabel lblPadding = new JLabel("Dir Replace Char.");
		lblPadding.setBounds(6, 152, 120, 16);
		frame.getContentPane().add(lblPadding);
		
		
		
		ignoreFolderTextField = new JTextField();
		ignoreFolderTextField.setText(".git");
		ignoreFolderTextField.setBounds(120, 186, 134, 28);
		frame.getContentPane().add(ignoreFolderTextField);
		ignoreFolderTextField.setColumns(10);
		
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
		btnAdd.setBounds(251, 186, 80, 29);
		frame.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				removeFolderFromIgnore(e) ;
				
			}
		});
		btnRemove.setBounds(322, 186, 80, 29);
		frame.getContentPane().add(btnRemove);
		
		/*
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				removeFolderFromIgnore(e) ;
				
			}
		});
		btnView.setBounds(393, 186, 80, 29);
		frame.getContentPane().add(btnView);
		*/
		
		
		JButton btnAdd2 = new JButton("Add");
		btnAdd2.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				addFolderToIgnore(e) ;
				
			}
		});
		btnAdd2.setBounds(251, 225, 80, 29);
		frame.getContentPane().add(btnAdd2);
		
		JButton btnRemove2 = new JButton("Remove");
		btnRemove2.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				removeFolderFromIgnore(e) ;
				
			}
		});
		btnRemove2.setBounds(322, 225, 80, 29);
		frame.getContentPane().add(btnRemove2);
		
		/*
		JButton btnView2 = new JButton("View");
		btnView2.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				removeFolderFromIgnore(e) ;
				
			}
		});
		btnView2.setBounds(393, 225, 80, 29);
		frame.getContentPane().add(btnView2);
		*/
		ignoreFileTextField = new JTextField();
		ignoreFileTextField.setText("placeholder.txt");
		ignoreFileTextField.setBounds(120, 225, 134, 28);
		frame.getContentPane().add(ignoreFileTextField);
		ignoreFileTextField.setColumns(10);
		
		
		JLabel lblFileIgnore = new JLabel("File ignore:");
		lblFileIgnore.setBounds(6, 230, 120, 16);
		frame.getContentPane().add(lblFileIgnore);
		
		
		
		folderOutputTextField = new JTextField();
		folderOutputTextField.setText("/output/tmp/");
		folderOutputTextField.setBounds(120, 262, 350, 28);
		frame.getContentPane().add(folderOutputTextField);
		folderOutputTextField.setColumns(10);
		
		JLabel  lblOutputLocation = new JLabel("Output Location:");
		lblOutputLocation.setBounds(6, 265, 120, 16);
		frame.getContentPane().add(lblOutputLocation);
		
		
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) {
				buttonConvertDirAction(e) ;
				
			}
		});
		btnConvert.setBounds(190, 300, 90, 29);
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
				JOptionPane.showMessageDialog(frame,"File Re-namer V2.0 \nProgram by Matt Reishman \nLast update: 10/1/2017");				
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
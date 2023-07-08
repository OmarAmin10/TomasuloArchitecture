import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Marwa7a implements ActionListener{
	
	private ArrayList<String> cdb = new ArrayList<String>();
	private String cdbOut = "";
	private String cdbOut1 = "";
	private String[] stArr;
	private ArrayList<Object> regs = new ArrayList<Object>();
	private ArrayList<String> list = new ArrayList<String>();
	private int cycles = 0;
	private int registers = 0;
	private int instructions = 0;
	private int loadLatency = 2;
	private int addLatency = 2;
	private int storeLatency = 1;
	private int subtractLatency = 2;
	private int multiplyLatency = 10;
	private int divideLatency = 40;
	private File output;
	private FileWriter writer;
	private String r = "";
	private String rr = "";
	private JFileChooser fc;
	private JLabel label;
	private JLabel labelOR;
	private JLabel result;
	private JLabel cdbLabel;
	private JLabel error;
	private JFrame frame;
	private JFrame simFrame;
	private JFrame cdbFrame;
	private JPanel panelT;
	private JPanel panelB;
	private JPanel panelR;
	private JTextField textField;
	private JTextField ad;
	private JTextField ld;
	private JTextField sd;
	private JTextField su;
	private JTextField mu;
	private JTextField di;
	private JButton buttonA;
	private JButton buttonL;
	private JButton buttonSt;
	private JButton buttonM;
	private JButton buttonS;
	private JButton buttonD;
	private JButton buttonU;
	private JButton buttonB;
	private JButton submit;
	private JButton next;
	private JButton end;
	private JButton restart;
	private JButton exit;
	private Object column1[]={"Instruction","i","j","k","Issue","Execution", "Write Result"};  
	private TableModel model1;
	private JTable jt1;          
	private JScrollPane sp1;
	private Object data2[][] = {{"L1", 0, " "}, {"L2", 0, " "}, {"L3", 0, " "}};
	private Object column2[] = {" ", "Busy", "Address"};  
	private TableModel model2;
	private JTable jt2;
	private JScrollPane sp2;
	private Object data7[][] = {{"S1", 0, " ", " ", " "}, {"S2", 0, " ", " ", " "}, {"S3", 0, " ", " ", " "}};
	private Object column7[] = {" ", "Busy", "Address", "V", "Q"};  
	private TableModel model7;
	private JTable jt7;
	private JScrollPane sp7;
	private Object column3[] = {" ", "Qi"};  
	private TableModel model3;
	private JTable jt3;
	private JScrollPane sp3;
	private Object data4[][] = {{"", "A1", 0, "", "", "", "", "", "", ""},
			{"", "A2", 0, "", "", "", "", "", "", ""},
			{"", "A3", 0, "", "", "", "", "", "", ""}
	};
	private Object column4[] = {"Time", " ", "busy", "op", "Vj", "Vk", "Qj", "Qk", "A"};
	private TableModel model4;
	private JTable jt4;
	private JScrollPane sp4;
	private Object data5[][] = {{"", "M1", 0, "", "", "", "", "", "", ""},
			{"", "M2", 0, "", "", "", "", "", "", ""}
	}; 
	private TableModel model5;
	private JTable jt5;
	private JScrollPane sp5;
	private Object data6[][] = {{cycles}};
	private TableModel model6;
	private JTable jt6;
	private JScrollPane sp6;
	private Object column6[] = {"Clock"};
	
	public Marwa7a() {
		frame = new JFrame();
		simFrame = new JFrame();
		cdbFrame = new JFrame();
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
		
		frame.setIconImage(icon.getImage());
		simFrame.setIconImage(icon.getImage());
		cdbFrame.setIconImage(icon.getImage());
		
		buttonA = new JButton("ADD.D");
		buttonA.addActionListener(this);
		
		buttonM = new JButton("MUL.D");
		buttonM.addActionListener(this);
		
		buttonL = new JButton("L.D");
		buttonL.addActionListener(this);
		
		buttonSt = new JButton("S.D");
		buttonSt.addActionListener(this);
		
		buttonS = new JButton("SUB.D");
		buttonS.addActionListener(this);
		
		buttonD = new JButton("DIV.D");
		buttonD.addActionListener(this);
		
		buttonU = new JButton("Upload File");
		buttonU.addActionListener(this);
		
		buttonB = new JButton("Reset");
		buttonB.addActionListener(this);
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setBackground(Color.red);
		
		label = new JLabel("Enter Registers: ");
		labelOR = new JLabel(" OR ");
		
		result = new JLabel(r);
		result.setForeground(Color.white);
		
		textField = new JTextField(10);
		
		error = new JLabel();
		error.setBounds(220, 100, 300, 30);
		error.setForeground(Color.red);
		error.setVisible(false);
		frame.add(error);
		
		JLabel latency = new JLabel("Latencies:");
		latency.setBounds(10, 90, 100, 20);
		latency.setForeground(Color.white);
		latency.setFont(new Font("Serif", Font.PLAIN, 16));
		frame.add(latency);
		
		JLabel add = new JLabel("ADD.D");
		ad = new JTextField(4);
		
		ld = new JTextField(2);
		JLabel load = new JLabel("L.D");
		
		sd = new JTextField(2);
		JLabel store = new JLabel("S.D");
		
		su = new JTextField(2);
		JLabel sub = new JLabel("SUB.D");
		
		mu = new JTextField(2);
		JLabel mul = new JLabel("MUL.D");
		
		di = new JTextField(2);
		JLabel div = new JLabel("DIV.D");
		
		ad.setBounds(50, 120, 20, 20);
		add.setBounds(10, 120, 50, 20);
		frame.add(add);
		frame.add(ad);
		
		ld.setBounds(50, 150, 20, 20);
		load.setBounds(10, 150, 50, 20);
		frame.add(load);
		frame.add(ld);
		
		sd.setBounds(50, 180, 20, 20);
		store.setBounds(10, 180, 50, 20);
		frame.add(store);
		frame.add(sd);
		
		su.setBounds(50, 210, 20, 20);
		sub.setBounds(10, 210, 50, 20);
		frame.add(sub);
		frame.add(su);
		
		mu.setBounds(50, 240, 20, 20);
		mul.setBounds(10, 240, 50, 20);
		frame.add(mul);
		frame.add(mu);
		
		di.setBounds(50, 270, 20, 20);
		div.setBounds(10, 270, 50, 20);
		frame.add(div);
		frame.add(di);
		
		panelT = new JPanel();
		panelT.setBorder(BorderFactory.createEmptyBorder(10, 100, 5, 100));
		buttonB.setBounds(530, 120, 70, 30);
		frame.add(buttonB);
		buttonB.setVisible(false);
		panelT.add(label);
		panelT.add(textField);
		panelT.add(labelOR);
		panelT.add(buttonU);
		
		panelB = new JPanel();
		panelB.setBorder(BorderFactory.createEmptyBorder(1, 100, 250, 100));
		panelB.add(buttonL);
		panelB.add(buttonSt);
		panelB.add(buttonA);
		panelB.add(buttonM);
		panelB.add(buttonS);
		panelB.add(buttonD);
		panelB.add(result);
		panelB.setBackground(Color.gray);  
		
		panelR = new JPanel();
		panelR.setBorder(BorderFactory.createEmptyBorder(10, 100, 5, 100));
		panelR.add(submit);
		
		
		frame.add(panelT, BorderLayout.NORTH);
		frame.add(panelB, BorderLayout.CENTER);
		frame.add(panelR, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Marwa7a's Tomasulo");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		simFrame.setLayout(null);
		cdbFrame.setLayout(null);
				
		JLabel t1Label = new JLabel("Load Buffers");
		t1Label.setBounds(10,190,200,300);
		
		model2 = new DefaultTableModel(data2, column2);
		jt2 = new JTable(model2);
		sp2 = new JScrollPane(jt2);
		sp2.setBounds(10, 350, 300, 75);
		jt2.setDefaultEditor(Object.class, null);
		
		JLabel t4Label = new JLabel("Store Buffers");
		t4Label.setBounds(10,90,200,300);
		
		model7 = new DefaultTableModel(data7, column7);
		jt7 = new JTable(model7);
		sp7 = new JScrollPane(jt7);
		sp7.setBounds(10, 250, 300, 75);
		jt7.setDefaultEditor(Object.class, null);
		
		JLabel t2Label = new JLabel("Registers File");
		t2Label.setBounds(650,20,100,30);
		
		model3 = new DefaultTableModel(data3, column3);
		jt3 = new JTable(model3);
		sp3 = new JScrollPane(jt3);
		sp3.setBounds(650, 50, 100, 120);
		jt3.setDefaultEditor(Object.class, null);
		
		JLabel t3Label = new JLabel("Reservation Stations");
		t3Label.setBounds(450,220,200,30);
			
		model4 = new DefaultTableModel(data4, column4);
		jt4 = new JTable(model4);
		sp4 = new JScrollPane(jt4);
		sp4.setBounds(450, 250, 400, 75);
		jt4.setDefaultEditor(Object.class, null);
		
		model5 = new DefaultTableModel(data5, column4);
		jt5 = new JTable(model5);
		sp5 = new JScrollPane(jt5);
		sp5.setBounds(450, 350, 400, 60);
		jt5.setDefaultEditor(Object.class, null);
		  
		model6 = new DefaultTableModel(data6, column6);
		jt6 = new JTable(model6);
		sp6 = new JScrollPane(jt6);
		sp6.setBounds(800, 60, 50, 40);
		jt6.setDefaultEditor(Object.class, null);
		
		next = new JButton("Next");
		next.addActionListener(this);
		next.setBackground(Color.red);
		next.setBounds(780, 420, 100, 30);
		
		end = new JButton("END");
		end.addActionListener(this);
		end.setBackground(Color.green);
		end.setBounds(780, 420, 100, 30);
	
		simFrame.add(t1Label);
		simFrame.add(sp2);
		simFrame.add(t2Label);
		simFrame.add(t3Label);
		simFrame.add(t4Label);
		simFrame.add(sp4);
		simFrame.add(sp5);
		simFrame.add(sp6);
		simFrame.add(sp7);
		simFrame.add(next);
		simFrame.add(end);
		end.setVisible(false);
		simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		simFrame.setTitle("Marwa7a's Tomasulo");
		simFrame.setPreferredSize(new Dimension(900, 500));
		simFrame.pack();
		simFrame.setLocationRelativeTo(null);
		simFrame.setResizable(false);
		
		cdbLabel = new JLabel(cdbOut1);
		JLabel cdbTitle = new JLabel("Results:");
		cdbLabel.setBounds(120, 10, 300, 250);
		cdbTitle.setBounds(10, 10, 100, 50);
		cdbLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		cdbTitle.setFont(new Font("Serif", Font.PLAIN, 26));
		
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.red);
		exit.setBounds(280, 420, 100, 30);
		
		restart = new JButton("Restart");
		restart.addActionListener(this);
		restart.setBackground(Color.green);
		restart.setBounds(280, 380, 100, 30);
		
		cdbFrame.add(cdbLabel);
		cdbFrame.add(cdbTitle);
		cdbFrame.add(exit);
		cdbFrame.add(restart);
		cdbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cdbFrame.setTitle("Marwa7a's Tomasulo");
		cdbFrame.setPreferredSize(new Dimension(400, 500));
		cdbFrame.pack();
		cdbFrame.setLocationRelativeTo(null);
		cdbFrame.setResizable(false);
	}

	public static void main(String[] args) {
		new Marwa7a();
	}

	public void actionPerformed(ActionEvent e) {
		String x = textField.getText();
		if(e.getSource() == buttonA) {
			if(x.equals("")) {
				error.setText("Please enter registers for instruction ADD.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>ADD.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "ADD.D     " + x;
				}
				else {
					r = r + "<br/>" + "ADD.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nADD.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonM) {
			if(x.equals("")) {
				error.setText("Please enter registers for instruction MUL.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>MUL.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "MUL.D     " + x;
				}
				else {
					r = r + "<br/>" + "MUL.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nMUL.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonL) {
			if(x.equals("")) {
				error.setText("Please enter register and address for instruction L.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>L.D&ensp;&ensp;&ensp;&ensp;&ensp;" + x;
					rr = rr + "L.D     " + x;
				}
				else {
					r = r + "<br/>" + "L.D&ensp;&ensp;&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nL.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonSt) {
			if(x.equals("")) {
				error.setText("Please enter register and address for instruction S.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>S.D&ensp;&ensp;&ensp;&ensp;&ensp;" + x;
					rr = rr + "S.D     " + x;
				}
				else {
					r = r + "<br/>" + "S.D&ensp;&ensp;&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nS.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonS) {
			if(x.equals("")) {
				error.setText("Please enter registers for instruction SUB.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>SUB.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "SUB.D     " + x;
				}
				else {
					r = r + "<br/>" + "SUB.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nSUB.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonD) {
			if(x.equals("")) {
				error.setText("Please enter registers for instruction DIV.D");
				error.setVisible(true);
			}
			else {
				if(r.equals("")) {
					r = r + "<html>DIV.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "DIV.D     " + x;
				}
				else {
					r = r + "<br/>" + "DIV.D&ensp;&ensp;&ensp;" + x;
					rr = rr + "\nDIV.D     " + x;
				}
				textField.setText("");
				result.setText(r);
				buttonB.setVisible(true);
				error.setVisible(false);
			}
		}
		else if(e.getSource() == buttonU) {
			fc = new JFileChooser();
			int res = fc.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION) {
				File path = new File(fc.getSelectedFile().getAbsoluteFile(), "");
				try {
					Scanner myReader = new Scanner(path);
					String line = "";
					while (myReader.hasNextLine()) {
				        line = myReader.nextLine();
				        if(r.equals("")) {
							r = r + "<html>" + line;
							rr = rr + line;
						}
						else {
							r = r + "<br/>" + line;
							rr = rr + "\n" + line;
						}
					}
					myReader.close();
					result.setText(r);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}	
			}
			if(!r.equals(""))
				buttonB.setVisible(true);
			error.setVisible(false);
		}
		else if(e.getSource() == buttonB) {
			r = "";
			rr = "";
			result.setText(r);
			ad.setText("");
			ld.setText("");
			sd.setText("");
			su.setText("");
			mu.setText("");
			di.setText("");
			buttonB.setVisible(false);
			error.setVisible(false);
		}
		else if(e.getSource() == next) {
			boolean c = checkEOP();
			if(!c) {
				cycles++;
				simulation();
				refresh();
			}
			else {
				end.setVisible(true);
				next.setVisible(false);
			}
		}
		else if(e.getSource() == end) {
			simFrame.setVisible(false);
			printCDB();
			cdbFrame.setVisible(true);
		}
		else if(e.getSource() == exit) {
			frame.dispose();
			simFrame.dispose();
			cdbFrame.dispose();
		}
		else if(e.getSource() == restart) {
			frame.dispose();
			simFrame.dispose();
			cdbFrame.dispose();
			new Marwa7a();
		}
		else {
			if(rr.equals("")) {
				error.setText("Please enter instructions to proceed.");
				error.setVisible(true);
			}
			else {
				stArr = rr.split("[\\s,()]+");
				if(!ad.getText().equals(""))
					addLatency = Integer.parseInt(ad.getText());
				if(!ld.getText().equals(""))
					loadLatency = Integer.parseInt(ld.getText());
				if(!sd.getText().equals(""))
					storeLatency = Integer.parseInt(sd.getText());
				if(!su.getText().equals(""))
					subtractLatency = Integer.parseInt(su.getText());
				if(!mu.getText().equals(""))
					multiplyLatency = Integer.parseInt(mu.getText());
				if(!di.getText().equals(""))
					divideLatency = Integer.parseInt(di.getText());
				output = new File("output.txt");
				try {
					writer = new FileWriter(output);
					writer.write(rr);
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				result.setText("");
				read();
				insert();
				frame.setVisible(false);
				simFrame.setVisible(true);
			}
		}
	}
		
	public void read() {
		for(int i = 0; i < stArr.length; i++) {
			if(stArr[i].equals("L.D") || stArr[i].equals("MUL.D") ||
					stArr[i].equals("SUB.D") || stArr[i].equals("DIV.D") ||
					stArr[i].equals("ADD.D") || stArr[i].equals("S.D"))
				instructions++;
			else if(stArr[i].startsWith("F")) {
				boolean flag = false;
				for(int j = 0; j < regs.size(); j++) {
					if(regs.get(j).equals(stArr[i]))
						flag = true;
				}
				if(!flag) {
					regs.add(stArr[i]);
					registers++;
				}
			}
		}
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		for(int i = 0; i < regs.size(); i++) {
			String t = (String) regs.get(i);
			t = t.substring(1, t.length());
			list1.add(Integer.parseInt(t));
		}
		Collections.sort(list1);
		
		for(int i = 0; i < list1.size(); i++) {
			String t = "F" + Integer.toString(list1.get(i));
			list.add(t);
		}
	}
	
	Object data1[][] = new Object[instructions][7];
	Object data3[][] = new Object[registers][2];
	
	public void insert() {	
		Object temp[][] = new Object[instructions][7];
		Object temp2[][] = new Object[registers][2];
		int k = 0;
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < 4; j++) {
				temp[i][j] = stArr[k];
				k++;
			}
		}
		
		for(int i = 0; i < temp2.length; i++) 
			temp2[i][0] = list.get(i);
		
		model1 = new DefaultTableModel(temp, column1);
		jt1 = new JTable(model1);          
		sp1 = new JScrollPane(jt1);
		sp1.setBounds(10, 10, 550, 150);
		jt1.setDefaultEditor(Object.class, null);
		simFrame.add(sp1);
		
		model3 = new DefaultTableModel(temp2, column3);
		jt3 = new JTable(model3);
		sp3 = new JScrollPane(jt3);
		sp3.setBounds(650, 50, 100, 120);
		jt3.setDefaultEditor(Object.class, null);
		simFrame.add(sp3);
		
		data1 = temp;
		data3 = temp2;
	}
	
	ArrayList<String> busy = new ArrayList<String>();
	int[] latency = new int[100];
	
	public void simulation() {
		//execute
		if(cycles > 0) {
			for(int i = 0; i < data1.length; i++) {
				String inst = (String) data1[i][0];
				if(inst.equals("L.D")) {
					if(i + 1 < cycles) {
						if(data1[i][5] == null) {
							if(loadLatency > 1) {
								String e = Integer.toString(cycles) + "...";
								data1[i][5] = e;
							}
							else {
								data1[i][5] = cycles;
							}
						}
						else {
							if(latency[i] == 1) {
								String e = data1[i][5] + Integer.toString(cycles);
								data1[i][5] = e;
							}
						}
						if(latency[i] >= 0)
							latency[i] -= 1;
						else
							latency[i] = -5;
					}
				}
				else if(inst.equals("S.D")) {
					if(i + 1 < cycles) {
						boolean flag = false;					
						for(int j = 0; j < busy.size(); j++) {
							if(data1[i][1].equals(busy.get(j)))
								flag = true;
						}
						if(!flag) {
							if(data1[i][5] == null) {
								if(storeLatency > 1) {
									String e = Integer.toString(cycles) + "...";
									data1[i][5] = e;
								}
								else {
									data1[i][5] = cycles;
								}
							}
							else {
								if(latency[i] == 1) {
									String e = data1[i][5] + Integer.toString(cycles);
									data1[i][5] = e;
								}
							}
							if(latency[i] >= 0)
								latency[i] -= 1;
							else
								latency[i] = -5;
						}
					}
				}
				else if(inst.equals("MUL.D")) {
					if(i + 1 < cycles) {
						boolean flagj = false;
						boolean flagk = false;
						for(int j = 0; j < busy.size(); j++) {
							if(data1[i][2].equals(busy.get(j)))
								flagj = true;
							if(data1[i][3].equals(busy.get(j)))
								flagk = true;
						}
						if(!flagj && !flagk) {
							if(data1[i][5] == null) {
								if(multiplyLatency > 1) {
									String e = Integer.toString(cycles) + "...";
									data1[i][5] = e;
								}
								else {
									data1[i][5] = cycles;
								}
							}
							else {
								if(latency[i] == 1) {
									String e = data1[i][5] + Integer.toString(cycles);
									data1[i][5] = e;
								}
							}
							if(latency[i] >= 0)
								latency[i] -= 1;
							else
								latency[i] = -5;
						}
					}
				}
				else if(inst.equals("DIV.D")) {
					if(i + 1 < cycles) {
						boolean flagj = false;
						boolean flagk = false;
						for(int j = 0; j < busy.size(); j++) {
							if(data1[i][2].equals(busy.get(j)))
								flagj = true;
							if(data1[i][3].equals(busy.get(j)))
								flagk = true;
						}
						if(!flagj && !flagk) {
							if(data1[i][5] == null) {
								if(divideLatency > 1) {
									String e = Integer.toString(cycles) + "...";
									data1[i][5] = e;
								}
								else {
									data1[i][5] = cycles;
								}
							}
							else {
								if(latency[i] == 1) {
									String e = data1[i][5] + Integer.toString(cycles);
									data1[i][5] = e;
								}
							}
							if(latency[i] >= 0)
								latency[i] -= 1;
							else
								latency[i] = -5;
						}
					}
				}
				else if(inst.equals("ADD.D")) {
					if(i + 1 < cycles) {
						boolean flagj = false;
						boolean flagk = false;
						for(int j = 0; j < busy.size(); j++) {
							if(data1[i][2].equals(busy.get(j)))
								flagj = true;
							if(data1[i][3].equals(busy.get(j)))
								flagk = true;
						}
						if(!flagj && !flagk) {
							if(data1[i][5] == null) {
								if(addLatency > 1) {
									String e = Integer.toString(cycles) + "...";
									data1[i][5] = e;
								}
								else {
									data1[i][5] = cycles;
								}
							}
							else {
								if(latency[i] == 1) {
									String e = data1[i][5] + Integer.toString(cycles);
									data1[i][5] = e;
								}
							}
							if(latency[i] >= 0)
								latency[i] -= 1;
							else
								latency[i] = -5;
						}
					}
				}
				else if(inst.equals("SUB.D")) {
					if(i + 1 < cycles) {
						boolean flagj = false;
						boolean flagk = false;
						for(int j = 0; j < busy.size(); j++) {
							if(data1[i][2].equals(busy.get(j)))
								flagj = true;
							if(data1[i][3].equals(busy.get(j)))
								flagk = true;
						}
						if(!flagj && !flagk) {
							if(data1[i][5] == null) {
								if(subtractLatency > 1) {
									String e = Integer.toString(cycles) + "...";
									data1[i][5] = e;
								}
								else {
									data1[i][5] = cycles;
								}
							}
							else {
								if(latency[i] == 1) {
									String e = data1[i][5] + Integer.toString(cycles);
									data1[i][5] = e;
								}
							}
							if(latency[i] >= 0)
								latency[i] -= 1;
							else
								latency[i] = -5;
						}
					}
				}
			}
		}
		//write results
		for(int j = 0; j < data3.length; j++) {
			String s = String.valueOf(data3[j][1]);
			if(s.startsWith("mem"))
				data3[j][1] = "";
		}
		if(cycles > 0) {
			for(int i = 0; i < data1.length; i++) {
				if(latency[i] < 0 && latency[i] > -2) {
					if(data1[i][0].equals("L.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String res = "";
						String s = "";
						for(int j = 0; j < data3.length; j++) {
							if(data3[j][0].equals(reg)) {
								if(!data3[j][1].equals("")) {
									res = (String) data3[j][1];
									s += "mem" + res.substring(1, 2);
									data3[j][1] = s;
								}
							}
						}
						for(int j = 0; j < data2.length; j++) {
							if(data2[j][0].equals(res)) {
								cdb.add((String) data2[j][0]);
								cdb.add(s + " From Address: " + data2[j][2]);
								data2[j][1] = 0;
								data2[j][2] = "";
							}
						}
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data4.length; j++) {
							for(int k = 0; k < data4[j].length; k++) {
								if(res.equals(data4[j][k])) {
									data4[j][k-2] = s;
									data4[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data5.length; j++) {
							for(int k = 0; k < data5[j].length; k++) {
								if(res.equals(data5[j][k])) {
									data5[j][k-2] = s;
									data5[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data7.length; j++) {
							for(int k = 2; k < data7[j].length; k++) {
								if(res.equals(data7[j][k])) {
									data7[j][k-1] = s;
									data7[j][k] = "";
								}
							}
						}
					}
					else if(data1[i][0].equals("S.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String s = "";
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data7.length; j++) {
							if(!data7[j][3].equals("")) {
								s = (String) data7[j][3];
								cdb.add((String) data7[j][0]);
								cdb.add(s + " In Address: " + data7[j][2]);
								data7[j][3] = "";
								data7[j][2] = "";
								data7[j][1] = 0;
								break;
							}
						}
					}
					else if(data1[i][0].equals("SUB.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String res = "";
						String s = "";
						for(int j = 0; j < data3.length; j++) {
							if(data3[j][0].equals(reg)) {
								if(!data3[j][1].equals("")) {
									res = (String) data3[j][1];
									for(int k = 0; k < data4.length; k++) {
										if(res.equals(data4[k][1])) {
											s = data4[k][4] + " - " + data4[k][5];
											String n = String.valueOf(data4[k][1]);
											cdb.add(n);
											cdb.add(s);
											data4[k][0] = "";
											data4[k][2] = 0;
											data4[k][3] = "";
											data4[k][4] = "";
											data4[k][5] = "";
											data4[k][6] = "";
											data4[k][7] = "";
										}
									}
									data3[j][1] = "";
								}
							}
						}
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data4.length; j++) {
							for(int k = 2; k < data4[j].length; k++) {
								if(res.equals(data4[j][k])) {
									data4[j][k-2] = s;
									data4[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data5.length; j++) {
							for(int k = 2; k < data5[j].length; k++) {
								if(res.equals(data5[j][k])) {
									data5[j][k-2] = s;
									data5[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data7.length; j++) {
							for(int k = 2; k < data7[j].length; k++) {
								if(res.equals(data7[j][k])) {
									data7[j][k-1] = s;
									data7[j][k] = "";
								}
							}
						}
					}
					else if(data1[i][0].equals("ADD.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String res = "";
						String s = "";
						for(int j = 0; j < data3.length; j++) {
							if(data3[j][0].equals(reg)) {
								if(!data3[j][1].equals("")) {
									res = (String) data3[j][1];
									for(int k = 0; k < data4.length; k++) {
										if(res.equals(data4[k][1])) {
											s = data4[k][4] + " + " + data4[k][5];
											String n = String.valueOf(data4[k][1]);
											cdb.add(n);
											cdb.add(s);
											data4[k][0] = "";
											data4[k][2] = 0;
											data4[k][3] = "";
											data4[k][4] = "";
											data4[k][5] = "";
											data4[k][6] = "";
											data4[k][7] = "";
										}
									}
									data3[j][1] = "";
								}
							}
						}
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data4.length; j++) {
							for(int k = 2; k < data4[j].length; k++) {
								if(res.equals(data4[j][k])) {
									data4[j][k-2] = s;
									data4[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data5.length; j++) {
							for(int k = 2; k < data5[j].length; k++) {
								if(res.equals(data5[j][k])) {
									data5[j][k-2] = s;
									data5[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data7.length; j++) {
							for(int k = 2; k < data7[j].length; k++) {
								if(res.equals(data7[j][k])) {
									data7[j][k-1] = s;
									data7[j][k] = "";
								}
							}
						}
					}
					else if(data1[i][0].equals("MUL.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String res = "";
						String s = "";
						for(int j = 0; j < data3.length; j++) {
							if(data3[j][0].equals(reg)) {
								if(!data3[j][1].equals("")) {
									res = (String) data3[j][1];
									for(int k = 0; k < data5.length; k++) {
										if(res.equals(data5[k][1])) {
											s = data5[k][4] + " * " + data5[k][5];
											String n = String.valueOf(data5[k][1]);
											cdb.add(n);
											cdb.add(s);
											data5[k][0] = "";
											data5[k][2] = 0;
											data5[k][3] = "";
											data5[k][4] = "";
											data5[k][5] = "";
											data5[k][6] = "";
											data5[k][7] = "";
										}
									}
									data3[j][1] = "";
								}
							}
						}
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data4.length; j++) {
							for(int k = 2; k < data4[j].length; k++) {
								if(res.equals(data4[j][k])) {
									data4[j][k-2] = s;
									data4[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data5.length; j++) {
							for(int k = 2; k < data5[j].length; k++) {
								if(res.equals(data5[j][k])) {
									data5[j][k-2] = s;
									data5[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data7.length; j++) {
							for(int k = 2; k < data7[j].length; k++) {
								if(res.equals(data7[j][k])) {
									data7[j][k-1] = s;
									data7[j][k] = "";
								}
							}
						}
					}
					else if(data1[i][0].equals("DIV.D")) {
						data1[i][6] = cycles;
						String reg = (String) data1[i][1];
						String res = "";
						String s = "";
						for(int j = 0; j < data3.length; j++) {
							if(data3[j][0].equals(reg)) {
								if(!data3[j][1].equals("")) {
									res = (String) data3[j][1];
									for(int k = 0; k < data5.length; k++) {
										if(res.equals(data5[k][1])) {
											s = data5[k][4] + " / " + data5[k][5];
											String n = String.valueOf(data5[k][1]);
											cdb.add(n);
											cdb.add(s);
											data5[k][0] = "";
											data5[k][2] = 0;
											data5[k][3] = "";
											data5[k][4] = "";
											data5[k][5] = "";
											data5[k][6] = "";
											data5[k][7] = "";
										}
									}
									data3[j][1] = "";
								}
							}
						}
						for(int j = 0; j < busy.size(); j++) {
							if(busy.get(j).contains(reg))
								busy.remove(j);
						}
						for(int j = 0; j < data4.length; j++) {
							for(int k = 2; k < data4[j].length; k++) {
								if(res.equals(data4[j][k])) {
									data4[j][k-2] = s;
									data4[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data5.length; j++) {
							for(int k = 2; k < data5[j].length; k++) {
								if(res.equals(data5[j][k])) {
									data5[j][k-2] = s;
									data5[j][k] = "";
								}
							}
						}
						for(int j = 0; j < data7.length; j++) {
							for(int k = 2; k < data7[j].length; k++) {
								if(res.equals(data7[j][k])) {
									data7[j][k-1] = s;
									data7[j][k] = "";
								}
							}
						}
					}
				}
			}
		}
		for(int j = 0; j < data4.length; j++) {
			if(!data4[j][4].equals("") && !data4[j][5].equals("")) {
				int index = 0;
				String s = (String) data4[j][1];
				for(int k = 0; k < data3.length; k++) {
					if(s.equals(data3[k][1]))
						s = (String) data3[k][0];
				}
				for(int k = 0; k < data1.length; k++) {
					if(s.equals(data1[k][1]))
						index = k;
				}
				data4[j][0] = latency[index];
			}
		}
		for(int j = 0; j < data5.length; j++) {
			if(!data5[j][4].equals("") && !data5[j][5].equals("")) {
				int index = 0;
				String s = (String) data5[j][1];
				for(int k = 0; k < data3.length; k++) {
					if(s.equals(data3[k][1]))
						s = (String) data3[k][0];
				}
				for(int k = 0; k < data1.length; k++) {
					if(s.equals(data1[k][1]))
						index = k;
				}
				data5[j][0] = latency[index];
			}
		}
		//issue
		if(cycles > 0 && cycles <= instructions) {
			int i = cycles - 1;
			data1[i][4] = cycles;
			String inst = (String) data1[i][0];
			String reg = (String) data1[i][1];
			if (i != data1.length - 1)
				busy.add(reg);
			String s = "";
		
			if(inst.equals("L.D")){
				latency[i] = loadLatency;
				for(int j = 0; j < data2.length; j++) {
					if(data2[j][1].equals(0)) {
						data2[j][1] = 1;
						s += data1[i][2] + "+" + data1[i][3];
						data2[j][2] =  s;
						s = (String) data2[j][0];
						break;
					}
				}
				for(int j = 0; j < data3.length; j++) {
					if(data3[j][0].equals(reg))
						data3[j][1] = s;
				}		
			}
			else if(inst.equals("S.D")){
				latency[i] = storeLatency;
				for(int j = 0; j < data7.length; j++) {
					if(data7[j][1].equals(0)) {
						data7[j][1] = 1;
						s = data1[i][2] + "+" + data1[i][3];
						data7[j][2] =  s;
						boolean flag = false;
						for(int k = 0; k < busy.size(); k++) {
							if(data1[i][1].equals(busy.get(k)))
								flag = true;
						}
						if(flag) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(reg)) 
									value = (String) data3[k][1];
							}
							data7[j][4] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][2] + "]";
							data7[j][3] = value;
						}
						break;
					}
				}	
			}
			else if(inst.equals("MUL.D")) {
				latency[i] = multiplyLatency;
				for(int j = 0; j < data5.length; j++) {
					if(data5[j][2].equals(0)) {
						data5[j][2] = 1;
						data5[j][3] = "MUL";
						boolean flag = false;
						boolean flag2 = false;
						for(int k = 0; k < busy.size(); k++) {
							if(data1[i][2].equals(busy.get(k)))
								flag = true;
							if(data1[i][3].equals(busy.get(k)))
								flag2 = true;
						}
						if(flag) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2]))
									value = (String) data3[k][1];
							}
							data5[j][6] = value;
						}
						else {
							
							String value = "";
							value += "R[" + (String) data1[i][2] + "]";
							data5[j][4] = value;
						}
						if(flag2) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][3]))
									value = (String) data3[k][1];
							}
							data5[j][7] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][3] + "]";
							data5[j][5] = value;
						}
						s = (String) data5[j][1];
						break;
					}
				}
				for(int j = 0; j < data3.length; j++) {
					if(data1[i][1].equals(data3[j][0]))
						data3[j][1] = s;
				}
			}
			else if(inst.equals("DIV.D")) {
				latency[i] = divideLatency;
				for(int j = 0; j < data5.length; j++) {
					if(data5[j][2].equals(0)) {
						data5[j][2] = 1;
						data5[j][3] = "DIV";
						boolean flag = false;
						boolean flag2 = false;
						for(int k = 0; k < busy.size(); k++) {
							if(data1[i][2].equals(busy.get(k)))
								flag = true;
							if(data1[i][3].equals(busy.get(k)))
								flag2 = true;
						}
						if(flag) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2]))
									value = (String) data3[k][1];
							}
							data5[j][6] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][2] + "]";
							data5[j][4] = value;
						}
						if(flag2) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][3]))
									value = (String) data3[k][1];
							}
							data5[j][7] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][3] + "]";
							data5[j][5] = value;
						}
						s = (String) data5[j][1];
						break;
					}
				}
				for(int j = 0; j < data3.length; j++) {
					if(data1[i][1].equals(data3[j][0]))
						data3[j][1] = s;
				}
			}
			else if(inst.equals("ADD.D")) {
				latency[i] = addLatency;
				for(int j = 0; j < data4.length; j++) {
					if(data4[j][2].equals(0)) {
						data4[j][2] = 1;
						data4[j][3] = "ADD";
						boolean flag = false;
						boolean flag2 = false;
						for(int k = 0; k < busy.size(); k++) {
							if(data1[i][2].equals(busy.get(k)))
								flag = true;
							if(data1[i][3].equals(busy.get(k)))
								flag2 = true;
						}
						if(flag) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2]))
									value = (String) data3[k][1];
							}
							data4[j][6] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][2] + "]";
							data4[j][4] = value;
						}
						if(flag2) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][3]))
									value = (String) data3[k][1];
							}
							data4[j][7] = value;
						}
						else {
							String value = "";
							value += "R[" + (String) data1[i][3] + "]";
							data4[j][5] = value;
						}
						s = (String) data4[j][1];
						break;
					}
				}
				for(int j = 0; j < data3.length; j++) {
					if(data1[i][1].equals(data3[j][0]))
						data3[j][1] = s;
				}
			}
			else if(inst.equals("SUB.D")) {
				latency[i] = subtractLatency;
				for(int j = 0; j < data4.length; j++) {
					if(data4[j][2].equals(0)) {
						data4[j][2] = 1;
						data4[j][3] = "SUB";
						boolean flag = false;
						boolean flag2 = false;
						for(int k = 0; k < busy.size(); k++) {
							if(data1[i][2].equals(busy.get(k)))
								flag = true;
							if(data1[i][3].equals(busy.get(k)))
								flag2 = true;
						}
						if(flag) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2]))
									value = (String) data3[k][1];
							}
							data4[j][6] = value;
						}
						else {
							String value = "";
							boolean empty = true;
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2])) {
									if(!data3[k][1].equals("")) {
										empty = false;
										value = (String) data3[k][1];
									}
								}
							}
							if(empty) 
								value += "R[" + (String) data1[i][2] + "]";
							data4[j][4] = value;
						}
						if(flag2) {
							String value = "";
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][3]))
									value = (String) data3[k][1];
							}
							data4[j][7] = value;
						}
						else {
							String value = "";
							boolean empty = true;
							for(int k = 0; k < data3.length; k++) {
								if(data3[k][0].equals(data1[i][2])) {
									if(!data3[k][1].equals("")) {
										empty = false;
										value = (String) data3[k][1];
									}
								}
							}
							if(empty) 
								value += "R[" + (String) data1[i][2] + "]";
							data4[j][5] = value;
						}
						s = (String) data4[j][1];
						break;
					}
				}
				for(int j = 0; j < data3.length; j++) {
					if(data1[i][1].equals(data3[j][0]))
						data3[j][1] = s;
				}
			}
		}
	}
	
	public void refresh() {
		model1 = new DefaultTableModel(data1, column1);
		jt1 = new JTable(model1);          
		sp1 = new JScrollPane(jt1);
		sp1.setBounds(10, 10, 550, 150);
		jt1.setDefaultEditor(Object.class, null);
		simFrame.add(sp1);
		
		model2 = new DefaultTableModel(data2, column2);
		jt2 = new JTable(model2);
		sp2 = new JScrollPane(jt2);
		sp2.setBounds(10, 350, 300, 75);
		jt2.setDefaultEditor(Object.class, null);
		simFrame.add(sp2);
		
		model7 = new DefaultTableModel(data7, column7);
		jt7 = new JTable(model7);
		sp7 = new JScrollPane(jt7);
		sp7.setBounds(10, 250, 300, 75);
		jt7.setDefaultEditor(Object.class, null);
		simFrame.add(sp7);
		
		model3 = new DefaultTableModel(data3, column3);
		jt3 = new JTable(model3);
		sp3 = new JScrollPane(jt3);
		sp3.setBounds(650, 50, 100, 120);
		jt3.setDefaultEditor(Object.class, null);
		simFrame.add(sp3);
		
		model4 = new DefaultTableModel(data4, column4);
		jt4 = new JTable(model4);
		sp4 = new JScrollPane(jt4);
		sp4.setBounds(450, 250, 400, 75);
		jt4.setDefaultEditor(Object.class, null);
		simFrame.add(sp4);
		
		model5 = new DefaultTableModel(data5, column4);
		jt5 = new JTable(model5);
		sp5 = new JScrollPane(jt5);
		sp5.setBounds(450, 350, 400, 60);
		jt5.setDefaultEditor(Object.class, null);
		simFrame.add(sp5);
		  
		data6[0][0] = cycles;
		model6 = new DefaultTableModel(data6, column6);
		jt6 = new JTable(model6);
		sp6 = new JScrollPane(jt6);
		sp6.setBounds(800, 60, 50, 40);
		jt6.setDefaultEditor(Object.class, null);
		simFrame.add(sp6);
		
		printAll();
	}
	
	public void printArr(Object[] arr) {
		System.out.println("");
		for(int i = 0; i < arr.length; i++) {
			System.out.print("\t" + arr[i] + " ");
		}
		System.out.println("");
	}
	
	public void printArr(Object[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print("\t" + arr[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public void printArr1() {
		Object column1temp[]={"Inst","i","j","k","Issue","Exec", "Write Result"};
		printArr(column1temp);
	}
	
	public void printCDB() {
		System.out.println("##########RESULTS##########\n");
		String out = "";
		String out1 = "<html>";
		for(int i = 0; i < cdb.size(); i+=2) {
			out = cdb.get(i) + ": " + cdb.get(i + 1);
			out1 += out + "<br/>"; 
			cdbOut += out;
			cdbOut += "\n";
		}
		out1 += "</html>";
		cdbOut1 = out1;
		cdbLabel.setText(cdbOut1);
		System.out.println(cdbOut);
		System.out.println("##########RESULTS##########");
	}
	
	public void printAll() {
		System.out.println("\t------------------------------------START OF CYCLE------------------------------------");
		
		printArr(column6);
		printArr(data6);
		
		printArr1();
		printArr(data1);
		
		printArr(column7);
		printArr(data7);
		
		printArr(column2);
		printArr(data2);
		
		printArr(column3);
		printArr(data3);
		
		printArr(column4);
		printArr(data4);
		
		printArr(column4);
		printArr(data5);
		
		System.out.println("\t------------------------------------END OF CYCLE------------------------------------\n");
	}
	
	public boolean checkEOP() {
		boolean flag = true;
		for(int i = 0; i < data1.length; i++) {
			for(int j = 4; j < data1[i].length; j++) {
				if(data1[i][j] == null) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			return true;
		}
		else
			return false;
	}
}

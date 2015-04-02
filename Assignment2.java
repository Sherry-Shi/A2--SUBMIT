package assignment2;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class MainFrame extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JLabel LogoLabel;
	JLabel headLabel;
	JButton notifyButton;
	LoginPanel loginPanel;
	SignupPanel signUpPanel;
	SqlPanel sqlPanel;
	ButtonPanel buttonPanel;
//	PostandSearchPanel postandsearch;
	ResultPanel resultPanel;
	Connection conn=null;
	ArrayList<String> requester = new ArrayList<String>();
	ArrayList<String> Relation= new ArrayList<String>();
	int countrequest=0;
	String UserEmail;
	JTextArea resultArea = null;
	JScrollPane scrollPane = null;
	int trigger = 0;
	JLabel showLabel;
	int hasRequest = 0;
	
	StringBuffer SQLOut = new StringBuffer ();
	StringBuffer ResultOut=new StringBuffer();
	MainFrame(){
		setResizable(false);
		setLayout(null);
		setSize(1100, 700);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((width-1100)/2,(height-700)/2);
		setTitle("This is GUI for database homework");
		SetLogo();
		setLoginPanel();
		setSignupPanel();
		setSqlPanel();
		setButtonPanel();
		setResultPanel();
	//	postandsearch.disablePanel();
		buttonPanel.disablePanel();
	}

	public void disableResult(){
    	resultArea.setText("");
    	resultArea.setEditable(false);
    	resultArea.setEnabled(false);
    	scrollPane.setEnabled(false);
	}

	public void setResultPanel(){

		resultArea = new JTextArea(10,30);
		resultArea.setLineWrap(true);
		scrollPane = new JScrollPane(resultArea);
		headLabel = new JLabel("Shopping System");
		add(scrollPane);
		add(headLabel);
		
		headLabel.setFont(new Font("Serif", Font.BOLD, 30));
		headLabel.setBounds(240,20 , 360, 60);
		scrollPane.setBounds(20, 100,740, 250);
	}

	public void SetLogo(){
	Image image;
	try {
		image = ImageIO.read(new File("usc_viterbi_logo.jpg"));
		ImageIcon icon = new ImageIcon(image);
		LogoLabel = new JLabel();
		LogoLabel.setIcon(icon);
		LogoLabel.setBounds(830,500,300,150);

		add(LogoLabel);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  //this generates an image file

	}

	

	public void setButtonPanel(){
		buttonPanel = new ButtonPanel();
		buttonPanel.setBounds(30, 380, 700, 90);
		this.add(buttonPanel);

		
		buttonPanel.buttons[0].addActionListener(new ActionListener() {
	          
            public void actionPerformed(ActionEvent e) {
            	StringBuffer result= new StringBuffer();
            	/*Fill this function*/
            	/*Press this your account button, you should be able to list*/
            	/* current log in customer information in the result panel (Including Email, First Name, Last Name, Address)*/
            	/*You can define the output format*/
            	resultPanel=new ResultPanel();
            	conn=ConnectDB.openConnection();
              	 String QueryStr = "select * from USERS where Email='"+loginPanel.username.getText()+"'";
              	 SQLOut.append(QueryStr+"\n\n");
              	 try {
  					Statement stmt = conn.createStatement();
  					ResultSet re = stmt.executeQuery(QueryStr);
  					 if(re.next())
  	            	 {
  						String email= re.getString("EMAIL");
  						String fname=re.getString("FNAME");
  						String lname=re.getString("LNAME");
  						String Add=re.getString("STRADRESS");
  						result.append("Email:"+email.trim()+"\n"+"FirstName:"+fname+"\n"+"LastName:"+lname+"\n"+"StrAddress:"+Add+"\n");
  						resultArea.setText("");
  						resultArea.append(result.toString());
  	            		
  	            	 }
  	            	 else
  	            	 {
 	            		 JOptionPane.showMessageDialog(null, "No ... please signup");
  	            		 signUpPanel.enablePanel();
  	            		// loginPanel.disablePanel();
  	            	}
  					

  				} catch (SQLException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  					ConnectDB.closeConnection(conn);
  					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
  					return;
  				}
              	 

            }
        });
		
		buttonPanel.buttons[1].addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
            	StringBuffer result= new StringBuffer();
            	/*Fill this function*/
            	/*Press this all products button, you should be able to list all the products which are visible to you*/
            	/*You can define the output format*/
            	conn=ConnectDB.openConnection();
             	 String QueryStr = "select * from Products";
             	 SQLOut.append(QueryStr+"\n\n");
             	 try {
 					Statement stmt = conn.createStatement();
 					ResultSet re = stmt.executeQuery(QueryStr);
 					int i=1;
 					 while(re.next())
 	            	 {
 						
 						String proID= re.getString("PRODUCTID");
 						String category=re.getString("CATEGORY");
 						String brand=re.getString("BRAND");
 						String name=re.getString("NAME");
 						String price=re.getString("PRICE");
 						result.append("/********************************NO.:"+i+"*************************/\n");
 						i++;
 						result.append("ProductID:"+proID+"\n"+"Category:"+category+"\n"+"Brand:"+brand+"\n"+"Name:"+name+"\n"+"Price:"+price+"\n\n\n");
 						resultArea.setText("");
  						resultArea.append(result.toString());
 	            	 }
 					 re.close();
 					// ConnectDB.closeConnection(conn);

 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 					ConnectDB.closeConnection(conn);
 					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
 					return;
 				}
             	 


            }
        });

		buttonPanel.buttons[2].addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
            	final Frame0 frame=new Frame0();
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
                frame.btn1.addActionListener(new ActionListener() {
                	
                    public void actionPerformed(ActionEvent e) {
                    	/*Fill this function*/
    	            	/*Press this choose category Button, after choosing and pressing OK*/
    	            	/* you should be able to list all products belong to this category*/
                    	String item= frame.combo.getSelectedItem().toString();
                    	String QueryStr = "select * from Products where Category='"+item+"'";
                    	StringBuffer result= new StringBuffer();
                    	 SQLOut.append(QueryStr+"\n\n");
                    	 try {
                    		 conn=ConnectDB.openConnection();
        					Statement stmt = conn.createStatement();
        					ResultSet re = stmt.executeQuery(QueryStr);
        					int i=1;
        					 while(re.next())
        					 {
        						String proID= re.getString("PRODUCTID");
        	 					String category=re.getString("CATEGORY");
        	 					String brand=re.getString("BRAND");
        	 					String name=re.getString("NAME");
        	 					String price=re.getString("PRICE");
        	 					result.append("/********************************NO.:"+i+"*************************/\n");
        	 					i++;
        	 					result.append("ProductID:"+proID+"\n"+"Category:"+category+"\n"+"Brand:"+brand+"\n"+"Name:"+name+"\n"+"Price:"+price+"\n\n\n");
        	 					resultArea.setText("");
          						resultArea.append(result.toString());
        					 }
        					 re.close();
                    }
                    	catch (SQLException e1) {
          					// TODO Auto-generated catch block
          					e1.printStackTrace();
          					ConnectDB.closeConnection(conn);
          					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
          					return;
          				}
                    }
                });
            }
        });

		
		buttonPanel.buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	final Frame1 frame=new Frame1("Please input Price Range ");
	            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	            frame.setVisible(true);

	            frame.btn1.addActionListener(new ActionListener() {
	               
	                public void actionPerformed(ActionEvent e) {
	                	/*Fill this function*/
	                	/*Press this set price range Button, you should be able to set price range*/
	                	/*Pressing "Set Price Range" button, a new window will pop out. */
	                	/*Then you can enter "Min_Price" & "Max_Price" and press "Search" button, */
	                	/*and then all products belong to category that you choose should be shown in the result panel.*/
	                	String lowPrice=frame.txtfield[0].getText();
	                	String highPrice=frame.txtfield[1].getText();
	                	float low=Float.parseFloat(lowPrice);
	                	float high=Float.parseFloat(highPrice);
	                	String QueryStr = "select * from Products where price>='"+low+"' and price<='"+high+"'";
                    	StringBuffer result= new StringBuffer();
                    	 SQLOut.append(QueryStr+"\n\n");
                    	 try {
                    		 conn=ConnectDB.openConnection();
        					Statement stmt = conn.createStatement();
        					ResultSet re = stmt.executeQuery(QueryStr);
        					int i=1;
        					 while(re.next())
        					 {
        						String proID= re.getString("PRODUCTID");
        	 					String category=re.getString("CATEGORY");
        	 					String brand=re.getString("BRAND");
        	 					String name=re.getString("NAME");
        	 					String price=re.getString("PRICE");
        	 					result.append("/********************************NO.:"+i+"*************************/\n");
        	 					i++;
        	 					result.append("ProductID:"+proID+"\n"+"Category:"+category+"\n"+"Brand:"+brand+"\n"+"Name:"+name+"\n"+"Price:"+price+"\n\n\n");
        	 					resultArea.setText("");
          						resultArea.append(result.toString());
        					 }
        					 re.close();
                    }
                    	catch (SQLException e1) {
          					// TODO Auto-generated catch block
          					e1.printStackTrace();
          					ConnectDB.closeConnection(conn);
          					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
          					return;
          				}
                    
	                }
	            });

	        }
			});
            
     

		buttonPanel.buttons[4].addActionListener(new ActionListener() {
			String proId;
            String quantity;
            String total1;
        public void actionPerformed(ActionEvent e) {
        	final Frame2 frame=new Frame2();
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setVisible(true);
            
            
            frame.btn1.addActionListener(new ActionListener() {
               
                public void actionPerformed(ActionEvent e) {
                	/*Fill this function*/     
//                	Press "Order Products" button, a new window will pop out. 
//                	Then you can enter "Product ID" and "Quantity", and then you press continue, 
//                	the Total Price should be shown correctly. 
//                	Then, you can press "Place Order" to complete this order. 
//                	This new order should be synchronized in the database.
                	
                	 proId=frame.productID.getText();
                	quantity=frame.quantity.getText();
                	String QueryStr = "select * from Products where PRODUCTID='"+proId+"'";
                	StringBuffer result= new StringBuffer();
                	 SQLOut.append(QueryStr+"\n\n");
                	 try {
                		 conn=ConnectDB.openConnection();
    					Statement stmt = conn.createStatement();
    					ResultSet re = stmt.executeQuery(QueryStr);
    					int i=1;
    					 if(re.next())
    					 {
    	 					String price=re.getString("PRICE");
    	 					float quan=Float.parseFloat(quantity);
    	 					float price1= Float.parseFloat(price);
    	 					float total=price1*quan;
    	 					
    	 					total1=Float.toString(total);
    	 					frame.totalPrice.setText(total1);
    					 }
    					 else{
	    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
	    					}
    					 re.close();
                }
                	catch (SQLException e1) {
      					// TODO Auto-generated catch block
      					e1.printStackTrace();
      					ConnectDB.closeConnection(conn);
      					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
      					return;
      				}
                }
            });


            frame.btn2.addActionListener(new ActionListener() {
           
                public void actionPerformed(ActionEvent e) {
                	/*Fill this function*/
                	/*Press this accept all Button, you should be able to accept all friend request and add this information into friend relationship table*/
                	/*pop up a standard dialog box to show <succeed or failed>*/
                	String QueryStr="Select count(*) from orders";
                	SQLOut.append(QueryStr+"\n\n");
                	conn=ConnectDB.openConnection();
					
	                //	 SQLOut.append(QueryStr+"\n\n");
	                	 try {
	                		 Statement stmt = conn.createStatement();
		    				ResultSet re = stmt.executeQuery(QueryStr);
		    				if(re.next())
		    				{
		    					String num=re.getString("count(*)");
	    						int num1=Integer.parseInt(num);
	    						num1+=1;
	    						num=String.valueOf(num1);
	    						String orderid="O"+num;
	    						String proqty="("+proId+","+quantity+")";
	    						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    						String datetime = sdf.format(Calendar.getInstance().getTime());
	    						QueryStr = "insert into orders(ORDERID,CUSTOMER,PROQTY,TOTALPRICE,PLACETIME) values(' "+orderid+"','"+UserEmail.trim()+"','"+proqty+"','"+total1+"','"+datetime+"')";
	    						SQLOut.append(QueryStr+"\n\n");
	    						stmt.executeUpdate(QueryStr);
	    						QueryStr="select * from products where productid='"+proId+"'";
	    						stmt = conn.createStatement();
	    						re = stmt.executeQuery(QueryStr);
	    						if(re.next())
	    						{
	    							String stock=re.getString("STOCK_QUANTITY");
	    							int stock1=Integer.parseInt(stock);
	    							int quantity1= Integer.parseInt(quantity);
	    							quantity1=stock1-quantity1;
	    							quantity=String.valueOf(quantity1);
	    							QueryStr="UPDATE PRODUCTS SET STOCK_QUANTITY='"+quantity+"'where productid='"+proId+"'";
	    							SQLOut.append(QueryStr+"\n\n");
	    							stmt.executeUpdate(QueryStr);
	    							JOptionPane.showMessageDialog(null, "Successfully order!!!");
	    						}
	    						else{
		    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
		    					}
	    					
		    				}
		    				else{
	    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
	    					}
	                	 }
	                	catch (SQLException e1) {
	      					// TODO Auto-generated catch block
	      					e1.printStackTrace();
	      					ConnectDB.closeConnection(conn);
	      					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
	      					return;
	      				}
	
                }
            });

        }
		});

		buttonPanel.buttons[5].addActionListener(new ActionListener() {
	          
            public void actionPerformed(ActionEvent e) {
            	StringBuffer result= new StringBuffer();
            	/*Fill this function*/
            	/*Press "Your Orders", all order history of this customer should be shown in the result panel.*/
            	String QueryStr="Select * from orders where customer='"+UserEmail.trim()+"'";
            	StringBuffer result2= new StringBuffer();
                //	 SQLOut.append(QueryStr+"\n\n");
                	 try {
                		 conn=ConnectDB.openConnection();
    					Statement stmt = conn.createStatement();
    					ResultSet re = stmt.executeQuery(QueryStr);
    					int i=1;
    					while(re.next())
    					{
    						String Oid=re.getString("ORDERID");
    						//String content=re.getString("CUSTOM");
    						String proq=re.getString("PROQTY");
    						String total=re.getString("TOTALPRICE");
    						String placetime=re.getString("PLACETIME");
    						result2.append("/***************NO "+i+"****************/"+"\n");
    						i++;
    						result2.append("ORDERID:" +Oid+"\n"+"PROQTY:" +proq+"\n"+"TOTALPRICE:"+total+"\n"+"PLACETIME:"+placetime+"\n\n\n");
    						resultArea.setText("");
      						resultArea.append(result2.toString());
    					}
                	 }
                	 catch (SQLException e1) {
	      					// TODO Auto-generated catch block
	      					e1.printStackTrace();
	      					ConnectDB.closeConnection(conn);
	      					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
	      					return;
                		 
                	 }
            	 

            }
        });

		
		buttonPanel.buttons[6].addActionListener(new ActionListener() {
	           
	        public void actionPerformed(ActionEvent e) {
	        	final Frame5 frame=new Frame5("Product ID : ","Review : ");
	            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	            frame.setVisible(true);

	            frame.btn1.addActionListener(new ActionListener() {
	               
	                public void actionPerformed(ActionEvent e) {
	                	/*Fill this function*/
	                	/*Press "Review Products" button, a new window will pop out. */
	                	/*Input product ID and review content and press the OK button, this information should be inserted into database.*/
	                	int rid;
	                	rid=0;
	                	String proid=frame.txtfield.getText();
	                	String review=frame.textArea.getText();
	                	//String email= signUpPanel.email.getText();
	                	String QueryStr="Select count(*) from reviews";
	                	
	                	//String QueryStr = "Insert into reviews (REVIEWID,CONTENT,AUTHORID,PRODUCTID) values(' "+reviewid+"','"+review+"','"+email.trim()+"','"+proid+"')"; 
	                	StringBuffer result= new StringBuffer();
	                	 conn=ConnectDB.openConnection();
	    					
	                //	 SQLOut.append(QueryStr+"\n\n");
	                	 try {
	                		 Statement stmt = conn.createStatement();
		    				ResultSet re = stmt.executeQuery(QueryStr);
		    				if(re.next())
		    				{
		    					String num=re.getString("count(*)");
	    						int num1=Integer.parseInt(num);
	    						num1+=1;
	    						num=String.valueOf(num1);
	    						String reviewid="R"+num;
	    					
	    						QueryStr = "Insert into reviews (REVIEWID,CONTENT,AUTHORID,PRODUCTID) values('"+reviewid+"','"+review+"','"+UserEmail.trim()+"','"+proid+"')"; 
	    						stmt.executeUpdate(QueryStr);
	    						JOptionPane.showMessageDialog(null, "Successfully reviewed!!!");
		    				}
	    					//int i=1;
	    					
		    				else{
	    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
	    					}
	                	 }
	                	 catch (SQLException e1) {
		      					// TODO Auto-generated catch block
		      					e1.printStackTrace();
		      					JOptionPane.showMessageDialog(null, "SQL Error!!!");
		      					ConnectDB.closeConnection(conn);
		      					return;
	                		 
	                	 }
	                }
	            });


	        }
			});
		buttonPanel.buttons[7].addActionListener(new ActionListener() {
	           
	        public void actionPerformed(ActionEvent e) {
	        	final Frame4 frame=new Frame4("Product ID : ","Submit");
	            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	            frame.setVisible(true);

	            frame.btn1.addActionListener(new ActionListener() {
	               
	                public void actionPerformed(ActionEvent e) {
	                	/*Fill this function*/
	                	/*Press "List All Reviews" button, a new window will pop out. */
	                	/*Input "Product ID" and press submit, all reviews about this product should be shown in the result panel.*/
	                	String productid=frame.txtfield.getText();
	                	String QueryStr="Select * from reviews where productid='"+productid+"'";
	                	StringBuffer result= new StringBuffer();
		                //	 SQLOut.append(QueryStr+"\n\n");
		                	 try {
		                		 conn=ConnectDB.openConnection();
		    					Statement stmt = conn.createStatement();
		    					ResultSet re = stmt.executeQuery(QueryStr);
		    					int i=1;
		    					while(re.next())
		    					{
		    						String rid=re.getString("REVIEWID");
		    						String content=re.getString("CONTENT");
		    						String aid=re.getString("AUTHORID");
		    						String pid=re.getString("PRODUCTID");
		    						result.append("/***************NO "+i+"****************/"+"\n");
		    						i++;
		    						result.append("REVIEWID:" +rid+"\n"+"CONTENT:" +content+"\n"+"AUTHOR:"+aid+"\n"+"PRODUCTID:"+pid+"\n\n\n");
		    						resultArea.setText("");
	          						resultArea.append(result.toString());
		    					}
		                	 }
		                	 catch (SQLException e1) {
			      					// TODO Auto-generated catch block
			      					e1.printStackTrace();
			      					JOptionPane.showMessageDialog(null, "SQL Error!!!");
			      					ConnectDB.closeConnection(conn);
			      					return;
		                		 
		                	 }
	                	
	                }
	            });


	            

	        }
			});
		
		buttonPanel.buttons[8].addActionListener(new ActionListener() {
	           
	        public void actionPerformed(ActionEvent e) {
	        	final Frame4 frame=new Frame4("Review ID : ","Like it");
	            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	            frame.setVisible(true);

	            frame.btn1.addActionListener(new ActionListener() {
	               
	                public void actionPerformed(ActionEvent e) {
	                	/*Fill this function*/
	                	/*Press "Like Reviews" button, a new window will pop out. */
	                	/*Input "Review ID" and press "Like it", this information should be inserted into database.*/
	                	String rid=frame.txtfield.getText();
	                	String QueryStr="Select * from reviews where reviewid='"+rid+"'";
	                	//StringBuffer result= new StringBuffer();
		                //	 SQLOut.append(QueryStr+"\n\n");
		                	 try {
		                		 conn=ConnectDB.openConnection();
		    					Statement stmt = conn.createStatement();
		    					ResultSet re = stmt.executeQuery(QueryStr);
		    					int i=1;
		    					if(re.next())
		    					{
		    						String rating=re.getString("RATING");
		    						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    						String datetime = sdf.format(Calendar.getInstance().getTime());
		    						QueryStr="INSERT INTO LIKEREVIEW1(REVIEWID,CUSTOMER,TIME) VALUEs('"+rid+"','"+UserEmail.trim()+"','"+datetime+"')";
		    						stmt.executeUpdate(QueryStr);
		    						JOptionPane.showMessageDialog(null, "Successfully like!!!");
		    						
		    					}
		    					else{
		    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
		    					}
		                	 }
		                	 catch (SQLException e1) {
			      					// TODO Auto-generated catch block
			      					e1.printStackTrace();
			      					JOptionPane.showMessageDialog(null, "SQL Error!!!");
			      					ConnectDB.closeConnection(conn);
			      					return;
		                		 
		                	 }
	                	
	                }
	            });


	            

	        }
			});	
		
		buttonPanel.buttons[9].addActionListener(new ActionListener() {
	          
            public void actionPerformed(ActionEvent e) {
            	StringBuffer result= new StringBuffer();
            	/*Fill this function*/
            	/*Press "List All Likes" button, all reviews that liked by this customer should be shown in the result panel.*/
            	String QueryStr="Select * from likereview1 where CUSTOMER='"+UserEmail.trim()+"'";
            	StringBuffer result1= new StringBuffer();
                //	 SQLOut.append(QueryStr+"\n\n");
                	 try {
                		 conn=ConnectDB.openConnection();
    					Statement stmt = conn.createStatement();
    					ResultSet re = stmt.executeQuery(QueryStr);
    					int i=1;
    					while(re.next())
    					{
    						
    						String rid=re.getString("REVIEWID");
    						QueryStr="Select * from reviews where REVIEWID='"+rid+"'";
    						SQLOut.append(QueryStr+"\n\n");
    						stmt = conn.createStatement();
    						re = stmt.executeQuery(QueryStr);
    						if(re.next())
    						{
    							String content=re.getString("CONTENT");
    							String aid=re.getString("AUTHORID");
    							String pid=re.getString("PRODUCTID");
    							result1.append("/***************NO "+i+"****************/"+"\n");
    							i++;
    							result1.append("REVIEWID:" +rid+"\n"+"CONTENT:" +content+"\n"+"AUTHOR:"+aid+"\n"+"PRODUCTID:"+pid+"\n\n\n");
    							resultArea.setText("");
    							resultArea.append(result1.toString());
    						}
    						else{
	    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
	    					}
    					}
                	 }
                	 catch (SQLException e1) {
	      					// TODO Auto-generated catch block
	      					e1.printStackTrace();
	      					ConnectDB.closeConnection(conn);
	      					return;
                		 
                	 }

            }
        });
		buttonPanel.buttons[10].addActionListener(new ActionListener() {
	          
            public void actionPerformed(ActionEvent e) {
            	StringBuffer result= new StringBuffer();
            	/*Fill this function*/
            	/*Press "Nearest Seller" button, the nearest seller info for this customer should be shown in the result panel.*/
            	/*This is a spatial query*/
            	
            	String QueryStr="Select addr2.vertice.SDO_POINT.x as user_x, addr2.vertice.sdo_point.y as user_y "
            			+ "			from address addr2, customers m"
            			+" where addr2.ADDRID = m.ADDRID and m.email ='"+UserEmail.trim()+"'";
            	SQLOut.append(QueryStr+"\n\n");
							 		 
            	//StringBuffer result1= new StringBuffer();
                //	 SQLOut.append(QueryStr+"\n\n");
                	 try {
                		 conn=ConnectDB.openConnection();
    					Statement stmt = conn.createStatement();
    					ResultSet re = stmt.executeQuery(QueryStr);
    					int i=1;
    					if(re.next())
    					{
    						
    						String x=re.getString("user_x");
    						String y=re.getString("user_y");
    						QueryStr="select "+"/"+"*"+"+ ORDERED USE_NL(addr,m1)"+"*"+"/"+" * from address addr, sellers m1"
    								+" where  m1.ADDRESS_ID = addr.ADDRID and m1.email<>'"+UserEmail+"' and  SDO_NN(addr.vertice, sdo_geometry(2001, NULL, sdo_point_type("+x+" ,"+y+" ,NULL), NULL, NULL),  'sdo_batch_size=20', 1) = 'TRUE' and rownum < 2";
    						SQLOut.append(QueryStr+"\n\n");
    						stmt = conn.createStatement();
    						re = stmt.executeQuery(QueryStr);
    						if(re.next())
    						{
    							result.append("The seller who is nearest to you:\r\n" + re.getString("EMAIL") + "\r\n");
    							//String email= re.getString("EMAIL");
    	  						String fname=re.getString("FNAME");
    	  						String lname=re.getString("LNAME");
    	  						String Add=re.getString("STREET_ADDRESS");
    	  						result.append("FirstName:"+fname+"\n"+"LastName:"+lname+"\n"+"StrAddress:"+Add+"\n");
    							//result1.append("REVIEWID:" +rid+"\n"+"CONTENT:" +content+"\n"+"AUTHOR:"+aid+"\n"+"PRODUCTID:"+pid+"\n\n\n");
    							resultArea.setText("");
    							resultArea.append(result.toString());
    						}
    						else{
	    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
	    					}
    					}
    					else{
    						JOptionPane.showMessageDialog(null, "Error Happens!!!");
    					}
                	 }
                	 catch (SQLException e1) {
	      					// TODO Auto-generated catch block
	      					e1.printStackTrace();
	      					ConnectDB.closeConnection(conn);
	      					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
	      					return;
                		 
                	 }
            }
        });
		
		buttonPanel.buttons[11].addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            	final Frame3 frame=new Frame3("Please input coordinate: ");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);

                frame.btn1.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent e) {
                    	/*Fill this function*/
                    	/*Press this Button, input left top corner coordinate and right down corner coordinate*/
                    	/*press ok, you should be able list the information(including address information) about seller who lives in this area. Close query window*/
                    	/*This is a spatial query*/
                    	String[] value = new String[4];
                    	for(int i = 0; i < 4; i++) {
                    		value[i] = frame.txtfield[i].getText();
                    	}
                    	String QueryStr = String.format("select m.email, m.fname, m.lname, a1.city,a1.state, a1.STREET_ADDRESS, a1.ZIPCODE"
								+" from address a1, sellers m "
								+" where "
								+"a1.addrid= m.address_id  and "
								+" SDO_RELATE(a1.vertice, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,3), SDO_ORDINATE_ARRAY(%s,%s,%s,%s)), 'mask=ANYINTERACT') = 'TRUE'",
								value[0], value[3], value[2], value[1]);
                    	
                    	resultArea.setText("");
                    	sqlPanel.SQLArea.setText("");
                    	try {
                    		conn = ConnectDB.openConnection();
                    		Statement stmt = conn.createStatement();
        					ResultSet rs = stmt.executeQuery(QueryStr);
							sqlPanel.SQLArea.append(QueryStr + "\n");
							while(rs.next()) {
								String email = rs.getString(1);
								String fname = rs.getString(2);
								String lname = rs.getString(3);
								String city =rs.getString(4);
								String state=rs.getString(5);
								String street_address = rs.getString(6);
								String  zipcode= rs.getString(7);
								
								String result1 = String.format("email: %s\nfname: %s\nlname: %s\ncity: %s\nstate: %s\nstreet_address: %s\n"
										+ "zipcode: %s\n\n",
										email, fname, lname, city,state,street_address, zipcode);
								resultArea.append(result1);
							}
							
							rs.close();
							stmt.close();
							ConnectDB.closeConnection(conn);
                    	}
                    	 catch (SQLException e1) {
 	      					// TODO Auto-generated catch block
 	      					e1.printStackTrace();
 	      					ConnectDB.closeConnection(conn);
 	      					JOptionPane.showMessageDialog(null, "SQL Happens!!!");
 	      					return;
                 		 
                 	 }
                    }
                });
            }
        });
		
	}

	public void setResultOutput(StringBuffer sb)
	{
		resultPanel.resultArea.setText(sb.toString());
		//sqlPanel.SQLArea.setEnabled(true);
	}
	public void setSQLOutput(StringBuffer sb)
	{
		sqlPanel.SQLArea.setText(sb.toString());
		sqlPanel.SQLArea.setEnabled(true);
	}
	public void setSqlPanel(){
		sqlPanel = new SqlPanel();
		showLabel = new JLabel("The corresponding SQL sentence:");
		showLabel.setBounds(30, 490, 400, 20);
		sqlPanel.setBounds(5, 515,790, 150);
		this.add(sqlPanel);
		this.add(showLabel);
	}

	public void setLoginPanel(){
		loginPanel = new LoginPanel();
		this.add(loginPanel);

		loginPanel.signup.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		           signUpPanel.enablePanel();
			}
        });
        loginPanel.login.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {  
            //	buttonPanel.enablePanel();
            	//Fill this fun
            	/*Press this Button, you should be a atch the user information. If valid, keep the user email information(but can't modified) and clear the password*/
            	/*If invalid, you should pop up a dialog box to notify user, then enable signup panel for user to register*/
            	/*After logged in, you should change this button's function as logout which means disable all the panel, return to the original state*/
            	if(trigger==0){
               	 //match account
               	 conn=ConnectDB.openConnection();
               	 String QueryStr = "select EMAIL from USERS where Email='"+loginPanel.username.getText()+"'";
               	 SQLOut.append(QueryStr+"\n\n");
               	 try {
   					Statement stmt = conn.createStatement();
   					ResultSet re = stmt.executeQuery(QueryStr);
   					if(re.next())
   					{
   						QueryStr= "select EMAIL from USERS where Email='"+loginPanel.username.getText()+"' and PASSWD = '"+loginPanel.password.getText()+"'";
   						SQLOut.append(QueryStr+"\n\n");
   						stmt=conn.createStatement();
   						re=stmt.executeQuery(QueryStr);
   						if(re.next())
   						{
   							UserEmail=loginPanel.username.getText().trim();
   						 loginPanel.setUserName(loginPanel.username.getText().toString());
   	            		 loginPanel.disablePanel();
   	            		 loginPanel.password.setText("");
   	            		 trigger = 1;
   	            		 loginPanel.login.setText("logout");
   	            		 signUpPanel.disablePanel();
   	            		 buttonPanel.enablePanel();
  	            		 loginPanel.signup.setEnabled(false);
   						}
   						else
   						{
   							JOptionPane.showMessageDialog(null, "Password is not matching!");
   	        
   						}
   					}
   	            	 else
   	            	 {
  	            		 JOptionPane.showMessageDialog(null, "No ... please signup");
   	            		 signUpPanel.enablePanel();
   	            		// loginPanel.disablePanel();
   	            	}
   					 ConnectDB.closeConnection(conn);

   				} catch (SQLException e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   					ConnectDB.closeConnection(conn);
   					return;
   				}

               	//getnotification


               }else{
               	loginPanel.login.setText("login");
               	loginPanel.enablePanel();
               	loginPanel.signup.setEnabled(true);
               	loginPanel.password.setText("");
               	loginPanel.username.setText("");
               	disableResult();
               	trigger = 0;
               	buttonPanel.disablePanel();
           	}
               	setSQLOutput(SQLOut);
              	}

           });
   

	}

	public void setSignupPanel(){

		signUpPanel = new SignupPanel();
		this.add(signUpPanel);
		signUpPanel.signup.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {  
            	/*Fill this function*/
            	/*Press this signup button, you should be able check whether current account is existed. If existed, pop up an error, if not check input validation(You can design this part according to your database table's restriction) create the new account information*/
            	/*pop up a standard dialog box to show <succeed or failed>*/
            	String email= signUpPanel.email.getText().trim();
            	conn=ConnectDB.openConnection();
              	 
              	 try {
  					
  						 String psd1=signUpPanel.password.getText();
  						 String psd2=signUpPanel.password2.getText();
  						 String fname= signUpPanel.fname.getText();
  						String lname= signUpPanel.lname.getText();
  						String birth= signUpPanel.birthday.getText();
  						String strno=signUpPanel.str_no.getText();
  						String stradd=signUpPanel.str_address.getText();
  						String city=signUpPanel.city.getText();
  						String state=signUpPanel.state.getText();
  						String zip=signUpPanel.zip.getText();
  						String reg="^[0-9]{2}(/)[0-9]{2}(/)[0-9]{4}$";
  						String reg1="^[0-9]+$";
  						if(email.isEmpty()||psd1.isEmpty()||psd2.isEmpty()||fname.isEmpty()||lname.isEmpty()||birth.isEmpty()||strno.isEmpty()||stradd.isEmpty()||city.isEmpty()||state.isEmpty()||zip.isEmpty())
  						{
  							JOptionPane.showMessageDialog(null, "Error! Please fill all the fields! ");
  						}
  						else if(!psd1.equals(psd2)) 
  						 {
  							 JOptionPane.showMessageDialog(null, "Error! Please check your password you entered! ");
  						 }
  						else if(!birth.matches(reg))
  						 {
  							JOptionPane.showMessageDialog(null, "Error! The format of birthday should be MM/DD/YYYY!");
  						 }
  						else if(!strno.matches(reg1)||!zip.matches(reg1))
  						{
  							JOptionPane.showMessageDialog(null, "Error! strno and zip fields shobiuld be numbers!");
  						} 
  						else // valid inputs
  						{
  							String QueryStr = "select EMAIL from users where Email='"+email.trim()+"'";
  			              	 SQLOut.append(QueryStr+"\n\n");
  							Statement stmt = conn.createStatement();
  		  					ResultSet re = stmt.executeQuery(QueryStr);
  		  					if(re.next())
	  	  					 {
	  	  						 JOptionPane.showMessageDialog(null, "Error! The user has already existed! "); 
	  	  					 }
  		  					else 
  		  					{
  		  						JOptionPane.showMessageDialog(null, "Success! Please login! "); 
  		  						//signUpPanel.disablePanel();
  		  						UserEmail=email.trim();
  		  						QueryStr = "Insert into users (EMAIL,FNAME,LNAME,BIRTHDAY,STRNO,STRADRESS,CITY,STATE,ZIP,PASSWD) values('"+email.trim()+"','"+fname+"','"+lname+"','"+birth+"','"+strno+"','"+stradd+"','"+city+"','"+state+"','"+zip+"','"+psd1+"')";
  		  						SQLOut.append(QueryStr+"\n\n");
  		  						stmt = conn.createStatement();
  		  						stmt.executeUpdate(QueryStr);
  		  					}
  							
  						}
  					 ConnectDB.closeConnection(conn);

    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    					ConnectDB.closeConnection(conn);
    					return;
    				}
            }
        });

		signUpPanel.disablePanel();

	}


}


class ConnectDB{

	public static Connection openConnection(){
        try{
	        String driverName = "oracle.jdbc.driver.OracleDriver";
	        Class.forName(driverName);

	        //set the username and password for your connection.
	        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	        String uname = "C##Sophie";
	        String pwd = "Sophie0705";

	        return DriverManager.getConnection(url, uname, pwd);
        }
        catch(ClassNotFoundException e){
        	System.out.println("Class Not Found");
        	e.printStackTrace();
        	return null;
        }
        catch(SQLException sqle){
        	System.out.println("Connection Failed");
        	sqle.printStackTrace();
        	return null;
        }

	}
	public static void closeConnection(Connection conn)
	{
		try{
		 conn.close();
	 }
	    catch (Exception e){
	    	e.printStackTrace();
	    	System.out.println("connection closing error ");
	    }
	}
}
public class Assignment2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	MainFrame frame = new MainFrame();
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

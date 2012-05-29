import hu.bme.mit.inf.gs.workflow.buyapp.BuyAppProcess;
import hu.bme.mit.inf.gs.workflow.buyapp.QualityCheckProcess;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class gui_main
{

	public static void main(String[] args)
	{
		Fielder f = new Fielder();
		f.inicFields();
	}
}
class Fielder implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		String s = ae.getActionCommand();
		if (s.equals("buyApp"))
		{
			// buy app button event handler
			System.out.println("Let's buy that app!");
			
			/*
			 * HTTP elérések:
			 * tApp.getText();tCredit.getText();tUser.getText();tCode.getText();
			 * User:
			 * tBuyer.getText();
			 * App id:
			 * tAID.getText();
			 */
			try {
				BuyAppProcess.startProcess(tBuyer.getText(), Integer.parseInt(tAID.getText()), tApp.getText(), tCredit.getText(), tUser.getText());
			} catch (NumberFormatException e) {
				System.out.println("Az ID legyen már szám...");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("upsz: " + e.getMessage());
				e.printStackTrace();
			}
		}
		else if (s.equals("checkQual"))
		{
			// check qual button event handler
			boolean isNewVersion;
			if (cType.getSelectedItem().equalsIgnoreCase("ApplicationVersion")) {
				isNewVersion = true;
			} else {
				isNewVersion = false;
			}
			try {
				QualityCheckProcess.startQualityCheckProcess(tApp.getText(), tCode.getText(), isNewVersion, Integer.parseInt(tAQID.getText()));
			} catch (NumberFormatException e) {
				System.out.println("Az ID legyen már szám...");
			} catch (Exception e) {
				System.out.println("upsz: " + e.getMessage());
			}
			/*
			 * HTTP elérések:
			 * tApp.getText();tCredit.getText();tUser.getText();tCode.getText();
			 * ComboBox(Choice):
			 * int cType.getSelectedIndex();
			 * vagy
			 * string cType.getSelectedItem();
			 * App id:
			 * tAID.getText();
			 */
		}
	}
	
	
	// Globals:
	Frame frm=new Frame("GS User Interface");
	GridBagConstraints con = new GridBagConstraints();
	
	// HTTP labels and texts:
	Panel p1 = new Panel();
	Panel p = new Panel();
	GridBagLayout gLay= new GridBagLayout();
	Label lApp = new Label("AppRepository");
	TextField tApp = new TextField(80);
	Label lCredit = new Label("CreditManager");
	TextField tCredit = new TextField(80);
	Label lUser = new Label("UserManager");
	TextField tUser = new TextField(80);
	Label lCode =new Label("CodeVerifier");
	TextField tCode=new TextField(80);
	
	//BuyApp labels and texts:
	Panel p2 = new Panel();
	Panel q = new Panel();
	GridBagLayout hLay= new GridBagLayout();
	Label lAppBuy = new Label("Buy Application...");
	Label lBuyer = new Label("BuyerName");
	TextField tBuyer = new TextField(60);
	Label lAID = new Label("ApplicationID");
	TextField tAID = new TextField(60);
	Button buyApp=new Button("Buy App");
	
	// Quality check labels etc.:
	Panel p3 = new Panel();
	Panel r = new Panel();
	GridBagLayout iLay= new GridBagLayout();
	Label lQual = new Label("Quality Check...");
	Label lType = new Label("Type");
	Choice cType = new Choice();
	Label lAQID = new Label("ApplicationID");
	TextField tAQID = new TextField(60);
	Button checkQual = new Button("Check Quality");
	
	public void inicFields()
	{
		frm.setSize(710,395);
		frm.setVisible(true);
		frm.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
		});
		
		
		
		
		con.fill = GridBagConstraints.BOTH;
		gLay.columnWeights = new double[]{1.0f, 5.0f};
		gLay.rowWeights = new double[]{1.0f, 1.0f, 1.0f, 1.0f};
		p.setLayout(gLay);
		
		con.weightx = 1.0;
		con.gridwidth = GridBagConstraints.WEST;
		gLay.setConstraints(lApp, con);
		p.add(lApp);
		con.gridwidth = GridBagConstraints.REMAINDER;
		gLay.setConstraints(tApp, con);
		p.add(tApp);
		con.gridwidth = GridBagConstraints.WEST;
		gLay.setConstraints(lCredit, con);
		p.add(lCredit);
		con.gridwidth = GridBagConstraints.REMAINDER;
		gLay.setConstraints(tCredit, con);
		p.add(tCredit);
		con.gridwidth = GridBagConstraints.WEST;
		gLay.setConstraints(lUser, con);
		p.add(lUser);
		con.gridwidth = GridBagConstraints.REMAINDER;
		gLay.setConstraints(tUser, con);
		p.add(tUser);
		con.gridwidth = GridBagConstraints.WEST;
		gLay.setConstraints(lCode, con);
		p.add(lCode);
		con.gridwidth = GridBagConstraints.REMAINDER;
		gLay.setConstraints(tCode, con);
		p.add(tCode);


		p1.add(p);
		frm.add(p1,BorderLayout.NORTH);
		
		con = new GridBagConstraints();
		con.fill = GridBagConstraints.BOTH;
		hLay.columnWeights = new double[]{1.0f, 4.0f};
		hLay.rowWeights = new double[]{1.0f, 1.0f, 1.0f, 1.0f};
		q.setLayout(hLay);
		
		con.weightx = 1.0;
		con.gridwidth = 2;
		con.gridheight = 1;
		con.gridwidth = GridBagConstraints.REMAINDER;
		lAppBuy.setFont(new Font("times", 0, 32));
		hLay.setConstraints(lAppBuy, con);
		q.add(lAppBuy);
		con.gridwidth = GridBagConstraints.WEST;
		con.gridheight = 1;
		hLay.setConstraints(lBuyer, con);
		q.add(lBuyer);
		con.gridwidth = GridBagConstraints.REMAINDER;
		hLay.setConstraints(tBuyer, con);
		q.add(tBuyer);
		con.gridwidth = GridBagConstraints.WEST;
		hLay.setConstraints(lAID, con);
		q.add(lAID);
		con.gridwidth = GridBagConstraints.REMAINDER;
		hLay.setConstraints(tAID, con);
		q.add(tAID);
		con.gridwidth = GridBagConstraints.REMAINDER;
		hLay.setConstraints(buyApp, con);
		q.add(buyApp);
		buyApp.setActionCommand("buyApp");
		buyApp.addActionListener(this);
		
		/*Button Submit=new Button("Gomb");p.add(Submit);*/
		p2.add(q);
		frm.add(p2,BorderLayout.CENTER);
		
		con = new GridBagConstraints();
		con.fill = GridBagConstraints.BOTH;
		iLay.columnWeights = new double[]{1.0f, 4.0f};
		iLay.rowWeights = new double[]{1.0f, 1.0f, 1.0f, 1.0f};
		cType.add("ApplicationVersion");
		cType.add("ApplicationMetadata");
		r.setLayout(iLay);
		
		con.weightx = 1.0;
		con.gridwidth = 2;
		con.gridheight = 1;
		con.gridwidth = GridBagConstraints.REMAINDER;
		lQual.setFont(new Font("times", 0, 32));
		iLay.setConstraints(lQual, con);
		r.add(lQual);
		con.gridwidth = GridBagConstraints.WEST;
		con.gridheight = 1;
		iLay.setConstraints(lType, con);
		r.add(lType);
		con.gridwidth = GridBagConstraints.REMAINDER;
		iLay.setConstraints(cType, con);
		r.add(cType);
		con.gridwidth = GridBagConstraints.WEST;
		iLay.setConstraints(lAQID, con);
		r.add(lAQID);
		con.gridwidth = GridBagConstraints.REMAINDER;
		iLay.setConstraints(tAQID, con);
		r.add(tAQID);
		con.gridwidth = GridBagConstraints.REMAINDER;
		iLay.setConstraints(checkQual, con);
		r.add(checkQual);
		checkQual.setActionCommand("checkQual");
		checkQual.addActionListener(this); 
		
		p3.add(r);
		frm.add(p3,BorderLayout.SOUTH);
	}
}

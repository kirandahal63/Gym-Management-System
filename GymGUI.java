import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

// new class GymGUI is created to include GUI components
public class GymGUI implements ActionListener{
    private JFrame fr,displayFrame;
    private JLabel lTitle,lpd,lId,lName,lLocation,lPhone,lEmail,lGender,lDob,lMembershipStartDate,lReferralSource,lPlan,lRemovalReason,
    lRegularPlanPrice,lId_pm,lMembershipStartDate_pm,lPaidAmount,lTrainerName,lPremiumPlanPrice,lDiscountAmount;
    private JTextField id,id_pm,name,location,phone,email,referralSource,paidAmount,removalReason,trainerName,
    regularPlanPrice,premiumPlanPrice,discountAmount;
    private JRadioButton male,female,others;
    private JComboBox planComboBox,dobYearComboBox,dobMonthComboBox,dobDayComboBox,msYearComboBox,msMonthComboBox,msDayComboBox,
    msDay_pm, msMonth_pm, msYear_pm; 
    private JButton addRegularMember,activateMembership,deactivateMembership,markAttendance,upgradePlan,revertMember,display,clear,
    saveToFile,readFromFile,addPremiumMember,calculateDiscount,payDueAmount,activateMembership_pm,deactivateMembership_pm,markAttendance_pm,
    revertMember_pm,clear_pm,saveToFile_pm,readFromFile_pm;
    private JTabbedPane tab,tabs;
    private JPanel panel1,panel2,panel3,panel4;
    private JTextArea displayMembers,area;
    private JScrollPane scrollPane;

    //creating Generic ArrayList
    private ArrayList<GymMember> objectList = new ArrayList<GymMember>();

    public GymGUI(){
        //creating gui components
        fr = new JFrame();
        lTitle = new JLabel("GYM SYSTEM");
        lId = new JLabel("Id:");
        lName = new JLabel("Name:");
        lLocation = new JLabel("Location:");
        lPhone = new JLabel("Phone:");
        lEmail = new JLabel("Email:");
        lGender = new JLabel("Gender:");
        lDob = new JLabel("DOB:");
        lPlan = new JLabel("Plan:");
        lMembershipStartDate = new JLabel("Membership Start Date:");
        lReferralSource = new JLabel("Referral Source:");
        lPaidAmount = new JLabel("Pay Amount:");
        lRemovalReason = new JLabel("Removal Reason:");
        lTrainerName = new JLabel("Trainer's Name:");
        lRegularPlanPrice = new JLabel("Regular Plan Price:");
        lPremiumPlanPrice = new JLabel("Premium Plan Price:");
        lDiscountAmount = new JLabel("Discount Amount:");
        lId_pm = new JLabel("Id:");
        lMembershipStartDate_pm = new JLabel("Membership Start Date:");

        id = new JTextField();
        id_pm = new JTextField();
        name = new JTextField();
        location = new JTextField();
        phone = new JTextField();
        email = new JTextField();
        referralSource = new JTextField();
        paidAmount = new JTextField();
        removalReason = new JTextField();
        trainerName = new JTextField();
        regularPlanPrice = new JTextField();
        premiumPlanPrice = new JTextField();        
        discountAmount = new JTextField();
        //setting non-editable text field for regular plan price, premium plan charge and discount amount
        regularPlanPrice.setEditable(false);
        premiumPlanPrice.setEditable(false);
        discountAmount.setEditable(false);

        male = new JRadioButton("Male");
        male.setSelected(true);
        female = new JRadioButton("Female");
        others = new JRadioButton("Others");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        buttonGroup.add(others);

        String[] plan = {"Basic","Standard","Deluxe"};
        planComboBox = new JComboBox(plan);

        String[] dobYear = new String[91];
        for(int i = 0;i<dobYear.length;i++){
            dobYear[i]=String.valueOf(1935+i);
        }
        String[] dobMonth = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String[] dobDay = new String[31];
        for(int i=0;i<dobDay.length;i++){
            dobDay[i]=String.valueOf(i+1);
        }        
        dobYearComboBox = new JComboBox(dobYear);
        dobMonthComboBox = new JComboBox(dobMonth);
        dobDayComboBox = new JComboBox(dobDay);

        String[] msYear = new String[20];
        for(int i = 0;i<msYear.length;i++){
            msYear[i]=String.valueOf(2025-i);
        }
        String[] msMonth = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String[] msDay = new String[31];
        for(int i=0;i<msDay.length;i++){
            msDay[i]=String.valueOf(i+1);
        }
        msYearComboBox = new JComboBox(msYear);
        msMonthComboBox = new JComboBox(msMonth);
        msDayComboBox = new JComboBox(msDay);
        msDay_pm = new JComboBox(msDay);
        msMonth_pm = new JComboBox(msMonth);
        msYear_pm = new JComboBox(msYear);

        addRegularMember = new JButton("Add Member");
        addPremiumMember = new JButton("Add Member");
        activateMembership = new JButton("Activate Membership");
        deactivateMembership = new JButton("Deactivate Membership");
        markAttendance = new JButton("Mark Attendance");
        upgradePlan = new JButton("Upgrade Plan");
        calculateDiscount = new JButton("Calculate Discount");
        revertMember = new JButton("Revert Member");
        payDueAmount = new JButton("Pay Due Amount");
        display = new JButton("Display");
        clear = new JButton("Clear");
        saveToFile = new JButton("Save to File");
        readFromFile = new JButton(" Read from File");

        //button for premium member
        activateMembership_pm = new JButton("Activate Membership");
        deactivateMembership_pm = new JButton("Deactivate Membership");
        markAttendance_pm = new JButton("Mark Attendance");
        revertMember_pm = new JButton("Revert Member");
        clear_pm = new JButton("Clear");
        saveToFile_pm = new JButton("Save to File");
        readFromFile_pm = new JButton(" Read from File");

        // adding font size
        lTitle.setFont(new Font("Times new roman",Font.BOLD,35));
        //lTitle.setForeground(Color.WHITE);  
        lTitle.setBounds(400,20,300,40);

        // Create a JPanel and set its layout to null
        tab = new JTabbedPane();
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(60, 70, 926, 150); // Set bounds for the panel
        //panel1.setBackground(new Color(245, 239, 203));
        //setting set Bounds in panel
        lName.setBounds(60,25,80,25);
        name.setBounds(140,25,160,25);
        lLocation.setBounds(620,25,80,25);
        location.setBounds(720,25,160,25);
        lEmail.setBounds(340,25,80,25);
        email.setBounds(400,25,180,25);
        lPhone.setBounds(60,80,80,25);
        phone.setBounds(140,80,160,25);
        lDob.setBounds(340,80,80,25);
        dobDayComboBox.setBounds(400,80,50,25);
        dobMonthComboBox.setBounds(460,80,50,25);
        dobYearComboBox.setBounds(520,80,60,25);
        lGender.setBounds(620,80,60,25);
        male.setBounds(680,80,60,25);
        female.setBounds(740,80,70,25);
        others.setBounds(810,80,70,25);      

        //adding Action Listener to buttons
        addRegularMember.addActionListener(this);
        activateMembership.addActionListener(this);
        deactivateMembership.addActionListener(this);
        revertMember.addActionListener(this);
        upgradePlan.addActionListener(this);
        markAttendance.addActionListener(this);
        clear.addActionListener(this);
        saveToFile.addActionListener(this);
        readFromFile.addActionListener(this);
        saveToFile_pm.addActionListener(this);
        readFromFile_pm.addActionListener(this);

        //adding to panel 1
        panel1.add(lName);
        panel1.add(name);
        panel1.add(lLocation);
        panel1.add(location);
        panel1.add(lPhone);
        panel1.add(phone);
        panel1.add(lEmail);
        panel1.add(email);
        panel1.add(lDob);
        panel1.add(dobDayComboBox);
        panel1.add(dobMonthComboBox);
        panel1.add(dobYearComboBox);
        panel1.add(lGender);
        panel1.add(male);
        panel1.add(female);
        panel1.add(others);
        tab.addTab("Personal Details", panel1);
        tab.setBounds(60, 90, 926, 155); 

        tabs = new JTabbedPane();
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(60, 320, 903, 200); // Set bounds for the panel
        //panel2.setBackground(new Color(245, 239, 203));

        //setting set Bounds in panel
        lId.setBounds(60,10,100,25);
        id.setBounds(60,35,160,25);
        lPlan.setBounds(280,15,160,25);
        planComboBox.setBounds(280,40,160,25);
        lRegularPlanPrice.setBounds(491,15,160,25);
        regularPlanPrice.setBounds(491,40,170,25);
        upgradePlan.setBounds(715, 40, 160, 25);
        lMembershipStartDate.setBounds(60,75,160,25);
        msDayComboBox.setBounds(60,100,50,25);
        msMonthComboBox.setBounds(140,100,60,25);
        msYearComboBox.setBounds(240,100,80,25);
        lReferralSource.setBounds(491,75,160,25);
        referralSource.setBounds(491,100,170,25);
        lRemovalReason.setBounds(715,75,160,25);
        removalReason.setBounds(715,100,160,25);
        addRegularMember.setBounds(60,140,160,25);        
        activateMembership.setBounds(280,140,160,25);
        deactivateMembership.setBounds(491,140,170,25);
        revertMember.setBounds(715, 140, 160, 25);
        markAttendance.setBounds(60, 180, 160, 25);
        clear.setBounds(280, 180, 160, 25);
        saveToFile.setBounds(491, 180, 170, 25);
        readFromFile.setBounds(715, 180, 160, 25);
        //adding to panel 2        
        panel2.add(lId);
        panel2.add(id);
        panel2.add(lRegularPlanPrice);
        panel2.add(regularPlanPrice);
        panel2.add(lPlan);
        panel2.add(planComboBox);
        panel2.add(upgradePlan);
        panel2.add(lMembershipStartDate);
        panel2.add(msDayComboBox);
        panel2.add(msMonthComboBox);
        panel2.add(msYearComboBox);
        panel2.add(lReferralSource);
        panel2.add(referralSource);
        panel2.add(lRemovalReason);
        panel2.add(removalReason);
        panel2.add(addRegularMember);
        panel2.add(activateMembership);
        panel2.add(deactivateMembership);
        panel2.add(revertMember);
        panel2.add(markAttendance);
        panel2.add(clear);
        panel2.add(saveToFile);
        panel2.add(readFromFile);

        panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(60, 290, 926, 210); 

        lId_pm.setBounds(60,10,100,25);
        id_pm.setBounds(60,35,160,25);
        lPremiumPlanPrice.setBounds(280,15,160,25);
        premiumPlanPrice.setBounds(280,40,160,25);                
        lDiscountAmount.setBounds(491,15,160,25);
        discountAmount.setBounds(491,40,170,25);
        calculateDiscount.setBounds(715, 40, 160, 25);

        lMembershipStartDate_pm.setBounds(60,75,160,25);
        msDay_pm.setBounds(60,100,50,25);
        msMonth_pm.setBounds(110,100,50,25);
        msYear_pm.setBounds(160,100,60,25);
        lTrainerName.setBounds(280,75,160,25);
        trainerName.setBounds(280,100,160,25);  
        lPaidAmount.setBounds(491,75,160,25);
        paidAmount.setBounds(491,100,170,25);
        payDueAmount.setBounds(715,100,160,25);

        addPremiumMember.setBounds(60,140,160,25);        
        activateMembership_pm.setBounds(280,140,160,25);
        deactivateMembership_pm.setBounds(491,140,170,25);
        revertMember_pm.setBounds(715, 140, 160, 25);
        markAttendance_pm.setBounds(60, 180, 160, 25);
        clear_pm.setBounds(280, 180, 160, 25);
        saveToFile_pm.setBounds(491, 180, 170, 25);
        readFromFile_pm.setBounds(715, 180, 160, 25);

        addPremiumMember.addActionListener(this);
        activateMembership_pm.addActionListener(this);
        deactivateMembership_pm.addActionListener(this);
        markAttendance_pm.addActionListener(this);
        revertMember_pm.addActionListener(this);
        clear_pm.addActionListener(this);
        saveToFile_pm.addActionListener(this);
        readFromFile_pm.addActionListener(this);
        display.addActionListener(this);
        calculateDiscount.addActionListener(this);
        planComboBox.addActionListener(this);
        payDueAmount.addActionListener(this);
        panel3.add(lId_pm);
        panel3.add(id_pm);
        panel3.add(lPremiumPlanPrice);
        panel3.add(premiumPlanPrice);
        panel3.add(lDiscountAmount);
        panel3.add(discountAmount);
        panel3.add(calculateDiscount);        
        panel3.add(lMembershipStartDate_pm);
        panel3.add(msDay_pm);
        panel3.add(msMonth_pm);
        panel3.add(msYear_pm);
        panel3.add(lTrainerName);
        panel3.add(trainerName);
        panel3.add(lPaidAmount);
        panel3.add(paidAmount);
        panel3.add(payDueAmount);        
        panel3.add(addPremiumMember);
        panel3.add(activateMembership_pm);
        panel3.add(deactivateMembership_pm);
        panel3.add(revertMember_pm);
        panel3.add(markAttendance_pm);
        panel3.add(clear_pm);
        panel3.add(saveToFile_pm);
        panel3.add(readFromFile_pm);

        // Add tabs to the JTabbedPane
        tabs.addTab("Regular Member", panel2);
        tabs.addTab("Premium Member", panel3);
        tabs.setBounds(60, 270, 926, 250);     

        panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setBounds(60, 540, 926, 100); // Set bounds for the panel
        panel4.setBackground(new Color(229,229,229));
        displayMembers = new JTextArea(10,10);
        displayMembers.setEditable(false); // Make it read-only
        displayMembers.setFont(new Font("Monospaced", Font.PLAIN, 11)); // Use monospaced font for alignment

        // Create a scroll pane for the text area
        scrollPane = new JScrollPane(displayMembers, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0, 926, 100); // Position within panel4
        panel4.add(scrollPane);

        //adding panel to frame

        fr.add(tab);
        fr.add(panel4); 
        fr.add(tabs); 

        display.setBounds(868, 650, 120, 35);
        fr.add(display);

        // adding GUI componentss to frame
        fr.add(lTitle);

        //setting frame layout,visibility,size
        fr.setLayout(null);
        fr.setVisible(true);
        fr.setSize(1066,735);
        fr.setResizable(false);
    }
    //Java event handling by implementing ActionListener
    @Override
    public void actionPerformed(ActionEvent ae){

        // Getting input values from textfields and comboboxes
        String name_ = name.getText().trim();
        String location_ = location.getText().trim();
        String phone_ = phone.getText().trim();
        String email_ = email.getText().trim();
        String DOB_ = dobMonthComboBox.getSelectedItem()+" "+dobDayComboBox.getSelectedItem()+", "+
            dobYearComboBox.getSelectedItem(); 
        String gender_ = "";
        if (male.isSelected()) {
            gender_ = "Male";
        } else if (female.isSelected()) {
            gender_ = "Female";
        } else {
            gender_ = "Others";
        }
        String membershipStartDate_ =msMonthComboBox.getSelectedItem()+" "+msDayComboBox.getSelectedItem()+", "+
            msYearComboBox.getSelectedItem();  
        String referralSource_ = referralSource.getText().trim();
        String reason_ = removalReason.getText().trim().trim();

        String DOB_pm_= msMonth_pm.getSelectedItem()+" "+msDay_pm.getSelectedItem()+", "+
            msYear_pm.getSelectedItem();
        String trainer_ = trainerName.getText().trim();
        String membershipStartDate_pm_ =msMonth_pm.getSelectedItem()+" "+msDay_pm.getSelectedItem()+", "+
            msYear_pm.getSelectedItem(); 

        if(ae.getSource()== addRegularMember){
            if (id.getText().isEmpty() || name_.isEmpty() || location_.isEmpty() || 
            phone_.isEmpty() || email_.isEmpty() || referralSource_.isEmpty() ||
            (!male.isSelected() && !female.isSelected() && !others.isSelected())) {
                JOptionPane.showMessageDialog(fr,"Please fill all the required fields!","ERROR",JOptionPane.ERROR_MESSAGE);                
            }else{ 
                if(phone_.length() !=10){
                    JOptionPane.showMessageDialog(fr,"Please enter a valid Phonne Number!", "Invalid Input",JOptionPane.ERROR_MESSAGE);   
                    
                    try{
                        int number = Integer.parseInt(phone.getText());
                    }
                    catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(fr,"Please enter a valid Phonne Number!", "Invalid Input",JOptionPane.ERROR_MESSAGE);     
                    }
                    return;
                }
                try{
                    int id_ = Integer.parseInt(id.getText());
                    if(id_ < 1){
                        JOptionPane.showMessageDialog(fr,"Please enter an ID greater than 0.", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                        return;
                    }  
                    if(!email_.endsWith("@gmail.com")){
                        JOptionPane.showMessageDialog(fr,"Please enter a valid email.", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(objectList.isEmpty()){

                        //creating RegularMember object
                        RegularMember regularmemberList = new RegularMember(id_, name_, location_, phone_, email_, DOB_, gender_, membershipStartDate_, referralSource_);

                        int userChoice = JOptionPane.showConfirmDialog(fr,"Please confirm that you want to add this member.","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        if(userChoice == JOptionPane.YES_OPTION){
                            //Adding object of Regular member to arraylist
                            objectList.add(regularmemberList);
                            JOptionPane.showMessageDialog(fr,"Member added sucessfully !","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(fr,"Member was not added!! You may try again.","Information",JOptionPane.INFORMATION_MESSAGE);  
                        }

                        // After adding to objectList
                        regularPlanPrice.setText(String.valueOf(regularmemberList.getPlanPrice(regularmemberList.getPlan())));
                    }else{
                        boolean memberExists = false;
                        for(GymMember member: objectList){
                            if(id_ == member.getId()){
                                memberExists = true;
                            }
                        }
                        if(memberExists){
                            JOptionPane.showMessageDialog(fr,"This ID is already registered in the system.\nPlease verify and try again.","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            //creating RegularMember object
                            RegularMember regularmemberList = new RegularMember(id_, name_, location_, phone_, email_, DOB_, gender_, membershipStartDate_, referralSource_);

                            int userChoice = JOptionPane.showConfirmDialog(fr,"Please confirm that you want to add this member.","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            if(userChoice == JOptionPane.YES_OPTION){
                                //Adding object of Regular member to arraylist
                                objectList.add(regularmemberList);
                                JOptionPane.showMessageDialog(fr,"Member added sucessfully !","Information",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(fr,"Member was not added!! You may try again.","Information",JOptionPane.INFORMATION_MESSAGE);  
                            }

                            // After adding to objectList
                            regularPlanPrice.setText(String.valueOf(regularmemberList.getPlanPrice(regularmemberList.getPlan())));
                        }
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        else if(ae.getSource()== addPremiumMember){

            if (id_pm.getText().trim().isEmpty() || name_.isEmpty() || location_.isEmpty() || 
            phone_.isEmpty() || email_.isEmpty() ||
            (!male.isSelected() && !female.isSelected() && !others.isSelected())) 
            {

                JOptionPane.showMessageDialog(fr,"Please fill all the required fields!","ERROR",JOptionPane.ERROR_MESSAGE);
            }else
            {   
                if(phone_.length() !=10){
                    JOptionPane.showMessageDialog(fr,"Please enter a valid Phonne Number!", "Invalid Input",JOptionPane.ERROR_MESSAGE);   
                    
                    try{
                        int number = Integer.parseInt(phone.getText());
                    }
                    catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(fr,"Please enter a valid Phonne Number!", "Invalid Input",JOptionPane.ERROR_MESSAGE);     
                    }
                    return;
                }

                try{
                    int id_pm_ = Integer.parseInt(id_pm.getText());
                    if(id_pm_ < 1){
                        JOptionPane.showMessageDialog(fr,"Please enter an ID greater than 0.", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                        return;
                    } 
                    if(!email_.endsWith("@gmail.com")){
                        JOptionPane.showMessageDialog(fr,"Please enter a valid email.", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(objectList.isEmpty()){

                        //creating PremiumMember object
                        PremiumMember premiumMemberList = new PremiumMember(id_pm_,name_, location_, phone_, email_, DOB_, 
                                gender_, membershipStartDate_pm_,trainer_);
                        int userChoice = JOptionPane.showConfirmDialog(fr,"Please confirm that you want to add this member.","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        if(userChoice == JOptionPane.YES_OPTION){
                            //Adding object of Regular member to arraylist
                            objectList.add(premiumMemberList);
                            JOptionPane.showMessageDialog(fr,"Member added sucessfully !","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(fr,"Member was not added!! You may try again.","Information",JOptionPane.INFORMATION_MESSAGE);  
                        }

                        // Showing premium membership charge
                        premiumPlanPrice.setText(String.valueOf(premiumMemberList.getPremiumCharge()));
                    }else{
                        boolean memberExists = false;
                        for(GymMember member: objectList){
                            if(id_pm_ == member.getId()){
                                memberExists = true;
                            }
                        }
                        if(memberExists){
                            JOptionPane.showMessageDialog(fr,"This ID is already registered in the system.\nPlease verify and try again.","Error",JOptionPane.ERROR_MESSAGE);

                        }else{
                            //creating PremiumMember object
                            PremiumMember premiumMemberList = new PremiumMember(id_pm_,name_, location_, phone_, email_, DOB_, 
                                    gender_, membershipStartDate_pm_,trainer_);

                            int userChoice = JOptionPane.showConfirmDialog(fr,"Please confirm that you want to add this member.","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            if(userChoice == JOptionPane.YES_OPTION){
                                //Adding object of Regular member to arraylist
                                objectList.add(premiumMemberList);
                                JOptionPane.showMessageDialog(fr,"Member added sucessfully !","Information",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(fr,"Member was not added!! You may try again.","Information",JOptionPane.INFORMATION_MESSAGE);  
                            }

                            // After adding to objectList
                            premiumPlanPrice.setText(String.valueOf(premiumMemberList.getPremiumCharge()));
                        }
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (ae.getSource() == activateMembership){
            if (id.getText().isEmpty()){
                JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            try{
                int id_ = Integer.parseInt(id.getText());
                boolean memberExists = false;
                //declaring variable memberFound to hold a reference to GymMember 
                GymMember regularMemberFound = null;
                for(GymMember member: objectList){ 
                    /*member is an instance of GymMember 
                    and can call public method of that class*/
                    if(id_ == member.getId()){
                        memberExists = true;
                        regularMemberFound = member;
                    }
                }
                if(memberExists){
                    if (regularMemberFound instanceof RegularMember){
                        if(regularMemberFound.getActiveStatus()){
                            JOptionPane.showMessageDialog(fr,"Membership Aldready Activatd.","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            regularMemberFound.activateMembership();
                            JOptionPane.showMessageDialog(fr,"Membership Activate.","Information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(fr,"This ID does not belongs to Regular Member.","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if (ae.getSource() == activateMembership_pm){
            if (id_pm.getText().isEmpty()){
                JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            try{
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                GymMember premiumMemberFound = null;
                for(GymMember member: objectList){ 
                    /*member is an instance of GymMember 
                    and can call public method of that class*/
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;
                    }
                }
                if (memberExists) {
                    if (premiumMemberFound instanceof PremiumMember) {
                        if (premiumMemberFound.getActiveStatus()) {
                            JOptionPane.showMessageDialog(fr, "Membership Already Activated.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            premiumMemberFound.activateMembership();
                            JOptionPane.showMessageDialog(fr, "Membership Activated.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        PremiumMember pm = (PremiumMember) premiumMemberFound; 
                        premiumPlanPrice.setText(String.valueOf(pm.getPremiumCharge()));
                    } else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(fr, "ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fr, "Please enter a valid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == deactivateMembership){

            if (id.getText().isEmpty()){
                JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            try{
                int id_ = Integer.parseInt(id.getText());
                boolean memberExists = false;
                GymMember regularMemberFound = null;
                for(GymMember member: objectList){
                    if(id_ == member.getId()){
                        memberExists = true;
                        regularMemberFound = member;
                    }
                }
                if(memberExists){
                    if (regularMemberFound instanceof RegularMember){
                        if(regularMemberFound.getActiveStatus()){
                            regularMemberFound.deactivateMembership();
                            JOptionPane.showMessageDialog(fr,"Membership Deactivated.","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(fr,"Can't Dectivatd membership \n Membership should be activatef firstly.",
                                "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(fr,"This ID does not belongs to Regular Member.","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == deactivateMembership_pm){
            if (id_pm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                GymMember premiumMemberFound = null;
                for(GymMember member: objectList){
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;
                    }
                }
                if(memberExists){
                    if (premiumMemberFound instanceof PremiumMember) {
                        if(premiumMemberFound.getActiveStatus()){
                            premiumMemberFound.deactivateMembership();
                            JOptionPane.showMessageDialog(fr,"Membership Deactivated.","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(fr,"Can't Dectivatd membership \n Membership should be activatef firstly.",
                                "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == markAttendance){
            if (id.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                int id_ = Integer.parseInt(id.getText());
                boolean memberExists = false;
                
                GymMember regularMemberFound = null;
                for(GymMember member: objectList){
                    if(id_ == member.getId()){
                        memberExists = true;
                        regularMemberFound = member;
                    }
                }
                if(memberExists){
                    if (regularMemberFound instanceof RegularMember){
                        if(regularMemberFound.getActiveStatus()){
                            regularMemberFound.markAttendance();
                            JOptionPane.showMessageDialog(fr,"Attendance marked.\n Attendance: "+ regularMemberFound.getAttendance(),
                                "Attendance", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(fr,"Membership Status not activated!!",
                                "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(fr,"This ID does not belongs to Regular Member.","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if (ae.getSource() == markAttendance_pm){
            if (id_pm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

            }
            try{
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                
                GymMember premiumMemberFound = null;
                for(GymMember member: objectList){
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;
                    }
                }
                if(memberExists){
                    if (premiumMemberFound instanceof PremiumMember) {
                        if(premiumMemberFound.getActiveStatus()){
                            premiumMemberFound.markAttendance();
                            JOptionPane.showMessageDialog(fr,"Attendance marked.\n Attendance: "+ premiumMemberFound.getAttendance(),
                                "Attendance", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(fr,"Membership Status not activated!!",
                                "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if (ae.getSource() == revertMember){
            if (id.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            try{
                int id_ = Integer.parseInt(id.getText());
                if (reason_.isEmpty()) {
                    JOptionPane.showMessageDialog(fr,"Please fill the removal reason.","Error",JOptionPane.ERROR_MESSAGE); 
                }
                
                else{
                    boolean memberExists = false;
                    GymMember regularMemberFound = null;
                    for(GymMember member: objectList){
                        if(id_ == member.getId()){
                            memberExists = true;
                            regularMemberFound = member;;
                        }
                    }
                    if(memberExists){
                        if (regularMemberFound instanceof RegularMember){
                            RegularMember rm = (RegularMember) regularMemberFound;
                            rm.revertRegularMember(removalReason.getText());
                            JOptionPane.showMessageDialog(fr,"Membership Reverted !!!.","Information",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(fr,"This ID does not belongs to Regular Member.","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                    }
                } 
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == revertMember_pm){
            if (id_pm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                GymMember premiumMemberFound = null;
                
                for(GymMember member: objectList){
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;;
                    }
                }
                if(memberExists){
                    if (premiumMemberFound instanceof PremiumMember) {
                        premiumMemberFound.resetMember();
                        PremiumMember pm = (PremiumMember) premiumMemberFound;
                        pm.revertPremiumMember();
                        JOptionPane.showMessageDialog(fr,"Membership Reverted .","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);

                } 
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == upgradePlan){
            if (id.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                int id_ = Integer.parseInt(id.getText());
                boolean memberExists = false;
                
                GymMember regularMemberFound = null;
                for(GymMember member: objectList){
                    if(id_ == member.getId()){
                        memberExists = true;
                        regularMemberFound = member;
                    }
                }
                if(memberExists){
                    if (regularMemberFound instanceof RegularMember){
                        RegularMember rm = (RegularMember) regularMemberFound; 
                        if(rm.activeStatus){
                            String message=  rm.upgradePlan(String.valueOf(planComboBox.getSelectedItem()));
                            JOptionPane.showMessageDialog(fr,message,
                                "Attendance", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(fr,"Membership Status not activated!!",
                                "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(fr,"This ID does not belongs to Regular Member.","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }    
        }
        else if (ae.getSource() == calculateDiscount){
            if (id_pm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                GymMember premiumMemberFound = null;
                
                for(GymMember member: objectList){
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;
                    }
                }
                if(memberExists){
                    if (premiumMemberFound instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) premiumMemberFound;
                        pm.calculateDiscount();
                        discountAmount.setText(String.valueOf(pm.getDiscountAmount()));
                        JOptionPane.showMessageDialog(fr,"Congratulations !! \nYou Received Discount Amount of: Rs."+pm.getDiscountAmount(),"Information",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);

                } 
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == payDueAmount){
            if (id_pm.getText().isEmpty()){
                    JOptionPane.showMessageDialog(fr, "Please enter Member ID !!.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }
            try{
                int id_pm_ = Integer.parseInt(id_pm.getText());
                boolean memberExists = false;
                
                GymMember premiumMemberFound = null;
                for(GymMember member: objectList){
                    if(id_pm_ == member.getId()){
                        memberExists = true;
                        premiumMemberFound = member;;
                    }
                }
                if(memberExists){
                    if (premiumMemberFound instanceof PremiumMember) {
                        if(paidAmount.getText().isEmpty()){
                            JOptionPane.showMessageDialog(fr, "Please enter amount to Pay.", "Warning", JOptionPane.WARNING_MESSAGE);
                        }else{
                            PremiumMember pm = (PremiumMember) premiumMemberFound;

                            JOptionPane.showMessageDialog(fr,pm.payDueAmount(Double.parseDouble(paidAmount.getText())),"Information",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(fr, "This ID does not belong to Premium Member.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(fr,"Can't found ID .","Error",JOptionPane.ERROR_MESSAGE);

                } 
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(fr,"Please enter in valid format.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == planComboBox) {

            String selectedPlan = (String) planComboBox.getSelectedItem();

            // Retrieve the price of the selected plan using the RegularMember instance
            double planPrice = RegularMember.getPlanPrice(selectedPlan);

            // Set the plan price in the appropriate label or field
            regularPlanPrice.setText(String.valueOf(planPrice));

        }
        else if (ae.getSource() == clear){
            id.setText("");
            name.setText("");
            location.setText("");
            phone.setText("");
            email.setText("");
            referralSource.setText("");
            removalReason.setText("");
            displayMembers.setText("");

            male.setSelected(true);
            female.setSelected(false);
            others.setSelected(false);

            dobMonthComboBox.setSelectedIndex(0);
            dobDayComboBox.setSelectedIndex(0);
            dobYearComboBox.setSelectedIndex(0);

            msMonthComboBox.setSelectedIndex(0);
            msDayComboBox.setSelectedIndex(0);
            msYearComboBox.setSelectedIndex(0); 
            planComboBox.setSelectedIndex(0);
        }
        else if (ae.getSource() == clear_pm){
            id_pm.setText("");
            name.setText("");
            location.setText("");
            phone.setText("");
            email.setText("");
            trainerName.setText("");
            paidAmount.setText("");
            displayMembers.setText("");

            male.setSelected(true);
            female.setSelected(false);
            others.setSelected(false);

            dobMonthComboBox.setSelectedIndex(0);
            dobDayComboBox.setSelectedIndex(0);
            dobYearComboBox.setSelectedIndex(0);

            msMonth_pm.setSelectedIndex(0);
            msDay_pm.setSelectedIndex(0);
            msYear_pm.setSelectedIndex(0);               
        }
        else if (ae.getSource() == display) {
            if (objectList.isEmpty()) {
                JOptionPane.showMessageDialog(fr, "There is nothing to display.", "Error", JOptionPane.ERROR_MESSAGE);
                displayMembers.setText("No members to display.");
            } else {
                // Initialize display text with header
                String displayHeader = String.format(
                        "%-3s %-16s %-8s %-15s %-12s %-8s %-12s %-12s %-25s %-5s %-6s %-9s %8s %8s     %8s   %-16s %-15s %-20s\n",
                        "ID", "Name", "Plan", "Location", "DOB", "Gender", "Start Date", "Phone", "Email",
                        "Attd", "Pts", "Status", "Price", "Paid","Discount", "Trainer", "RefSource", "Removal"
                    );
                displayHeader += "-".repeat(230)+"\n";

                // Each member's details in one line
                for (GymMember member : objectList) {
                    displayHeader += toFormattedString(member) + "\n";
                    member.display(); // console output
                }

                displayMembers.setText(displayHeader);
            }
        }
        else if( ae.getSource() == saveToFile ){
            File file = new File("MemberDetails.txt");
            FileWriter writer = null;
            try{

                writer  = new FileWriter(file);
                String displayHeader = String.format(
                        "%-3s %-16s %-8s %-15s %-12s %-8s %-12s %-12s %-25s %-5s %-6s %-9s %8s %8s     %8s   %-16s %-15s %-20s\n",
                        "ID", "Name", "Plan", "Location", "DOB", "Gender", "Start Date", "Phone", "Email",
                        "Attd", "Pts", "Status", "Price", "Paid","Discount", "Trainer", "RefSource", "Removal"
                    );
                writer.write(displayHeader);

                // Write the separator
                writer.write("-".repeat(230) + "\n");

                // Write each member's info
                for (GymMember member : objectList) {
                    writer.write(toFormattedString(member) + "\n");
                }

                JOptionPane.showMessageDialog(fr, "Member details saved to MemberDetails.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e){
                JOptionPane.showMessageDialog(fr, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } finally{
                try {
                    if (writer != null){ 
                        writer.close();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(fr, "Error closing file writer: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                }               
            }
        }
        else if(ae.getSource() == saveToFile_pm){
            File file = new File("MemberDetails.txt");
            FileWriter writer = null;
            try{

                writer  = new FileWriter(file);
                String displayHeader = String.format(
                        "%-3s %-16s %-8s %-15s %-12s %-8s %-12s %-12s %-25s %-5s %-6s %-9s %8s %8s     %8s   %-16s %-15s %-20s\n",
                        "ID", "Name", "Plan", "Location", "DOB", "Gender", "Start Date", "Phone", "Email",
                        "Attd", "Pts", "Status", "Price", "Paid","Discount", "Trainer", "RefSource", "Removal"
                    );
                writer.write(displayHeader);

                // Write the separator
                writer.write("-".repeat(230) + "\n");

                // Write each member's info
                for (GymMember member : objectList) {
                    writer.write(toFormattedString(member) + "\n");
                }

                JOptionPane.showMessageDialog(fr, "Member details saved to MemberDetails.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e){
                JOptionPane.showMessageDialog(fr, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } finally{
                try {
                    if (writer != null){ 
                        writer.close();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(fr, "Error closing file writer: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                }               
            }
        }
        else if (ae.getSource() == readFromFile ) {
            File file = new File("MemberDetails.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(fr, "File not found: MemberDetails.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FileReader reader = null;
            try {
                reader = new FileReader(file);
                String content = "";
                int ch;

                while ((ch = reader.read()) != -1) {
                    content += (char) ch;
                }

                displayFrame = new JFrame("Member Details (From File)");
                displayFrame.setSize(1350, 600);

                area = new JTextArea();
                area.setText(content);
                area.setFont(new Font("Monospaced", Font.PLAIN, 12));
                area.setEditable(false);

                scrollPane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                displayFrame.add(scrollPane);
                displayFrame.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(fr, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(fr, "Error closing file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if ( ae.getSource() == readFromFile_pm) {
            File file = new File("MemberDetails.txt");

            if (!file.exists()) {
                JOptionPane.showMessageDialog(fr, "File not found: MemberDetails.txt", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FileReader reader = null;
            try {
                reader = new FileReader(file);
                String content = "";
                int ch;

                while ((ch = reader.read()) != -1) {
                    content += (char) ch;
                }

                displayFrame = new JFrame("Member Details (From File)");
                displayFrame.setSize(1350, 600);

                area = new JTextArea();
                area.setText(content);
                area.setFont(new Font("Monospaced", Font.PLAIN, 12));
                area.setEditable(false);

                scrollPane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                displayFrame.add(scrollPane);
                displayFrame.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(fr, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(fr, "Error closing file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            System.exit(0);
        }// end for main if
    }

    private String toFormattedString(GymMember m) {
        String id     = String.valueOf(m.getId());
        String name   = m.getName().toUpperCase();
        String plan;
        String loc    = m.getLocation().toUpperCase();
        String dob    = m.getDOB();
        String gender = m.getGender().toUpperCase();
        String start  = m.getMembershipStartDate();
        String phone  = m.getPhone();
        String email  = m.getEmail();
        int    attd   = m.getAttendance();
        double pts    = m.getLoyaltyPoints();
        String status = m.getActiveStatus() ? "ACTIVE" : "INACTIVE"; // Using Ternary Operator

        double price, paid;
        String trainer, refSrc, removal,discount;

        if (m instanceof RegularMember) {
            RegularMember r = (RegularMember) m;
            price   = r.getPrice();
            paid    = r.getPlanPrice(r.getPlan());
            trainer = "N/A";
            plan = r.getPlan().toUpperCase();
            discount = "N/A";
            refSrc  = r.getReferralSource().toUpperCase();
            removal = r.getRemovalReason() != null ? r.getRemovalReason().toUpperCase() : "N/A";
        } else {
            PremiumMember p = (PremiumMember) m;
            price   = p.getPremiumCharge();
            paid    = p.getPaidAmount();
            plan = "PREMIUM";
            discount = String.valueOf(p.getDiscountAmount());
            trainer = p.getPersonalTrainer().toUpperCase();
            refSrc  = "N/A";
            removal = "N/A";
        }

        return String.format(
            "%-3s %-16s %-8s %-15s %-12s %-8s %-12s %-12s %-25s %-5d %-6.1f %-9s   %8.2f  %8.2f  %-8s   %-16s %-15s %-20s",
            id, name, plan, loc,  gender,dob, start, phone, email,
            attd, pts, status, price, paid,discount, trainer, refSrc, removal
        );
    }

    public static void main(String Args[]){
        new GymGUI();
    }

}
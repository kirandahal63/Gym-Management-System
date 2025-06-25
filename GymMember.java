/*GymMember is a parent/base class for both RegularMember and PremiumMember 
 demonstrating Hierachical Inheritance.
 It is an abstract class which cannot be instantiated.*/
public abstract class GymMember{
    //declaring instance variables with protected access modifier 
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    // This is a parameterized constructor of parent class
    public GymMember(int id,String name,String location, String phone,String email,String gender,String DOB,String membershipStartDate){
        //initializing instance variables with parameter values
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        //initializing instance variables with default values
        this.attendance = 0;
        this.loyaltyPoints = 0.0d;
        this.activeStatus = false;     
     
    }
    //defining accessor method for each instance variable/attribute to access the value
    public int getId(){
        return this.id;          
    }
    public String getName(){
        return this.name;        
    }
    public String getLocation(){
        return this.location;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
    public String getGender(){
        return this.gender;
    }
    public String getDOB(){
        return this.DOB;
    }
    public String getMembershipStartDate(){
        return this.membershipStartDate;
    }
    public int getAttendance(){
        return this.attendance;
    }
    public double getLoyaltyPoints(){
        return this.loyaltyPoints;
    }
    public boolean getActiveStatus(){
        return this.activeStatus;
    }
    /*
     creating abstract method markAttendance to track attandance of member
     Abstract method must not have body
     */
    public abstract void markAttendance();
    
    /*method to set membership renewed or activated
    return type is set to void because this method does not return any value*/
    public void activateMembership(){
        this.activeStatus = true;
    }
    //method to deactivate membership only if the membership is active
    public void deactivateMembership(){
        if (this.activeStatus == true){
            this.activeStatus = false;
        }
        else{
            System.out.println("Sorry! There is no active membership associated with your account that can be deactivated.");
        }
    }
    //method to reset the plan
    public void resetMember(){
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0.0d;
    }
    //display method to show desired output
    public void display(){
        System.out.println("Client's id is : "+id);
        System.out.println("Client's name is: "+name);
        System.out.println("Client's Location is : "+location);
        System.out.println("Client's Contact number is : "+phone);
        System.out.println("Client's Email : is "+email);
        System.out.println("Client's Gender is : "+gender);
        System.out.println("Client's DOB is : "+DOB);
        System.out.println("Client's  membership started on : "+membershipStartDate);
        System.out.println("Client's Attendance is : "+attendance);
        System.out.println("Client's  current loyalty Point :"+loyaltyPoints);
        System.out.println("Active Status : "+ (activeStatus?"Activated":"Deactivated"));
        
        
    }
}
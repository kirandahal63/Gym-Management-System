//Derived class Regularmembership 
public class RegularMember extends GymMember{
    //declaring instance variables with protected access modifier 
    private final int attendanceLimit; //final keyword ensures that this attribute cannot be changed
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private static double  price;
    
    //paramaterized constructor of this class
    public RegularMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String referralSource){
        //calling parent class constructor 
        super(id,name,location,phone,email,gender,DOB,membershipStartDate);
        //assigning default values 
        this.isEligibleForUpgrade = false;
        this.attendanceLimit = 30;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = "";
        // assigning variables with corresponding parameter values
        this.referralSource =  referralSource;
    }
    //defining accessor method for each instance variable/attribute to access the value
    public int getAttendanceLimit(){
        return this.attendanceLimit;
    }
    public boolean getIsEligibleForUpgrade(){
        return isEligibleForUpgrade;
    }
    public String getRemovalReason(){
        return removalReason;
    }
    public String getReferralSource(){
        return referralSource;
    }
    public String getPlan(){
        return plan;
    }
    public double getPrice(){
        return price;
    }
    //changing inherited abstract method into concrete method by overrriding
    @Override
    public void markAttendance(){
        this.attendance += 1; // increasing attendance by 1
        this.loyaltyPoints += 5; // increasing loyalty points by 5
    }
    // new method called getplanPrice with return-type double is created which accepts plan as parameter 
    public static double getPlanPrice(String plan){
        // switch case is used to return corresponding plan's price from given conditions
        switch(plan.toLowerCase()){
            case "basic":
                return price = 6500;
            case "standard":
                return price = 12500;
            case "deluxe":
                return price = 18500;
            default:
                System.out.println("Invalid Plan!Please choose between basic, standard and deluxe!");
                return -1;
                            
        }
    }
    // another method upgradePlan() is defined to upgrade plan 
    public String upgradePlan(String plan){
        // use of Nested If statement
        if (getAttendance() >= attendanceLimit){
            // instance variaable isEligibleForUpgrade is set to true
            this.isEligibleForUpgrade = true;
            // price is initialized as a local variable which calls getPlanPrice() method and stores the returned value
            double price = getPlanPrice(plan);
            if (this.plan.equalsIgnoreCase(plan)){
                System.out.println("Client is currently subscribed to this plan!");
                return "You are aldready Subscribed to "+plan+ " plan!";
            }
            else if(price == -1){
                return "Sorry! We do not offer this plan at the moment.";
            }else{
                this.plan = plan;
                this.price = price; 
                return "Congratulations! You have been upgraded to "+ plan+" plan.";
            } 
        }
        else{
            return  "Not eligible to Upgrade Plan!";
        }
    }
    /* another method called revertRegularMember() is defined which accepts removalReason as parameter.
       This method resets value to default.*/
    public void revertRegularMember(String removalReason){
        //calling base class method
        super.resetMember();
        // assigning attribues of this class with default values
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        // assigning attribute with corresponding parameter value
        this.removalReason = removalReason;
    }
    // overriding display() method of parent class 
    @Override
    //method to display details of Regular Members
    public void display(){
        // calling display method of parent class using super keyword to have same signature 
        super.display();
        //displaying plan and price
        System.out.println("Client is  subscribed to : "+ plan +" plan.");
        System.out.println("Membership fee for the " + plan + " plan is : Rs." + price);
        /*displaying reason for plan removal if given.
         .isBlank() method is used to check both empty values and whitespaces*/ 
        if(this.removalReason.isBlank() == false){
            System.out.println("Reason for Removal Membership is :\n" +removalReason);
        }
        System.out.println("\n");
    }
    }
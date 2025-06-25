//Sub class PremiumMember 
public class PremiumMember extends GymMember{
    //declaring instance variables with private access modifier 
    private final double premiumCharge;//final keyword ensures that this attribute cannot be changed
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    //paramaterized constructor of this class
    public PremiumMember(int id,String name,String location,String phone,String email,String gender,String DOB,String membershipStartDate,String personalTrainer){
        //calling parent class constructor    
        super(id,name,location,phone,email,gender,DOB,membershipStartDate); 
        //assigning default values to attributes
        this.premiumCharge = 50000.0;
        this.paidAmount = 0.0;
        this.isFullPayment = false;
        this.discountAmount = 0.0;
        //Assigning instance variables with corresponding paraaterized variables
        this.personalTrainer = personalTrainer;
    }
    //defining accessor method for each instance variable/attribute to access the value
    public double getPremiumCharge(){
        return premiumCharge;
    }

    public String getPersonalTrainer(){
        return personalTrainer;
    }

    public boolean getIsFullPayment(){
        return isFullPayment;
    }

    public double getPaidAmount(){
        return paidAmount;
    }

    public double getDiscountAmount(){
        return discountAmount;
    }
    //changing inherited abstract method into concrete method by overrriding
    @Override
    public void markAttendance(){
        this.attendance += 1;// increasing attendance by 1
        this.loyaltyPoints += 10;// increasing attendance by 1
    }
    // a new method payDueAmount() with return type String is created 

    public String payDueAmount(double paidAmount) {
        // prevent overpayment
        if (this.isFullPayment == true){
            return "No Dues Remaining";
        }else if (this.paidAmount + paidAmount > premiumCharge) {
            double maximumPayableAmount = premiumCharge - this.paidAmount;
            return "You can only pay Rs. " + maximumPayableAmount;
        }
        // apply payment
        this.paidAmount += paidAmount;

        double remainingAmount = premiumCharge - this.paidAmount;
        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
            System.out.println("Payment Status : Cleared!");
            return "Payment Sucessful!! No Dues Remaining";
        } else {
            this.isFullPayment = false;
            System.out.println("Payment has been successfully made.\nPaid Amount : " + this.paidAmount);
            return "Payment has been successfully made.\nDue Amount : " + remainingAmount;
        }
    }

    // crreating another method calculateDiscount() to calculate discounts
    public void calculateDiscount(){
        if( isFullPayment == true){
            this.discountAmount = 0.1 * premiumCharge;
            System.out.println("Client's Discount Amount is : " + this.discountAmount);
        }
        else{
            System.out.println("Not Eligible for Discount!");
        }
    }

    /* another method called revertPremiumMember() is defined.
    This method resets value to default.*/
    public void revertPremiumMember(){
        //calling super class restMethod()
        super.resetMember();
        // assigning attribues of this class with default values
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0.0;
        this.discountAmount = 0.0;

    }
    // overriding display() method of parent class 
    @Override
    public void display(){
        // calling display method of parent class using super keyword to have same signature
        super.display();
        // displaying other attributes
        System.out.println("Client's Personal Trainer is : " + personalTrainer);
        System.out.println("Paid Amount : "+paidAmount);
        /* Display client has fully paid or not. 
        Use of ternary operator.*/
        System.out.println("Payment Status : "+ (isFullPayment?"All Payment Cleared":"Due Payment"));
        // Instantiating local variable remainingAmount
        double remainingAmount = premiumCharge - this.paidAmount;
        System.out.println("Client's Due Amount to pay is : "+ remainingAmount);
        //display discountAmount if full payment is done
        if( isFullPayment == true){
            System.out.println("Client's Discount Amount is : " + this.discountAmount);
        }
        System.out.println("\n");
    }
}
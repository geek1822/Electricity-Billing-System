package elect_bill;

public class calculateAmount {
    int units,amount;
    calculateAmount(int units){
        this.units=units;
    }
    public void cal(){
        if(units <=100)
            amount= (int)(units*1.20);
        else if(units <= 300)
            amount =(int)(100*1.20+(units-100)*2);
        else if(units > 300)
            amount = (int) (100*1.20+200*2+(units-300)*3);
    }
    public int show(){
        cal();
        return amount;
    }

}

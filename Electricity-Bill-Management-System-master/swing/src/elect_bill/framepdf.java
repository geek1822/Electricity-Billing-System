package elect_bill;

public class framepdf {
    public String list(String d, int i) {
        i=i-d.length();
        for(int j=0;j<i*2;j++)
            d=d+" ";
        return d;
    }

    public String blist(String month, int i) {
        i=i-month.length();
        for(int j=0;j<i*2;j++)
            month=" "+month;
        return month;
    }

    public String title(String comp, int l) {
        int cl=comp.length();
        int space=(l-cl);
        for(int i=0;i<space;i++)
            comp=" "+comp;
        for(int i=0;i<space;i++)
            comp=comp+" ";
        return comp;
    }

    public String frame(String s,String h,int p) {
        String c="";
        for(int i=0;i<p;i++)
            c=c+h;
        c=s+c+s;
        return c;
    }
}

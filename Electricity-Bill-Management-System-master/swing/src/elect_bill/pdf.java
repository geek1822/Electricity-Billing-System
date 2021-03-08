package elect_bill;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class pdf {
    String name ,address,district, pincode, phoneNo, emailId, months ,units;
    int amount,det;
    pdf(String name,int det,String address,String district,String pincode,String phoneNo,String emailId,String months ,String units, int amount){
        this.name=name;
        this.det=det;
        this.address=address;
        this.district=district;
        this.pincode=pincode;
        this.phoneNo=phoneNo;
        this.emailId=emailId;
        this.months=months;
        this.units=units;
        this.amount=amount;
    }
    public void set() throws FileNotFoundException, DocumentException {
        framepdf t=new framepdf();
        String f="C:\\bill_elect\\"+det+"_"+months+".pdf";
        Document document =new Document();
        PdfWriter.getInstance(document,new FileOutputStream(f));
        document.open();
        String fin="FOR "+months+",2020 THE CONSUMED UNITS IS "+units+" AND TOTAL PAYABLE IS "+amount;
        String frame=t.frame("+","-",127);
        String frame1=t.frame("|"," ",154);
        String comp="XYZ Power Limited";
        comp=t.title(comp,78);
        String sub="Electricity Bill for "+months+",2020";
        sub=t.title(sub,84);
        name=" Customer Name : "+name;
        name=t.list(name,50);
        String meter=" Meter No : "+det;
        meter=t.list(meter,49);
        address=" Address : "+address;
        address=t.list(address,52);
        district=" District : "+district;
        district=t.list(district,52);
        pincode=" Pincode : "+pincode;
        pincode=t.list(pincode,50);
        months=t.blist(months,14);
        phoneNo=" Phone No : "+phoneNo;
        phoneNo=t.list(phoneNo,49);
        units=t.blist(units,13);
        emailId=" Email Id : "+emailId;
        emailId=t.list(emailId,51);
        String am= String.valueOf(amount);
        am=t.blist(am,15);
        fin=t.title(fin,77);

        String u=frame+"\n|"+comp+" |\n|"+sub+" |\n"+frame+"\n"+frame1+"\n|"+name+"|| Meter Rent :                                98 |\n" +
                "|"+meter+"  || MCB Rent :                                 42 |\n|"+address+"  || Service Tax :                             112 |\n" +
                "|"+district+"|| GST@9% :                                 48 |\n|"+pincode+"|| Current Month :  "+months+" |\n" +
                "|"+phoneNo+" || Units Consumed : "+units+" |\n|"+emailId+" || Total Payable : "+am+" |\n"+frame1+"\n"+frame+"\n|"+fin+"|\n"+frame;
        Paragraph p=new Paragraph(u);
        document.add(p);
        document.close();
        System.out.println("0000");
    }
}

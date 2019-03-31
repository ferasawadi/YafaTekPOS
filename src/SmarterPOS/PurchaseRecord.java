package SmarterPOS;

import javafx.scene.control.Alert;

public class PurchaseRecord {



   String PBar = "";
   String PUniname;
   String PQTY;
   String PPrice;

    public String getPBar() {
        return PBar;
    }

    public String getPUniname() {
        return PUniname;
    }

    public String getPQTY() {
        return PQTY;
    }

    public String getPPrice() {
        return PPrice;
    }

    public String getPSubtotal() {
        return PSubtotal;
    }

    public String getPDiscount() {
        return PDiscount;
    }

    public String getPTotal() {
        return PTotal;
    }

    String PSubtotal;
   String PDiscount = "0";
   String PTotal;

    public void setPBar(String bar) {
        PBar = bar;
    }

    public void setPUniname(String uniname) {
        PUniname = uniname;
    }

    public void setPQTY(String QTY) {
        this.PQTY = QTY;
        this.PSubtotal = Double.parseDouble(QTY) * Double.parseDouble(this.PPrice)+"";
        this.PTotal =Double.parseDouble(this.PSubtotal.toString()) * (1 - Double.parseDouble(this.PDiscount)/100) +"";

    }

    public void setPPrice(String price) {
        PPrice = price;
        this.PSubtotal = Double.parseDouble(PQTY) * Double.parseDouble(this.PPrice)+"";
        this.PTotal =Double.parseDouble(this.PSubtotal.toString()) * (1 - Double.parseDouble(this.PDiscount)/100) +"";

    }

    public void setPSubtotal(String subtotal) {
        PSubtotal = subtotal;
    }

    public void setPDiscount(String discount) {
        PDiscount = discount;
        this.PSubtotal = Double.parseDouble(PQTY) * Double.parseDouble(this.PPrice)+"";
        this.PTotal =Double.parseDouble(this.PSubtotal.toString()) * (1 - Double.parseDouble(this.PDiscount)/100) +"";

    }



    public PurchaseRecord(String uniname, String QTY, String price, String discount, String bar) {
        try {
            this.PUniname =uniname;
            this.PQTY =  QTY;
            this.PPrice = price;
            this.PSubtotal = Double.parseDouble(QTY) * Double.parseDouble(price)+"";
            this.PDiscount = discount;
            this.PTotal =Double.parseDouble(this.PSubtotal.toString()) * (1 - Double.parseDouble(discount)/100) +"";
            this.PBar = bar;
        }
        catch  (Exception ex) {
            //Alreat Box For Error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("الرجاء التأكد من القيم المدخلة");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }





}

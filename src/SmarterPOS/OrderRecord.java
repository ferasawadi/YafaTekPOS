package SmarterPOS;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

public class OrderRecord {



    public  String Bar;
    public  String Uniname;
    public  String QTY;
    public  String Price;
    public  String Subtotal;
    public  String Discount;
    public  String Total;

    public void setBar(String bar) {
        Bar = bar;
    }

    public void setUniname(String uniname) {
        Uniname = uniname;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
        this.Subtotal = Double.parseDouble(QTY) * Double.parseDouble(this.Price)+"";
        this.Total =Double.parseDouble(this.Subtotal.toString()) * (1 - Double.parseDouble(this.Discount)/100) +"";
    }

    public void setPrice(String price) {
        Price = price;
        this.Subtotal = Double.parseDouble(QTY) * Double.parseDouble(this.Price)+"";
        this.Total =Double.parseDouble(this.Subtotal.toString()) * (1 - Double.parseDouble(this.Discount)/100) +"";
    }

    public void setSubtotal(String subtotal) {
        Subtotal = subtotal;
    }

    public void setDiscount(String discount) {
        Discount = discount;
        this.Subtotal = Double.parseDouble(QTY) * Double.parseDouble(this.Price)+"";
        this.Total =Double.parseDouble(this.Subtotal.toString()) * (1 - Double.parseDouble(this.Discount)/100) +"";
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getBar() {
        return Bar;
    }

    public String getUniname() {
        return Uniname;
    }

    public String getQTY() {
        return QTY;
    }

    public String getPrice() {
        return Price;
    }

    public String getSubtotal() {
        return Subtotal;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getTotal() {
        return Total;
    }

    public OrderRecord(String uniname, String QTY, String price, String discount,String BAR) {
        try {
            this.Uniname =uniname;
            this.QTY =  QTY;
            this.Price = price;
            this.Subtotal = Double.parseDouble(QTY) * Double.parseDouble(price)+"";
            this.Discount = discount;
            this.Total =Double.parseDouble(this.Subtotal.toString()) * (1 - Double.parseDouble(discount)/100) +"";
            this.Bar = BAR;
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

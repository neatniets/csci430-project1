import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {
  private String date;
  private String desc;
  private double amt;
  public Transaction(String date,
                     String desc,
                     double amt) {
    this.date = date;
    this.desc = desc;
    this.amt = amt;
  }

  public String getDate() {
    return date;
  }
  public String getDesc() {
    return desc;
  }
  public double getTotal() {
    return amt;
  }

  public void addItemOrder(String prod_id, int qty, double price_per) {
    double tot = qty * price_per;
    /* round the total to 2 decimal places */
    BigDecimal bd = new BigDecimal(Double.toString(tot));
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    tot = bd.doubleValue();

    desc += prod_id + " x " + qty + " @ $" + price_per + "/unit = $" + tot
            + "\n";

    amt += tot;
    /* round the other total to 2 decimal places */
    bd = new BigDecimal(Double.toString(amt));
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    amt = bd.doubleValue();
  }

  public String toString() {
    return "Date: " + date + "\n"
           + "Total: " + amt + "\n"
           + "Description:\n"
           + desc;
  }
}

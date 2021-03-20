import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.Serializable;
public class Transaction implements Serializable {
  private static final long serialVersionUID = 1L;
  private String date;
  private String desc;
  private Money amt;
  public Transaction(String date,
                     String desc,
                     Money amt) {
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
  public Money getTotal() {
    return amt;
  }

  public void addItemOrder(String prod_id, int qty, Money price_per) {
    Money tot = new Money(qty * price_per.toValue());

    desc += prod_id + " x " + qty + " @ $" + price_per + "/unit = $" + tot
            + "\n";

    amt.add(tot);
  }

  public String toString() {
    return "Date: " + date + "\n"
           + "Total: $" + amt + "\n"
           + "Description:\n"
           + desc;
  }
}

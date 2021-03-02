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
    desc += prod_id + " x " + qty + " @ $" + price_per + "/unit = $" + tot
            + "\n";
    amt += tot;
  }
}

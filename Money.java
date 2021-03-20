import java.text.DecimalFormat;
import java.io.Serializable;
/** A class to help with handling money formatting. */
public class Money implements Serializable {
        private static final long serialVersionUID = 1L;
        private String amt;
        private static DecimalFormat fmt = new DecimalFormat("0.00");

        private static String convert(double amt) {
                return fmt.format(amt);
        }

        public Money() {
                this(0.00);
        }
        public Money(double amt) {
                this.amt = convert(amt);
        }

        public int compareTo(double amt) {
                return compareTo(new Money(amt));
        }
        public int compareTo(Money amt) {
                return this.amt.compareTo(amt.toString());
        }

        public Money add(double amt) {
                return add(new Money(amt));
        }
        public Money add(Money amt) {
                this.amt = convert(this.toValue() + amt.toValue());
                return this;
        }

        public Money sub(double amt) {
                return sub(new Money(amt));
        }
        public Money sub(Money amt) {
                this.amt = convert(this.toValue() - amt.toValue());
                return this;
        }

        public Money mul(double amt) {
                return mul(new Money(amt));
        }
        public Money mul(Money amt) {
                this.amt = convert(this.toValue() * amt.toValue());
                return this;
        }

        public Money div(double amt) {
                return div(new Money(amt));
        }
        public Money div(Money amt) {
                this.amt = convert(this.toValue() / amt.toValue());
                return this;
        }

        public double toValue() {
                return Double.parseDouble(amt);
        }

        public String toString() {
                return amt;
        }
}

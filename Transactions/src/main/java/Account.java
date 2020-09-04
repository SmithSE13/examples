import java.math.BigDecimal;

public class Account
{
    private BigDecimal money;
    private String accNumber;

    private boolean isBlocked = false;

    public Account(String accNumber, double money) {
        this.money = new BigDecimal(money);
        this.accNumber = accNumber;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    protected void subtractMoney(Double money) {
        if (!isBlocked) this.money = this.money.subtract(new BigDecimal(money));
    }

    protected void addMoney(Double money) {
        if (!isBlocked) this.money = this.money.add(new BigDecimal(money));
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

}

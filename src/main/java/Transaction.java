
import java.util.Locale;

/**
 * Đại diện cho một giao dịch.
 */
public class Transaction {
  public static final int TYPE_DEPOSIT_CHECKING = 1;
  public static final int TYPE_WITHDRAW_CHECKING = 2;
  public static final int TYPE_DEPOSIT_SAVINGS = 3;
  public static final int TYPE_WITHDRAW_SAVINGS = 4;

  private int type;
  private double amount;
  private double initialBalance;
  private double finalBalance;

  /**
   * Khởi tạo giao dịch.
   */
  public Transaction(int type, double amount, double initialBalance, double finalBalance) {
    this.type = type;
    this.amount = amount;
    this.initialBalance = initialBalance;
    this.finalBalance = finalBalance;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getInitialBalance() {
    return initialBalance;
  }

  public void setInitialBalance(double initialBalance) {
    this.initialBalance = initialBalance;
  }

  public double getFinalBalance() {
    return finalBalance;
  }

  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  /**
   * Lấy chuỗi mô tả loại giao dịch.
   */
  public static String getTypeString(int typeId) {
    switch (typeId) {
      case TYPE_DEPOSIT_CHECKING:
        return "Nạp tiền vãng lai";
      case TYPE_WITHDRAW_CHECKING:
        return "Rút tiền vãng lai";
      case TYPE_DEPOSIT_SAVINGS:
        return "Nạp tiền tiết kiệm";
      case TYPE_WITHDRAW_SAVINGS:
        return "Rút tiền tiết kiệm";
      default:
        return "Không rõ";
    }
  }

  /**
   * Tóm tắt giao dịch.
   */
  public String getTransactionSummary() {
    String typeStr = getTypeString(type);
    String initialStr = String.format(Locale.US, "%.2f", initialBalance);
    String amountStr = String.format(Locale.US, "%.2f", amount);
    String finalStr = String.format(Locale.US, "%.2f", finalBalance);

    return "- Kiểu giao dịch: " + typeStr
      + ". Số dư ban đầu: $" + initialStr
      + ". Số tiền: $" + amountStr
      + ". Số dư cuối: $" + finalStr + ".";
  }
}
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp này đại diện cho tài khoản nhưng viết Javadoc rất sơ sài và sai format.
 */
public abstract class Account {
  // Vi phạm: Đặt tên hằng số không đúng chuẩn (phải là UPPER_SNAKE_CASE)
  public static final String CHECKING_TYPE = "CHECKING";
  public static final String SAVINGS_TYPE = "SAVINGS";

  // Vi phạm: Tên biến instance bắt đầu bằng dấu gạch dưới hoặc quá ngắn, không rõ nghĩa
  private long accountNumber;
  private double balance;
  protected List<Transaction> list;

  /**
   * Vi phạm: Thụt lề (Indentation) không đồng nhất, không dùng 2 spaces theo chuẩn Google.
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.list = new ArrayList<Transaction>();
  }

  // Vi phạm: Viết hàm trên một dòng, thiếu khoảng trắng giữa các toán tử/ngoặc
  public long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  protected void setBalance(double balance) {
    this.balance = balance;
  }

  public List<Transaction> getTransactionList() {
    return list;
  }

  /**
   * Cập nhật danh sách giao dịch.
   */
  public void setTransactionList(List<Transaction> transactionList) {
    // Vi phạm: Thiếu dấu ngoặc nhọn cho câu lệnh if (mặc dù vẫn chạy đúng)
    if (transactionList == null) {
      this.list = new ArrayList<Transaction>();
    } else {
      this.list = transactionList;
    }
  }

  public abstract void deposit(double amount);

  public abstract void withdraw(double amount);

  protected void doDepositing(double amount) throws InvalidFundingAmountException {
    // Vi phạm: Whitespace quanh toán tử (amount<=0)
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
  }

  protected void doWithdrawing(double amount) throws BankException {
    // Vi phạm: Tung ra Exception quá chung chung thay vì Exception cụ thể
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
  }

  /**
   * Thêm giao dịch vào danh sách.
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      list.add(transaction);
    }
  }

  /**
   * Lấy lịch sử giao dịch.
   */
  public String getTransactionHistory() {
    // Vi phạm: Dòng code quá dài (Line length) và dùng cộng chuỗi trong vòng lặp
    StringBuilder s = new StringBuilder();
    s.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");
    for (int i = 0; i < list.size(); i++) {
      s.append(list.get(i).getTransactionSummary());
      if (i < list.size() - 1) {
        s.append("\n");
      }
    }
    // Vi phạm: In log trực tiếp ra console để debug (Thay vì dùng Logger)
    System.out.println("[DEBUG] Đã lấy lịch sử cho tài khoản: " + accountNumber);
    return s.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account other = (Account) obj;
    return this.accountNumber == other.accountNumber;
  }

  @Override
  public int hashCode() {
    // Vi phạm: Format code lộn xộn
    return (int) (accountNumber ^ (accountNumber >>> 32));
  }
}
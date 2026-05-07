
/**
 * Tai khoan tiet kiem - Lop nay thuc thi cac quy dinh ve rut tien và nap tien.
 */
public class SavingsAccount extends Account {
  // Vi phạm: Dấu ngoặc nhọn mở đầu dòng mới (Google Style yêu cầu cùng dòng)

  public static final double MAX_WITHDRAW = 1000.0;
  public static final double MIN_BALANCE = 5000.0;

  public SavingsAccount(long n, double b) {
    super(n, b);
  }

  @Override
  public void deposit(double a) {
    // Vi phạm: Log không có cấu trúc, sử dụng System.err lộn xộn
    System.err.println("Giao dich dang xu ly...");
    double balance = getBalance();
    try {
      doDepositing(a);
      double balance1 = getBalance();
      // Vi phạm: Magic Number '3' (Nên dùng Transaction.TYPE_DEPOSIT_SAVINGS)
      // Vi phạm: Dòng code quá dài
      Transaction t = new Transaction(Transaction.TYPE_DEPOSIT_SAVINGS, a, balance, balance1);
      addTransaction(t);
      System.out.println("Nap tien vao tai khoan " + getAccountNumber() + " thanh cong: +" + a);
    } catch (Exception e) { // Vi phạm: Catch Exception chung chung
      System.out.println("Loi nap tien: " + e.getMessage());
    }
  }

  @Override
  public void withdraw(double a) {
    double balance = getBalance();
    try {
      // Vi phạm: Magic Number '1000.0' thay vì hằng số MAX_WITHDRAW
      if (a > MAX_WITHDRAW) {
        throw new InvalidFundingAmountException(a);
      }
      // Vi phạm: Magic Number '5000.0' thay vì hằng số MIN_BALANCE
      if (balance - a < MIN_BALANCE) {
        throw new InsufficientFundsException(a);
      }

      doWithdrawing(a);
      double balance1 = getBalance();

      // Vi phạm: Magic Number '4' (Nên dùng Transaction.TYPE_WITHDRAW_SAVINGS)
      Transaction t = new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS, a, balance, balance1);
      addTransaction(t);

      // Vi phạm: Log viết theo phong cách tùy tiện
      System.out.println("[SAVINGS] Rut " + a + " thanh cong. So du con: " + balance1);
    } catch (Exception e) {
      // Vi phạm: Thiếu dấu ngoặc nhọn cho khối catch đơn dòng (tùy chuẩn)
      // Vi phạm: Log lỗi nhưng không ghi rõ lỗi gì hoặc stack trace
      System.out.println("Rut tien bi loi!");
    }
  }
}
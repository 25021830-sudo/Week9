
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.*;

public class Bank {
  private static final Logger logger = LoggerFactory.getLogger(Bank.class);
  private List<Customer> customerList = new ArrayList<>();
  private static final String ID_REGEX = "\\d{9}";

  /**
   * Đọc danh sách khách hàng và tài khoản từ InputStream.
   * Sử dụng logging để hỗ trợ xác minh quy trình CI/CD.
   */
  public void readCustomerList(InputStream inputStream) {
    logger.info("Bắt đầu quy trình đọc danh sách khách hàng...");

    if (inputStream == null) {
      logger.warn("InputStream đầu vào bị null.");
      return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      Customer currentCustomer = null;

      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;

        int lastSpaceIndex = line.lastIndexOf(' ');
        if (lastSpaceIndex <= 0) continue;

        String lastToken = line.substring(lastSpaceIndex + 1).trim();

        if (lastToken.matches(ID_REGEX)) {
          // Xử lý dòng thông tin khách hàng
          String name = line.substring(0, lastSpaceIndex).trim();
          long id = Long.parseLong(lastToken);
          currentCustomer = new Customer(id, name);
          customerList.add(currentCustomer);
          logger.debug("Đã thêm khách hàng mới: {} (ID: {})", name, id);
        } else if (currentCustomer != null) {
          // Xử lý dòng thông tin tài khoản
          parseAccountData(currentCustomer, line);
        }
      }
      logger.info("Hoàn tất đọc dữ liệu. Tổng số khách hàng: {}", customerList.size());
    } catch (IOException e) {
      logger.error("Lỗi I/O khi đọc dữ liệu: {}", e.getMessage());
    }
  }

  private void parseAccountData(Customer customer, String line) {
    String[] parts = line.split("\\s+");
    if (parts.length < 3) return;

    try {
      long accNum = Long.parseLong(parts[0]);
      double balance = Double.parseDouble(parts[2]);
      String type = parts[1];

      if ("CHECKING".equals(type)) {
        customer.addAccount(new CheckingAccount(accNum, balance));
      } else if ("SAVINGS".equals(type)) {
        customer.addAccount(new SavingsAccount(accNum, balance));
      }
      logger.debug("Đã thêm tài khoản {} cho khách hàng ID: {}", type, customer.getIdNumber());
    } catch (NumberFormatException e) {
      logger.error("Lỗi định dạng số tại dòng: {}", line);
    }
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }
}
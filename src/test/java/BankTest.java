import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class BankTest {

  @Test
  public void testReadCustomerListValidData() {
    Bank bank = new Bank();

    String inputData = "Nguyen Van A 123456789\n"
      + "1000001 CHECKING 500.0";
    InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

    bank.readCustomerList(inputStream);

    List<Customer> customers = bank.getCustomerList();
    assertEquals(1, customers.size(), "Phải đọc được 1 khách hàng");
    assertEquals("Nguyen Van A", customers.get(0).getFullName());
    assertEquals(1, customers.get(0).getAccountList().size(), "Khách hàng phải có 1 tài khoản");
  }
}
package workshop;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class CalculatorTest {

  private Calculator calc;
  private DisplayInterface display;

  @Before
  public void setUp() {
    display = mock(DisplayInterface.class);
    calc = new Calculator(display);
    when(display.isAvailable()).thenReturn(true);
  }

  @DataProvider
  public static Object[][] myPositiveNumbers() {
    return new Object[][] {
        {9000, 100,},
        {32000, 123123,},
        {3203300, 12223123,},
    };
  }

  @Test
  public void testSum_NumbersAreNegative_ResultShouldBeNegative() throws EqualNumberException, UnavailableDisplayException {
    // Arrange
    int a = -10;
    int b = -100;

    // Act
    int result = calc.sum(a, b);

    // Assert
    Assert.assertTrue(result < 0);
  }

  @Test
  @UseDataProvider("myPositiveNumbers")
  public void testSum_NumbersArePositive_ResultShouldBePositive(int a, int b) throws EqualNumberException, UnavailableDisplayException {
    // Act
    int result = calc.sum(a, b);

    // Assert
    Assert.assertTrue(result > 0);

  }

  @Test
  public void testSum_NumbersAreOpposite_ResultShouldBeZero() throws EqualNumberException, UnavailableDisplayException {
    // Arrange
    int a = -10;
    int b = 10;

    // Act
    int result = calc.sum(a, b);

    // Assert
    Assert.assertTrue(result  == 0);
  }

  @Test(expected = EqualNumberException.class)
  public void testSum_NumbersAreEqual_ThrowEqualNumberException() throws EqualNumberException, UnavailableDisplayException {
    // Arrange
    int a = 10;
    int b = 10;

    // Act
    calc.sum(a, b);
  }

  @Test(expected = UnavailableDisplayException.class)
  public void testSum_DisplayIsUnavailable_ThrowUnavailableDisplayException() throws EqualNumberException, UnavailableDisplayException {
    // Arrange
    int a = 10;
    int b = 100;
    when(display.isAvailable()).thenReturn(false);

    // Act
    calc.sum(a, b);
  }
}

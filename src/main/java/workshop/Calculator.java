package workshop;

public class Calculator {
  private DisplayInterface display;

  public Calculator(DisplayInterface display) {

    this.display = display;
  }

  public int sum(int a, int b) throws EqualNumberException, UnavailableDisplayException {
    if(a == b) {
      throw new EqualNumberException();
    }
    int result = a + b;
    if(!display.isAvailable()) {
      throw new UnavailableDisplayException();
    }
    display.show(result);
    return result;
  }
}

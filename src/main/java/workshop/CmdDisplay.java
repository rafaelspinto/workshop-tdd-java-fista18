package workshop;

public class CmdDisplay implements DisplayInterface{
  public void show(int result) {
    System.out.println("MY RESULT:"+ result);
  }

  public boolean isAvailable() {
    return false;
  }
}

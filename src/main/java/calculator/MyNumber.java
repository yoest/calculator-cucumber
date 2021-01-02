package calculator;

public class MyNumber implements Expression
{
  private int value;

  public /*constructor*/ MyNumber(int v) {
	  value=v;
	  }

  public Integer compute() {
	  return value;
	  }
  
  public Integer countDepth() {
	  return 0;
  }
  
  public Integer countOps() {
	  return 0;
  }

  public Integer countNbs() {
	  return 1;
  }

  public String toString() {
	  return Integer.toString(value);
	  }
  
}

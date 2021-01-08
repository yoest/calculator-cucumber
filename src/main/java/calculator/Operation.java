package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Operation implements Expression
{  
  private List<Expression> args;
  protected String symbol;
  protected int neutral; // the neutral element of the operation (e.g. 1 for *, 0 for +)
  public Notation notation = Notation.INFIX; //by default, expressions are rendered as strings using infix notation

  // It is not allowed to create operation that have a null list of arguments.
  // Note that it is allowed to have an EMPTY list of arguments.
  public /*constructor*/ Operation(List<Expression> elist)
		  throws IllegalConstruction
	{ 
	  if (elist == null) {
		  throw new IllegalConstruction(); }
	  else {
		  args = new ArrayList<>(elist);
 	  }
    }

  public /*constructor*/ Operation(List<Expression> elist,Notation n)
		  throws IllegalConstruction
  {	
	  if (elist == null) {
		  throw new IllegalConstruction(); }
	  else {
		  notation = n;
		  args = new ArrayList<>(elist);
 	  }
  }
  
  abstract protected int op(int l, int r);
    // the operation itself is specified in the subclasses

  // add more arguments to the existing list of arguments args
  void addMoreParams(List<Expression> params) {
  	args.addAll(params);
  }

  public Integer compute() {
    // use of Java 8 functional programming capabilities
	return args.stream()
			   .mapToInt(Expression::compute)
			   .reduce(this::op)
			   .getAsInt();
    }
  
  final public Integer countDepth() {
	    // use of Java 8 functional programming capabilities
	return 1 + args.stream()
			   .mapToInt(Expression::countDepth)
			   .max()
			   .getAsInt();  
  }

  final public Integer countOps() {
	    // use of Java 8 functional programming capabilities
	return 1 + args.stream()
			   .mapToInt(Expression::countOps)
			   .reduce(Integer::sum)
			   .getAsInt();  
  }

  final public Integer countNbs() {
	    // use of Java 8 functional programming capabilities
	return args.stream()
			   .mapToInt(Expression::countNbs)
			   .reduce(Integer::sum)
			   .getAsInt();  
  }

  @Override
  final public String toString() {
  	return toString(notation);
  }

  final public String toString(Notation n) {
   Stream<String> s = args.stream().map(Object::toString);
   switch (n) {
	   case INFIX: return "( " +
			              s.reduce((s1,s2) -> s1 + " " + symbol + " " + s2).get() +
			              " )";
	   case PREFIX: return symbol + " " +
			               "(" +
			               s.reduce((s1,s2) -> s1 + ", " + s2).get() +
			               ")";
	   case POSTFIX: return "(" +
			                s.reduce((s1,s2) -> s1 + ", " + s2).get() +
			                ")" +
			                " " + symbol;
	   default: return "This case should never occur.";
	  }
  }

}

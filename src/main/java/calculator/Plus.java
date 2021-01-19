package calculator;

import java.util.List;

final public class Plus extends Operation
{

  public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "+";
	neutral = 0;
	}
  
  public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		symbol = "+";
		neutral = 0;
  }

  protected int op(int l, int r) {
  	return (l+r);
  }

  //Two Plus expressions are equal if their list of arguments is equal as well
  @Override
  public boolean equals(Object o) {
	  if (o == null) return false;

	  if (!(o instanceof Plus)) {
			return false;
	  }
	  Plus other = (Plus) o;
	  return this.args.equals(other.getArgs());
	}

  /*TO DO: The method hashCode also needs to be overridden it the equals method is overridden;
	otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
	@Override
	public int hashCode() {
		return super.hashCode();
	}
   */
}

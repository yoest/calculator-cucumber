package calculator;

import java.util.List;

final public class Times extends Operation
{

  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
	super(elist);
	symbol = "*";
	neutral = 1;
	}

  public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		symbol = "*";
		neutral = 1;
		}
  
  protected int op(int l, int r)
    { return (l*r); }

  //Two Times expressions are equal if their list of arguments is equal as well
  @Override
  public boolean equals(Object o) {
	  if (o == null) return false;

	  if (!(o instanceof Times)) {
			return false;
	  }
	  Times other = (Times) o;
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

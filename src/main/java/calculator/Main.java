package calculator;

import memory.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Département d'Informatique
 * Faculté des Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {

		Expression e, f;
		Calculator c = new Calculator();
		//Caretaker caretaker = new Caretaker();
		//MemoryCalculator mc = new MemoryCalculator(caretaker);

		try {
			//int maxSize = 10;
			int number = 5;
			Caretaker tmp = new Caretaker(); //TODO: remove this line

			MemoryCalculator calculator = new MemoryCalculator(tmp);
			Expression expression = new MyNumber(number);

			calculator.save(expression);
			calculator.eval(expression);

			Expression retrieved = calculator.load();
			System.out.println("retrieved: " + retrieved);
			Expression e1 = new MyNumber(2);
			Expression e2 = new MyNumber(3);
			Expression e3 = new MyNumber(4);
			calculator.eval(e1);
			calculator.eval(e2);
			calculator.eval(e3);
			calculator.undo();
			calculator.undo();
			calculator.redo();
			Expression loaded = calculator.load();
			System.out.println("loaded: " + loaded);
			//print history
			calculator.printHistory();
			//tmp.saveHistoryTxt();
			//tmp.display();
			//calculator.clearHistory();
			//mc.setMaxSize(maxSize);
			//caretaker.setHistory(caretaker.deserializeHistory());
			//caretaker.display();
		/*
			e = new MemoryNumber(8);
			mc.eval(e);

			List<Expression> params_ = new ArrayList<>();
			Collections.addAll(params_, new MemoryNumber(3), new MemoryNumber(4), new MemoryNumber(5));
			f = new Plus(params_, Notation.PREFIX);
			//mc.printExpressionDetails(f);
			mc.eval(f);


			//mc.getHistory_();
			caretaker.display();
			System.out.println("Stop");

			mc.undo();
			//mc.getHistory_();
			caretaker.display();
			System.out.println("Stop");

			mc.redo();
			//mc.getHistory_();
			caretaker.display();
			System.out.println("Stop");
			//mc.printExpressionDetails(f);
			//caretaker.serializeHistory();

			/*

                                    mc.printHistory();
                                    System.out.println(mc.getTimeOfAllSaves());
                                    mc.undo();
                                    System.out.println(mc.getTimeOfAllSaves());
                                    mc.undo();
                                    System.out.println(mc.getTimeOfAllSaves());


                                    List<Expression> params_ = new ArrayList<>();
                                    Collections.addAll(params_, new MemoryNumber(3), new MemoryNumber(4), new MemoryNumber(5));
                                    f = new Plus(params_, Notation.PREFIX);
                                    mc.printExpressionDetails(f);
                                    mc.eval(f);


                                    mc.save(f, "fName");
                                    Expression expr = mc.load("fName");
                                    mc.printExpressionDetails(expr);
                                    mc.eval(expr);
                                    System.out.println(mc.eval(expr));
                                    List<Snapshot> hist = mc.getHistory();
                                    // get size of the history
                                    System.out.println(hist.size());
                                    // get the last snapshot
                                    System.out.println("The end");
                                    System.out.println(mc.getTimeOfAllSaves());
                                    mc.printHistory();
                                    mc.undo();
                                    System.out.println(mc.getTimeOfAllSaves());

                                    List<Expression> params = new ArrayList<>();
                                    Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
                                    e = new Plus(params,Notation.PREFIX);
                                    c.printExpressionDetails(e);
                                    c.eval(e);

                                    List<Expression> params2 = new ArrayList<>();
                                    Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
                                    e = new Minus(params2, Notation.INFIX);
                                    c.print(e);
                                    c.eval(e);

                                    List<Expression> params3 = new ArrayList<>();
                                    Collections.addAll(params3, new Plus(params), new Minus(params2));
                                    e = new Times(params3);
                                    c.printExpressionDetails(e);
                                    c.eval(e);

                                    List<Expression> params4 = new ArrayList<>();
                                    Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(5));
                                    e = new Divides(params4,Notation.POSTFIX);
                                    c.print(e);
                                    c.eval(e);
                                }

                                catch(IllegalConstruction exception) {
                                    System.out.println("cannot create operations without parameters");
                                    } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }

                                }

            }*/
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}


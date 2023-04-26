package calculatorParser;
import calculator.*;

import java.util.List;

public class NotationChecker {

    private NotationChecker() {
    }

    public static boolean verifyNotation(Expression e) {
        if (isInfix(e) || isPrefix(e) || isPostfix(e)) {
            return true;
        }
        return false;
    }

    public static boolean verifyNotations(List<Expression> expressions, Notation notation) {
        for (Expression e : expressions) {
            if (notation == Notation.INFIX && !isInfix(e)) {
                    return false;
            }
            if (notation == Notation.PREFIX && !isPrefix(e)) {
                    return false;
            }
            if (notation == Notation.POSTFIX && !isPostfix(e)) {
                    return false;
            }
        }
        return true;
    }
    public static boolean isInfix(Expression e) {
        if (e instanceof Operation) {
            Operation op = (Operation) e;
            Notation notation = op.notation;
            if (notation == Notation.INFIX) {
                for (Expression exp : op.args) {
                    if (!isInfix(exp)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        if (e instanceof calculator.MyNumber || e instanceof MyTime) {
            return true;
        }
        return false;
    }

    public static boolean isPrefix(Expression e) {
        if (e instanceof Operation) {
            Operation op = (Operation) e;
            Notation notation = op.notation;
            if (notation == Notation.PREFIX) {
                for (Expression exp : op.args) {
                    if (!isPrefix(exp)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        if (e instanceof calculator.MyNumber || e instanceof MyTime) {
            return true;
        }
        return false;
    }

    public static boolean isPostfix(Expression e) {
        if (e instanceof Operation) {
            Operation op = (Operation) e;
            Notation notation = op.notation;
            if (notation == Notation.POSTFIX) {
                for (Expression exp : op.args) {
                    if (!isPostfix(exp)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        if (e instanceof calculator.MyNumber || e instanceof MyTime) {
            return true;
        }
        return false;
    }
}


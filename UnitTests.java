package GAPL_project1;

import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;
import org.junit.Test;

public class UnitTests {

	@Test
	public void test() {
		substitution subtest = new substitution();
		subtest.put(GdlPool.getVariable("X"),GdlPool.getConstant("a"));
		subtest.put(GdlPool.getVariable("Y"),GdlPool.getConstant("b"));
		subtest.put(GdlPool.getVariable("V"),GdlPool.getConstant("b"));

		GdlTerm[] terms = new GdlTerm[2];

		terms[0] = GdlPool.getVariable("X");
		terms[1] = GdlPool.getVariable("Y");
		GdlFunction move1 = GdlPool.getFunction(GdlPool.getConstant("move"), terms);

		terms[0] = GdlPool.getConstant("a");
		terms[1] = GdlPool.getVariable("V");
		GdlFunction move2 = GdlPool.getFunction(GdlPool.getConstant("move"), terms);

		terms[0] = GdlPool.getConstant("b");
		terms[1] = GdlPool.getVariable("V");
		GdlFunction move3 = GdlPool.getFunction(GdlPool.getConstant("move"), terms);

		//substitution mgu = Unifier.mgu(move1,move2);
		//assert subtest.equals(mgu);
		//mug = Unifier.mgu(move1,move3);
		//assert !subtest.equals(mgu);

		GdlVariable Y = GdlPool.getVariable("Y");
		GdlVariable V = GdlPool.getVariable("V");

		//mgu = Unifier.mgu(Y,V);
		//assert !subtest.equals(mgu);

	}

}

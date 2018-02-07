package GAPL_project1;

import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;
import org.junit.Test;

public class UnificationTest {

	@Test
	public void test() {
		substitution mgu; // Most general unifier

		// subtest is the substitution {X->a, Y->V}
		substitution subtest = new substitution();
		subtest.put(GdlPool.getVariable("Y"),GdlPool.getVariable("V"));
		subtest.put(GdlPool.getVariable("X"),GdlPool.getConstant("a"));

		GdlTerm[] terms  = new GdlTerm[1];
		GdlTerm[] terms2 = new GdlTerm[2];
		GdlTerm[] terms3 = new GdlTerm[3];


		// *****************************
		// Tests for GdlFunctions

		// GdlFunction move(X,Y)
		terms2[0] = GdlPool.getVariable("X");
		terms2[1] = GdlPool.getVariable("Y");
		GdlFunction move1 = GdlPool.getFunction(GdlPool.getConstant("move"), terms2);

		// GdlFunction move(a,V)
		terms2[0] = GdlPool.getConstant("a");
		terms2[1] = GdlPool.getVariable("V");
		GdlFunction move2 = GdlPool.getFunction(GdlPool.getConstant("move"), terms2);

		// GdlFunction move(b,V)
		terms2[0] = GdlPool.getConstant("b");
		terms2[1] = GdlPool.getVariable("V");
		GdlFunction move3 = GdlPool.getFunction(GdlPool.getConstant("move"), terms2);

		// move(X,Y) and move(a,V) should be unifiable
		mgu = OurUnification.mgu(move1,move2,new substitution());
		assert subtest.equals(mgu);

		// move(a,V) and move(b,V) should not be unifiable
		mgu = OurUnification.mgu(move2,move3,new substitution());
		assert mgu == null;


		// ****************************
		// Tests for GdlVariables

		GdlVariable Y = GdlPool.getVariable("Y");
		GdlVariable V = GdlPool.getVariable("V");

		// mgu for Y and V should be {Y->V} and subtest is {X->a, Y->V}, not equal
		mgu = OurUnification.mgu(Y,V,new substitution());
		assert !subtest.equals(mgu);
		// remove X->a, should be equal now
		subtest.remove(GdlPool.getVariable("X"));
		assert subtest.equals(mgu);


		// ****************************
		// Tests for GdlRelations

		// next(F) and next(cell(1,2,b)) should have a unifier {F->cell(1,2,b)}
		terms3[0] = GdlPool.getConstant("1");
		terms3[1] = GdlPool.getConstant("2");
		terms3[2] = GdlPool.getConstant("b");
		GdlFunction cell = GdlPool.getFunction(GdlPool.getConstant("cell"), terms3);
		terms[0] = cell;
		GdlRelation rel1 = GdlPool.getRelation(GdlPool.getConstant("next"), terms);
		terms[0] = GdlPool.getVariable("F");
		GdlRelation rel2 = GdlPool.getRelation(GdlPool.getConstant("next"), terms);

		mgu = OurUnification.mgu(rel1, rel2, new substitution());
		assert mgu.toString().equals("{F->( cell 1 2 b )}");
	}

}
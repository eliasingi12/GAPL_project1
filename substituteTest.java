package GAPL_project1;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlConstant;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;
import org.junit.Test;

public class substituteTest {

	@Test
	public void test() {

		substitution testvar = new substitution();
		testvar.put(GdlPool.getVariable("X"),GdlPool.getConstant("a"));
		GdlVariable VarExpression = GdlPool.getVariable("X");
		Gdl result = Prover.substitute(VarExpression, testvar);
		// System.out.println(result);
		assert ((GdlConstant) result).getValue() == "a"; // works
		assert !(((GdlConstant) result).getValue() == "b"); // "fails"

		GdlTerm[] terms = new GdlTerm[3];
		terms[0] = GdlPool.getVariable("X");
		terms[1] = GdlPool.getVariable("Y");
		terms[2] = GdlPool.getConstant("b");

		GdlFunction cell = GdlPool.getFunction(GdlPool.getConstant("cell"), terms);

		substitution testfunc = new substitution();
		testfunc.put(GdlPool.getVariable("X"),GdlPool.getConstant("1"));
		testfunc.put(GdlPool.getVariable("Y"),GdlPool.getConstant("2"));

		Gdl funcresult = Prover.substitute(cell, testfunc);
		// System.out.println(((GdlFunction) funcresult).getName());
		// System.out.println(((GdlFunction) funcresult).get(0));
		assert ((((GdlFunction) funcresult).getName()).toString() == "cell"); // works
		assert !((((GdlFunction) funcresult).getName()).toString() == "control"); // "fails"
		assert ((((GdlFunction) funcresult).get(0)).toString() == "1"); // works
		assert ((((GdlFunction) funcresult).get(1)).toString() == "2"); // works
		assert !((((GdlFunction) funcresult).get(1)).toString() == "3"); // "fails"
		assert ((((GdlFunction) funcresult).get(2)).toString() == "b"); // works


		// testing relation --- next(cell(X,Y,b)) with substitution X->5, Y->10
		GdlTerm[] relbody = new GdlTerm[4];
		relbody[0] = GdlPool.getFunction(GdlPool.getConstant("cell"));
		relbody[1] = GdlPool.getVariable("X");
		relbody[2] = GdlPool.getVariable("Y");
		relbody[3] = GdlPool.getConstant("b");

		substitution testrel = new substitution();
		testrel.put(GdlPool.getVariable("X"),GdlPool.getConstant("5"));
		testrel.put(GdlPool.getVariable("Y"),GdlPool.getConstant("10"));

		GdlRelation nextcell = GdlPool.getRelation(GdlPool.getConstant("next"), relbody);
		Gdl relresult = Prover.substitute(nextcell, testrel);
		System.out.println(relresult);







	}



}

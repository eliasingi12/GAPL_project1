package GAPL_project1;

import java.util.ArrayList;
import java.util.List;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlDistinct;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlLiteral;
import org.ggp.base.util.gdl.grammar.GdlNot;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.gdl.grammar.GdlSentence;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class Prover {

	Prover() {
		// Nothing here
	}

	public static Gdl substitute(Gdl expression, substitution theta) {
		// Applies the substitution theta to expression and returns a Gdl object

		if (expression instanceof GdlTerm) {
			if (expression instanceof GdlVariable) {
				GdlVariable var = (GdlVariable) expression;
				GdlTerm obj = theta.get(var);
				if (obj != null) {return obj;}
			} else if (expression instanceof GdlFunction) {
				List<GdlTerm> terms = ((GdlFunction) expression).getBody();
				List<GdlTerm> body = new ArrayList<GdlTerm>();
				for (GdlTerm t : terms) {
					// We recursively call substitute on the function's body
					body.add((GdlTerm) substitute((Gdl) t, theta));
				}
				// We have substituted the body with substitution theta
				return GdlPool.getFunction(((GdlFunction) expression).getName(), body);
			}
			// expression is a constant so there is nothing to substitute!
			return expression;
		}

		else if (expression instanceof GdlLiteral) {
			if (expression instanceof GdlSentence) {
				if (expression instanceof GdlRelation) {
					List<GdlTerm> terms = ((GdlRelation) expression).getBody();
					List<GdlTerm> body = new ArrayList<GdlTerm>();
					for (GdlTerm t : terms) {
						// We recursively call substitute on the relation's body
						body.add((GdlTerm) substitute((Gdl) t, theta));
					}
					return GdlPool.getRelation(((GdlRelation) expression).getName(), body);
				}
				// expression is a proposition so there is nothing to substitute!
				return expression;
			} else if (expression instanceof GdlDistinct) {
				GdlTerm arg1 = ((GdlDistinct) expression).getArg1();
				GdlTerm arg2 = ((GdlDistinct) expression).getArg2();
				GdlTerm subarg1 = (GdlTerm) substitute((Gdl) arg1, theta);
				GdlTerm subarg2 = (GdlTerm) substitute((Gdl) arg2, theta);
				return GdlPool.getDistinct(subarg1, subarg2);
			} else if (expression instanceof GdlNot) {
				GdlLiteral body = ((GdlNot) expression).getBody();
				return GdlPool.getNot((GdlLiteral) substitute((Gdl) body, theta));
			}
		}
		return expression;
	}
}

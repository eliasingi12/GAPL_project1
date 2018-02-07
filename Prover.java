package GAPL_project1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlDistinct;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlLiteral;
import org.ggp.base.util.gdl.grammar.GdlNot;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.gdl.grammar.GdlRule;
import org.ggp.base.util.gdl.grammar.GdlSentence;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class Prover {

	Prover() {
		// Nothing here
	}

	public static List<substitution> prove(LinkedList<GdlLiteral> query, List<Gdl> rules, substitution answersub) {
		if (query.isEmpty()) {
			List<substitution> answers = new ArrayList<substitution>();
			answers.add(answersub);
			return answers;}
		// TODO: If Query is empty, then answers.add(s); ???
		GdlLiteral literal = query.pop();
		if (literal instanceof GdlNot) {

		}
		if (literal instanceof GdlDistinct) {

		} else if (literal instanceof GdlSentence) {
			Gdl head;
			List<GdlLiteral> body;
			for (Gdl r : rules) {
				if (r instanceof GdlSentence) {
					head = ((GdlSentence) r).getName();
					body = null;
					System.out.print("The head: " + ((GdlSentence) r).getName() + " And body: " + ((GdlSentence) r).getBody() + "\n");
				}
				else
				{
					head = ((GdlRule) r).getHead();
					body = ((GdlRule) r).getBody();
				}
				answersub = OurUnification.mgu(literal, head, answersub);
				if (answersub == null) continue;
				else {
					LinkedList<GdlLiteral> query2 = new LinkedList<GdlLiteral>(query);
					query2.addAll(body);
					return prove(query2, rules, answersub);
				}
			}
			// ((GdlRule) r).getHead();
		}
		// System.out.print("Query: \n" + query + "\n");
		// System.out.print("rules: \n" + rules + "\n");

		// TODO: if (l = not p) then if (prove(p,rules,{}) == null) prove(query - l, rules, s)
		// TODO: if l = distinct(x,y) ...
		// TODO: else {} // Here l is a instance of GdlSentence
		// for r in rules do
		// if r is GdlSentence head = r; body = {}
		// else ((GdlRule r).getHead(); Body = r.getBody()
		// s = mgu(l, head, s)
		// if (s == null) continue
		// else query2 = query-l + body
		// 		prove(query2,rules,s)
		// return context;
		return null;
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

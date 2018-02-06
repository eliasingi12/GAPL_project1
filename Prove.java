package GAPL_project1;

import java.util.HashSet;
import java.util.Set;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlLiteral;

import scala.collection.immutable.List;

public class Prove {
	// answers should be a new set of items
	Set<String> answers = new HashSet<String>();

	substitution s = new substitution();

	public Prove(List<GdlLiteral> query, List<Gdl> rules, substitution s) {

		// TODO: If Query is empty, then answers.add(s);
		// l = query.pop();
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
	}

}

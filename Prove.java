package GAPL_project1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlLiteral;

public class Prove {
	// answers should be a new set of items
	Set<String> answers = new HashSet<String>();

	// substitution s = new substitution();

	public Prove(LinkedList<GdlLiteral> query, List<Gdl> rules, List s) {
	// private Set<GdlSentence> Prove(GdlSentence query, Set<GdlSentence> context, boolean askOne) {
		// System.out.print("Query: \n" + query + "\n");
		// TODO: If Query is empty, then answers.add(s);
		System.out.print("Hello list: \n" + s + "\n");
		// GdlLiteral l = query.pop();
		// System.out.print("Query: \n" + query.pop() + "\n");
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
	}

}

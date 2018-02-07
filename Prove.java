package GAPL_project1;

import java.util.LinkedList;
import java.util.List;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlLiteral;
import org.ggp.base.util.gdl.grammar.GdlSentence;

public class Prove {

	public static List<substitution> prove(LinkedList<GdlLiteral> query, List<Gdl> rules, LinkedList<substitution> extrasub) {
	// private Set<GdlSentence> Prove(GdlSentence query, Set<GdlSentence> context, boolean askOne) {
		// TODO: If Query is empty, then answers.add(s);
		// System.out.print("Query: \n" + query + "\n");
		// System.out.print("Hello gameDescr: \n" + gameDescr + "\n");
		// System.out.print("Hello list: \n" + extrasub + "\n");
		GdlLiteral literal = query.pop();
		// System.out.print("query.pop(): \n" + literal + "\n");
		// System.out.print("query.pop(): \n" + (literal instanceof GdlSentence) + "\n");
		if (literal instanceof GdlSentence) {
			for (Gdl r : rules) {
				if (r instanceof GdlSentence) {

					System.out.print("The head: " + ((GdlSentence) r).getName() + " And body: " + ((GdlSentence) r).getBody() + "\n");
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

}

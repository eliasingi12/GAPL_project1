package GAPL_project1;

import java.util.ArrayList;
import java.util.List;

import org.ggp.base.util.gdl.grammar.GdlConstant;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class Prover {

	Prover() {
		//Nothing here
	}

	public static GdlTerm substitude(GdlTerm term, substitution theta) {
		if (term instanceof GdlVariable) {
			GdlVariable var = (GdlVariable) term;
			GdlTerm obj = theta.get(var);
			if (obj != null) {return obj;}
		}
		if (term instanceof GdlFunction) {
			List<GdlTerm> terms = ((GdlFunction) term).getBody();
			List<GdlTerm> body = new ArrayList<GdlTerm>();
			for (GdlTerm t : terms) {
				if (t instanceof GdlConstant) {
					body.add(t);
				}
				else if (t instanceof GdlVariable) {
					GdlVariable v = (GdlVariable) t;
					body.add(theta.get(v));
				}
				else if (t instanceof GdlFunction) {
					GdlFunction f = (GdlFunction) t;
					body.add(substitude(f, theta));
				}
			}
			GdlFunction f = (GdlFunction) term;
			GdlFunction newFun = GdlPool.getFunction(f.getName(), body);

			return newFun;
		}
		return term;
	}
}

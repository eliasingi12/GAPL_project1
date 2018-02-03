package GAPL_project1;

import java.util.HashMap;

import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class substitution {

	private HashMap<GdlVariable, GdlTerm> submap;

	public substitution() {
		submap = new HashMap<GdlVariable, GdlTerm>();
	}

	public void put(GdlVariable var, GdlTerm term) {
		submap.put(var, term);
	}

	public GdlTerm get(GdlVariable var) {
		return submap.get(var);
	}
}

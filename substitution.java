package GAPL_project1;

import java.util.HashMap;
import java.util.Set;

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

	public void remove(GdlVariable var) {
		this.submap.remove(var);
	}

	public boolean contains(GdlVariable var)
    {
        return submap.containsKey(var);
    }

	public GdlVariable[] getKeySet() {
		Set<GdlVariable> keys = this.submap.keySet();
		int s = keys.size();
		return keys.toArray(new GdlVariable[s]);
	}

	public boolean equals(substitution s) {
		GdlVariable[] keys = s.getKeySet();
		GdlVariable key;
		for (int i = 0; i < keys.length; i++) {
			key = keys[i];
			if (!submap.containsKey(key)) {return false;}
			if (submap.get(key).toString() != s.get(key).toString()) {return false;}
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "{";
		GdlVariable[] keys = this.getKeySet();
		for (int i = 0; i < keys.length; i++) {
			str = str + keys[i].toString() + "->" + this.get(keys[i]).toString()+", ";
		}
		return str + "}";
	}
}

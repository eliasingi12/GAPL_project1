package GAPL_project1;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlConstant;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class OurUnification {

	public static substitution sigma = new substitution();

	public static substitution mgu(Gdl x, Gdl y, substitution sigma)
	{
		if(x.equals(y))
            return sigma;
		if (x instanceof GdlVariable)
			return mguvar((GdlVariable) x, (GdlTerm) y, sigma);
		if (y instanceof GdlTerm)
			return mguvar((GdlVariable) y, (GdlTerm) x, sigma);

		if ((x instanceof GdlConstant) && (y instanceof GdlConstant))
        {
            if (!x.equals(y))
            {
                return null;
            }
        }

		else if ((x instanceof GdlFunction) && (y instanceof GdlFunction))
        {
            GdlFunction xFunction = (GdlFunction) x;
            GdlFunction yFunction = (GdlFunction) y;

            // function names don't match
            if(xFunction.getName() != yFunction.getName())
                return null;
                // function arguments don't match ? Number of arguments or parameters...
                for (int i = 0; i < xFunction.arity(); i++)
                {
                	if(xFunction.get(i) != yFunction.get(i))
                	{
                		return null;
                	}
                }
          }
		return sigma;
	}

	public static substitution mguvar(GdlVariable x, GdlTerm y, substitution sigma)
	{
		if(sigma.contains(x))
        {
            return mgu(sigma.get(x), y, sigma);
        }
		else if ((x instanceof GdlVariable) && sigma.contains((GdlVariable) y))
        {
            return mgu(x, sigma.get((GdlVariable) y), sigma);
        }
        else
        {
            sigma.put(x, y);
            return sigma;
        }
	}
}

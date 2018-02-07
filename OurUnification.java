package GAPL_project1;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlConstant;
import org.ggp.base.util.gdl.grammar.GdlFunction;
import org.ggp.base.util.gdl.grammar.GdlProposition;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.gdl.grammar.GdlSentence;
import org.ggp.base.util.gdl.grammar.GdlTerm;
import org.ggp.base.util.gdl.grammar.GdlVariable;

public class OurUnification {

	// public static substitution sigma = new substitution();

	public static substitution mgu(Gdl x, Gdl y, substitution sigma)
	{
		// if the two Gdl's are the same, we return the substitution
		if(x.equals(y))
            return sigma;

		// check if either x or y are GdlVariables, call mguvar() and add sub to sigma if successful.
		if (x instanceof GdlVariable)
			return mguvar((GdlVariable) x, (GdlTerm) y, sigma);
		if (y instanceof GdlVariable)
			return mguvar((GdlVariable) y, (GdlTerm) x, sigma);

		if ((x instanceof GdlConstant) && (y instanceof GdlConstant))
        {
            if (!x.equals(y))
            {
            	System.out.println("Null because of constants do not match");
                return null;
            }
        }

		// Only enter if both are GdlFunctions
		else if ((x instanceof GdlFunction) && (y instanceof GdlFunction))
        {
            GdlFunction xFunction = (GdlFunction) x;
            GdlFunction yFunction = (GdlFunction) y;

            // function names don't match, arity() => function arguments size... returns body.size();
            if(xFunction.getName() != yFunction.getName() || xFunction.arity() != yFunction.arity())
            {
            	System.out.println("Null because function names differ / or number of arguments");
                return null;
            }
            else
            {
            	// for each argument...
            	for (int i = 0; i < xFunction.arity(); i++)
            	{
            		if(xFunction.get(i) == yFunction.get(i))
            		{
            			// call mgu with get(x), get(y) if they match
            			mgu(xFunction.get(i) , yFunction.get(i), sigma);
            		}
        			// return null of this fails
            		else
            			System.out.println("Failed to compare function arguments");
            			return null;
            	}
            }
         }

		// Cover GdlRelations and GdlPropositions
		if((x instanceof GdlSentence) && (y instanceof GdlSentence))
		{
			// same head... , check for relation or proposition
			if(((GdlSentence) x).getName() == ((GdlSentence) y).getName())
			{
				if((x instanceof GdlRelation) && (y instanceof GdlRelation) && (((GdlRelation) x).getName() == ((GdlRelation) y).getName()))
				{
					// body.size() of relation --- number of arguments basically
					if(((GdlRelation) x).arity() != ((GdlRelation) y).arity())
					{
						System.out.println("Null because of relation size differs");
						return null;
					}

					for(int i = 0; i < ((GdlRelation) x).arity(); i++)
					{
						mgu(((GdlRelation) x).get(i), ((GdlRelation) y).get(i), sigma);
					}
				}

				else if((x instanceof GdlProposition) && (y instanceof GdlProposition) && (((GdlProposition) x).getName() == ((GdlProposition) y).getName()))
				{
					mgu(((GdlProposition) x).getName(), ((GdlProposition) y).getName(), sigma);
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
		else if ((y instanceof GdlVariable) && sigma.contains((GdlVariable) y))
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

package GAPL_project1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.ggp.base.util.gdl.grammar.Gdl;
import org.ggp.base.util.gdl.grammar.GdlLiteral;
import org.ggp.base.util.gdl.grammar.GdlPool;
import org.ggp.base.util.gdl.grammar.GdlRelation;
import org.ggp.base.util.statemachine.MachineState;
import org.ggp.base.util.statemachine.Move;
import org.ggp.base.util.statemachine.Role;
import org.ggp.base.util.statemachine.StateMachine;
import org.ggp.base.util.statemachine.exceptions.GoalDefinitionException;
import org.ggp.base.util.statemachine.exceptions.MoveDefinitionException;
import org.ggp.base.util.statemachine.exceptions.TransitionDefinitionException;

public class OurStateMachine extends StateMachine {

	private List<Gdl> gameDescr;
	private LinkedList<GdlLiteral> query = new LinkedList<GdlLiteral>();
	private LinkedList<substitution> extrasub = new LinkedList<substitution>();
	List myList = new ArrayList();
	// Set<GdlSentence> Prove = new Prove(query, rules, true);


	@Override
	public void initialize(List<Gdl> description) {
		this.gameDescr = description;

		// this.query = query;
		// substitution result = new substitution();
		// System.out.print("Query: \n" + query + "\n");
		// System.out.print("rules: \n" + rules + "\n");
		// System.out.print("List: \n" + myList + "\n");
		// System.out.print("Description: \n" + description + "\n");
	}

	@Override
	public int getGoal(MachineState state, Role role) throws GoalDefinitionException {
		// TODO Auto-generated method stub
		System.out.print("Role name: " + "\n" + "\n" + "\n");
		// Here we want to return prove([goal(xplayer, V)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{V -> ...)}, {V -> ... }, ... }]
		return 0;
	}

	@Override
	public boolean isTerminal(MachineState state) {
		// TODO Auto-generated method stub
		// Here we want to return prove([terminal)], rules, {})
		// and it should return us a set of substitutes
		// Here we should return random substitutions but not empty []
		//	=> [{}, {M -> ... }, ... }]
		return false;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MachineState getInitialState() {
		// Because getRelation class takes in GdlConstant and a GdlTerm[] we create a array.
		List GdlTerm = new ArrayList();
		GdlTerm.add(GdlPool.getVariable("F"));
		// System.out.print("Testing: \n" + GdlTerm + "\n");
		// We create a initial query
		GdlRelation initial = GdlPool.getRelation(GdlPool.INIT, GdlTerm);
		// Put query inside our LinkedList<GdlLiteral>
		query.add(initial);
		System.out.print("query: \n" + query + "\n");
		// We create a list of substitutions from our query and game description

		List<substitution> answers = Prove.prove(query, gameDescr, extrasub);

		// Here we want to return prove([init(F)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{F -> cell(1,1,b)}, {F -> cell(1,2,b), ... }]

		return null;
		// Then we return Set<GdlTerm> Terms = new Set<>();
		// foreach (subs s in answers){
		// term = s.get("F")
		// terms.add(term);}
		// return new SimpleMachineState( ...);
	}

	@Override
	public List<Move> getLegalMoves(MachineState state, Role role) throws MoveDefinitionException {
		// TODO Auto-generated method stub

		// Here we want to return prove([legal(xplayer, M)], rules, {})
		// and it should return us a set of substitutes
		//	=> [{M -> ...)}, {M -> ... }, ... }]
		return null;

	}

	@Override
	public MachineState getNextState(MachineState state, List<Move> moves) throws TransitionDefinitionException {
		// TODO Auto-generated method stub

		// Here we want to return prove([next( ... )], rules, {})
		// and it should return us a set of substitutes
		//	=> [{M -> ...)}, {M -> ... }, ... }]
		return null;
	}

}

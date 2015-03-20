package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class BuiltinLet extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		LLObject lets = arg.get(0).dethunk();
		LLObject body = arg.get(1);
		Environment inEnv = new Environment(outEnv);
		while (lets instanceof Cons) {
			Cons letsCons = (Cons)lets;
			LLObject let = letsCons.getCar();
			checkArgCount(let, 2);
			inEnv.put(let.get(0).asAtom(), let.get(1).thunk(inEnv));
			lets = letsCons.getCdr().dethunk();
		}
		return body.thunk(inEnv);
	}
}

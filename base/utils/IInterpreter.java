package utils;

public interface IInterpreter<TSource, TResult> {
	TResult interprete(TSource source) throws InvalidSemanticException;
}

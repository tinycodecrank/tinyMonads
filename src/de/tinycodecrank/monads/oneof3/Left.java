package de.tinycodecrank.monads.oneof3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import de.tinycodecrank.monads.opt.Opt;

final class Left<L, M, R> implements OneOf3<L, M, R>
{
	private final L left;
	
	Left(L left)
	{
		this.left = left;
	}
	
	@Override
	public void forEither(Consumer<L> left, Consumer<M> middle, Consumer<R> right)
	{
		left.accept(this.left);
	}
	
	@Override
	public Opt<L> left()
	{
		return Opt.of(left);
	}
	
	@Override
	public Opt<M> middle()
	{
		return Opt.empty();
	}
	
	@Override
	public Opt<R> right()
	{
		return Opt.empty();
	}
	
	@Override
	public L left(Supplier<L> fallback)
	{
		return left;
	}
	
	@Override
	public M middle(Supplier<M> fallback)
	{
		return fallback.get();
	}
	
	@Override
	public R right(Supplier<R> fallback)
	{
		return fallback.get();
	}
	
	@Override
	public boolean isLeft()
	{
		return true;
	}
	
	@Override
	public boolean isMiddle()
	{
		return false;
	}
	
	@Override
	public boolean isRight()
	{
		return false;
	}
	
	@Override
	public <U> OneOf3<U, M, R> mapL(Function<L, U> bind)
	{
		return new Left<>(bind.apply(left));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <U> OneOf3<L, U, R> mapM(Function<M, U> bind)
	{
		return (OneOf3<L, U, R>) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <U> OneOf3<L, M, U> mapR(Function<R, U> bind)
	{
		return (OneOf3<L, M, U>) this;
	}
	
	@Override
	public <U, V, W> OneOf3<U, V, W> map(Function<L, U> left, Function<M, V> middle, Function<R, W> right)
	{
		return new Left<>(left.apply(this.left));
	}
	
	@Override
	public <Ret> Ret fold(Function<L, Ret> left, Function<M, Ret> middle, Function<R, Ret> right)
	{
		return left.apply(this.left);
	}
	
	@Override
	public String toString()
	{
		return String.format(ToString, left, "∅", "∅");
	}
}
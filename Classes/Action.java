public abstract class Action<T extends Action>
{
    abstract void execute();
    abstract void solveConflict(T t);
    abstract boolean isDuplicate(T t);
}

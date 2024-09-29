package interfaces;
import java.util.List;

public interface IRepoGet<T, t> {
    T get(t id);
    List<T> getAll();
}

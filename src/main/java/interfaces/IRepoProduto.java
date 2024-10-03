package interfaces;
import java.util.List;

public interface IRepoProduto<T, t> {
    T get(t id);
    List<T> getAll();
}

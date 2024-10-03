package interfaces;

import model.Pagamento;
import java.util.List;

public interface IRepoPagamento {
    List<Pagamento> getAll();
}

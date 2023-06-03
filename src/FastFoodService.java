import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FastFoodService extends Remote {
    List<Produto> getItensDisponiveis() throws RemoteException;
    void selectItem(Produto produto) throws RemoteException;
    List<Produto> getSelectedItems() throws RemoteException;
    double getTotalAmount() throws RemoteException;
    void pay(double amountPaid) throws RemoteException;
}
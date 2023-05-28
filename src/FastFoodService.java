import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FastFoodService extends Remote {
    boolean login(String username, String password) throws RemoteException;
    List<String> getAvailableItems() throws RemoteException;
    void selectDeliveryAddress(String address) throws RemoteException;
    void addItem(String item) throws RemoteException;
    double checkout() throws RemoteException;
    void logout() throws RemoteException;

    String getDeliveryAddress() throws RemoteException;

    List<String> getSelectedItems() throws RemoteException;

    double getPrice(String item) throws RemoteException;
}

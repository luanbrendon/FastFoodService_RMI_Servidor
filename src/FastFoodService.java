import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FastFoodService extends Remote {
    List<Produto> getItensDisponiveis() throws RemoteException;
    void selectItem(Produto produto) throws RemoteException;
    List<Produto> getSelectedItems() throws RemoteException;
    double getTotalAmount() throws RemoteException;
    String getSelectedItemsString() throws RemoteException;
    void pay(double amountPaid) throws RemoteException;
    boolean verificarLogin(String usuario, String senha) throws RemoteException;
    void informarServidor(String usuario) throws RemoteException;
    boolean cadastrarUsuario(String usuario, String senha, String endereco) throws RemoteException; // Servico para cadastro de novos usuarios


}
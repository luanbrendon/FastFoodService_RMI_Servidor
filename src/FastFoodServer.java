import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastFoodServer extends UnicastRemoteObject implements FastFoodService {
    private Map<String, Double> menu;
    private List<String> selectedItems;
    private String deliveryAddress;
    private boolean loggedIn;

    public FastFoodServer() throws RemoteException {
        menu = new HashMap<>();
        menu.put("Cheese Burger", 5.99);
        menu.put("X-Burger", 6.99);
        menu.put("X-Tudo", 8.99);
        menu.put("X-Alcatra", 9.99);
        menu.put("Batata Frita", 3.99);
        menu.put("X-Camarão", 12.99);
        menu.put("X-Salada", 7.99);

        selectedItems = new ArrayList<>();
        deliveryAddress = "";
        loggedIn = false;
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        // Verificar as credenciais do usuário no banco de dados ou sistema de autenticação
        // Retornar true se as credenciais estiverem corretas, caso contrário, retornar false
        loggedIn = true;
        return true;
    }

    @Override
    public List<String> getAvailableItems() throws RemoteException {
        return new ArrayList<>(menu.keySet());
    }

    @Override
    public void selectDeliveryAddress(String address) throws RemoteException {
        deliveryAddress = address;
    }

    @Override
    public void addItem(String item) throws RemoteException {
        selectedItems.add(item);
    }

    @Override
    public double checkout() throws RemoteException {
        double total = 0.0;
        for (String item : selectedItems) {
            total += menu.getOrDefault(item, 0.0);
        }
        return total;
    }

    @Override
    public void logout() throws RemoteException {
        loggedIn = false;
        selectedItems.clear();
        deliveryAddress = "";
    }

    @Override
    public String getDeliveryAddress() throws RemoteException {
        return deliveryAddress;
    }

    @Override
    public List<String> getSelectedItems() throws RemoteException {
        return new ArrayList<>(selectedItems);
    }

    @Override
    public double getPrice(String item) throws RemoteException {
        return menu.getOrDefault(item, 0.0);
    }
}
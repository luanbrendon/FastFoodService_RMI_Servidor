import javax.swing.*;
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
    private double totalAmount;

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
        totalAmount = 0.0;
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
        totalAmount += getPrice(item);
        System.out.println("Item adicionado: " + item + ", Preço: " + getPrice(item) + ", Total atual: " + totalAmount);
    }

    @Override
    public double checkout() throws RemoteException {
        return totalAmount;
    }

    @Override
    public void logout() throws RemoteException {
        loggedIn = false;
        selectedItems.clear();
        deliveryAddress = "";
        totalAmount = 0.0;
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
    public void selectItem() throws RemoteException {
        List<String> availableItems = getAvailableItems();
        String[] options = availableItems.toArray(new String[0]);
        int choice = JOptionPane.showOptionDialog(null, "Selecione um item:", "Fast Food App - Seleção de Item", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice >= 0 && choice < availableItems.size()) {
            String selectedItem = availableItems.get(choice);
            addItem(selectedItem);
            System.out.println("Itens selecionados: " + getSelectedItems());
        }
    }

    @Override
    public double getPrice(String item) throws RemoteException {
        return menu.getOrDefault(item, 0.0);
    }

    @Override
    public double processPayment(String clientName, double amountPaid) throws RemoteException {
        double change = amountPaid - totalAmount;
        totalAmount = 0.0; // Reinicializa o totalAmount após o pagamento ser processado
        return change;
    }
}

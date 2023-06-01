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
    public double getPrice(String item) throws RemoteException {
        return menu.getOrDefault(item, 0.0);
    }

    @Override
    public double processPayment(String clientName, double amountPaid) throws RemoteException {
        double change = amountPaid - totalAmount; // Calcula o troco

        if (change >= 0) {
            // Pagamento completo, há troco a ser dado
            // Realize aqui ações adicionais necessárias, como registrar o pagamento no sistema, imprimir o comprovante, etc.

            // Reiniciar a lista de itens selecionados e o valor total
            selectedItems.clear();
            deliveryAddress = "";
            totalAmount = 0.0;

            return change; // Retorna o valor do troco
        } else {
            // Pagamento insuficiente, não há troco a ser dado
            // Aqui você pode realizar ações adicionais, como exibir uma mensagem de erro, registrar o pagamento parcial, etc.

            return -1; // Retorna um valor negativo para indicar que o pagamento foi insuficiente
        }
    }
}

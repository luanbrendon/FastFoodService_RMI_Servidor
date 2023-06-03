import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FastFoodServiceImpl extends UnicastRemoteObject implements FastFoodService {
    private List<Produto> itensDisponiveis;
    private List<Produto> selectedItems;

    public FastFoodServiceImpl(List<Produto> itensDisponiveis) throws RemoteException {
        super();
        this.itensDisponiveis = itensDisponiveis;
        this.selectedItems = new ArrayList<>();
    }

    public List<Produto> getItensDisponiveis() throws RemoteException {
        return itensDisponiveis;
    }

    public void selectItem(Produto produto) throws RemoteException {
        selectedItems.add(produto);
    }

    public List<Produto> getSelectedItems() throws RemoteException {
        return selectedItems;
    }

    public double getTotalAmount() throws RemoteException {
        double totalAmount = 0.0;
        for (Produto produto : selectedItems) {
            totalAmount += produto.getPreco();
        }
        return totalAmount;
    }

    private String getSelectedItemsString() {
        StringBuilder sb = new StringBuilder();
        for (Produto produto : selectedItems) {
            sb.append(produto.getNome()).append(" - R$ ").append(produto.getPreco()).append("\n");
        }
        return sb.toString();
    }


    public void pay(double amountPaid) throws RemoteException {
        double totalAmount = getTotalAmount();
        if (amountPaid < totalAmount) {
            System.out.println("Valor insuficiente. Por favor, pague o valor total da compra.");
        } else {
            System.out.println("O valor total do cliente foi de R$ " + totalAmount);
            System.out.println("O valor que o cliente pagou foi de R$ " + amountPaid);
            double change = amountPaid - totalAmount;
            System.out.println("Troco: R$ " + change);
            System.out.println("Itens selecionado: \n" +getSelectedItemsString());
            selectedItems.clear();
        }
    }
}
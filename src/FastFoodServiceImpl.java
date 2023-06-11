import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastFoodServiceImpl extends UnicastRemoteObject implements FastFoodService {

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>(){{
        add(new Usuario("admin", "admin"));
        add(new Usuario("Allan", "Allan123"));
        add(new Usuario("Alvaro", "Alvaro123"));
        add(new Usuario("Luan", "Luan123"));
        add(new Usuario("Thor", "Thor123"));
    }};

    private static FastFoodServiceImpl instance;
    private List<Produto> itensDisponiveis;
    private List<Produto> selectedItems;

    public FastFoodServiceImpl(List<Produto> itensDisponiveis) throws RemoteException {
        super();
        this.itensDisponiveis = itensDisponiveis;
        this.selectedItems = new ArrayList<>();
    }

    public static synchronized FastFoodServiceImpl getInstance(List<Produto> itensDisponiveis) throws RemoteException {
        if (instance == null) {
            instance = new FastFoodServiceImpl(itensDisponiveis);
        }
        return instance;
    }

    public List<Produto> getItensDisponiveis() throws RemoteException {
        return itensDisponiveis;
    }

    public void selectItem(Produto produto) throws RemoteException {
        selectedItems.add(produto);
        String itemSelectedString = getItemSelectedString(produto);
        System.out.println("Item selecionado: " + itemSelectedString);
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

  public String getSelectedItemsString() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        int itemCount = selectedItems.size();
        if (itemCount == 0) {
            sb.append("Nenhum item selecionado");
        } else {
            sb.append(itemCount).append(" itens selecionados:\n");
        }
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
            System.out.println("========== Nota Fiscal ==========");
            System.out.println("Cliente: " + nomeUsuarioLogado);
            System.out.println("Data: " + LocalDate.now());
            System.out.println("Hora: " + LocalTime.now());
            System.out.println("-------------------------------");
            System.out.println("Itens do pedido:");
            System.out.println(getSelectedItemsString());
            System.out.println("-------------------------------");
            System.out.println("Valor total: R$ " + totalAmount);
            System.out.println("Valor pago: R$ " + amountPaid);
            double change = amountPaid - totalAmount;
            System.out.println("Troco: R$ " + change);
            System.out.println("================================");
            selectedItems.clear();
        }
    }

private String nomeUsuarioLogado;
    @Override
    public boolean verificarLogin(String usuario, String senha) throws RemoteException {
        if (usuario.isEmpty() || senha.isEmpty()) {
            return false;
        }

        for (Usuario u : usuarios) {
            if (usuario.equals(u.getUsuario()) && senha.equals(u.getSenha())) {
                nomeUsuarioLogado = usuario;
                informarServidor(usuario);
                return true;
            }
        }

        return false;
    }


    @Override
    public void informarServidor(String usuario) throws RemoteException {
        System.out.println("Usuário " + usuario + " fez login.");
    }

    @Override
    public boolean cadastrarUsuario(String usuario, String senha) throws RemoteException {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return false; // Usuário já cadastrado
            }
        }
        usuarios.add(new Usuario(usuario, senha));
        informarServidor(usuario);
        return true; // Cadastro realizado com sucesso
    }


    private String getItemSelectedString(Produto produto) {
        return produto.getNome() + " - R$ " + produto.getPreco();
    }



}
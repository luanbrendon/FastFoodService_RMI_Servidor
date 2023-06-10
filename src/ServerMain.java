import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Cria uma lista de objetos Produto para representar os itens disponíveis na lanchonete
            List<Produto> itensDisponiveis = new ArrayList<>();
            itensDisponiveis.add(new Produto("Cheeseburger", 15.0));
            itensDisponiveis.add(new Produto("Cheeseburger Duplo", 20.0));
            itensDisponiveis.add(new Produto("Hambúrguer Vegetariano", 18.0));
            itensDisponiveis.add(new Produto("Hambúrguer Especial", 22.0));
            itensDisponiveis.add(new Produto("Sanduíche de Frango", 12.0));
            itensDisponiveis.add(new Produto("Sanduíche de Carne", 14.0));
            itensDisponiveis.add(new Produto("Sanduíche de Atum", 16.0));
            itensDisponiveis.add(new Produto("Sanduíche Vegetariano", 22.0));
            itensDisponiveis.add(new Produto("Batata Frita", 8.0));
            itensDisponiveis.add(new Produto("Onion Rings", 10.0));
            itensDisponiveis.add(new Produto("Nuggets de Frango", 12.0));
            itensDisponiveis.add(new Produto("Salada Verde", 9.0));
            itensDisponiveis.add(new Produto("Refrigerante (lata)", 5.0));
            itensDisponiveis.add(new Produto("Refrigerante (2 litros", 10.0));
            itensDisponiveis.add(new Produto("Suco Natural", 8.50));
            itensDisponiveis.add(new Produto("Agua Mineral", 6.50));
            itensDisponiveis.add(new Produto("Sundae de Chocolate", 8.50));
            itensDisponiveis.add(new Produto("Milkshake", 10.50));
            itensDisponiveis.add(new Produto("Torta de Maçã", 9.50));
            itensDisponiveis.add(new Produto("Petit Gateau", 12.50));


            FastFoodService fastFoodService = FastFoodServiceImpl.getInstance(itensDisponiveis);
            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("FastFoodService", fastFoodService);

            System.out.println("Servidor pronto para receber solicitações.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
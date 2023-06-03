import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Cria uma lista de objetos Produto para representar os itens disponíveis na lanchonete
            List<Produto> itensDisponiveis = new ArrayList<>();
            itensDisponiveis.add(new Produto("Hambúrguer", 10.0));
            itensDisponiveis.add(new Produto("Batata Frita", 5.0));
            itensDisponiveis.add(new Produto("Refrigerante", 3.0));

            FastFoodService fastFoodService = new FastFoodServiceImpl(itensDisponiveis);
            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("FastFoodService", fastFoodService);

            System.out.println("Servidor pronto para receber solicitações.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
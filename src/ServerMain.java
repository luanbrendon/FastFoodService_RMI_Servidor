import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            FastFoodService fastFoodService = new FastFoodServer();
            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("FastFoodService", fastFoodService);
            System.out.println("Servidor pronto para receber solicitações.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}

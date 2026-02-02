import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {

    public ArrayList<ClienteDTO> listar() {
        ArrayList<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nome FROM cliente ORDER BY nome";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                lista.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        return lista;
    }
}

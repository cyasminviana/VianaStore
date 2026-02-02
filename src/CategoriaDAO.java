import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO {

    public ArrayList<CategoriaDTO> listar() {
        ArrayList<CategoriaDTO> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nome FROM categoria ORDER BY nome";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                CategoriaDTO c = new CategoriaDTO();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                lista.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage(), e);
        }
        return lista;
    }
}

   
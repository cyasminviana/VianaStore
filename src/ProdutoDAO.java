import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDAO {

    public ArrayList<ProdutoDTO> listar() {
        ArrayList<ProdutoDTO> lista = new ArrayList<>();
        String sql = "SELECT id_produto, nome, tamanho, cor, estoque, preco, id_categoria FROM produto ORDER BY id_produto";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ProdutoDTO p = new ProdutoDTO();
                p.setId_produto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setTamanho(rs.getString("tamanho"));
                p.setCor(rs.getString("cor"));
                p.setEstoque(rs.getInt("estoque"));
                p.setPreco(rs.getDouble("preco"));
                p.setId_categoria(rs.getInt("id_categoria"));
                lista.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }
        return lista;
    }

    public ArrayList<ProdutoDTO> listarPorNome(String termo) {
        ArrayList<ProdutoDTO> lista = new ArrayList<>();
        String sql = "SELECT id_produto, nome, tamanho, cor, estoque, preco, id_categoria "
                   + "FROM produto WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + termo + "%");

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    ProdutoDTO p = new ProdutoDTO();
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setTamanho(rs.getString("tamanho"));
                    p.setCor(rs.getString("cor"));
                    p.setEstoque(rs.getInt("estoque"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setId_categoria(rs.getInt("id_categoria"));
                    lista.add(p);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produtos: " + e.getMessage(), e);
        }
        return lista;
    }

    public ProdutoDTO buscarPorId(int id) {
        String sql = "SELECT id_produto, nome, tamanho, cor, estoque, preco, id_categoria FROM produto WHERE id_produto = ?";
        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    ProdutoDTO p = new ProdutoDTO();
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setTamanho(rs.getString("tamanho"));
                    p.setCor(rs.getString("cor"));
                    p.setEstoque(rs.getInt("estoque"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setId_categoria(rs.getInt("id_categoria"));
                    return p;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto: " + e.getMessage(), e);
        }
        return null;
    }

    public void inserir(ProdutoDTO p) {
        String sql = "INSERT INTO produto (nome, tamanho, cor, estoque, preco, id_categoria) VALUES (?,?,?,?,?,?)";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, p.getNome());
            pst.setString(2, p.getTamanho());
            pst.setString(3, p.getCor());
            pst.setInt(4, p.getEstoque());
            pst.setDouble(5, p.getPreco());
            pst.setInt(6, p.getId_categoria());

            pst.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage(), e);
        }
    }

    public void atualizar(ProdutoDTO p) {
        String sql = "UPDATE produto SET nome=?, tamanho=?, cor=?, estoque=?, preco=?, id_categoria=? WHERE id_produto=?";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, p.getNome());
            pst.setString(2, p.getTamanho());
            pst.setString(3, p.getCor());
            pst.setInt(4, p.getEstoque());
            pst.setDouble(5, p.getPreco());
            pst.setInt(6, p.getId_categoria());
            pst.setInt(7, p.getId_produto());

            pst.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    public void excluir(int idProduto) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";

        try (Connection conn = new ConectaDAO().connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idProduto);
            pst.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
        }
    }

    public void baixarEstoque(Connection conn, int idProduto, int quantidade) throws Exception {
        String sql = "UPDATE produto SET estoque = estoque - ? WHERE id_produto = ? AND estoque >= ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, quantidade);
            pst.setInt(2, idProduto);
            pst.setInt(3, quantidade);

            int linhas = pst.executeUpdate();
            if (linhas == 0) {
                throw new Exception("Estoque insuficiente para o produto ID " + idProduto);
            }
        }
    }
}

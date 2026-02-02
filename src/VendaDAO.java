import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VendaDAO {

    public int registrarVenda(VendaDTO venda, ArrayList<ItemVendaDTO> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new RuntimeException("Carrinho vazio. Não é possível registrar venda.");
        }

        String sqlVenda = "INSERT INTO venda (id_cliente, id_usuario, data_venda, total) VALUES (?,?,?,?)";
        String sqlItem  = "INSERT INTO itemvenda (id_venda, id_produto, quantidade, preco_unitario) VALUES (?,?,?,?)";

        try (Connection conn = new ConectaDAO().connectDB()) {
            conn.setAutoCommit(false);

            
            int idVendaGerada;
            try (PreparedStatement pstVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
                pstVenda.setInt(1, venda.getId_cliente());
                pstVenda.setInt(2, venda.getId_usuario());
                pstVenda.setObject(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                pstVenda.setDouble(4, venda.getTotal());

                pstVenda.executeUpdate();

                try (ResultSet rs = pstVenda.getGeneratedKeys()) {
                    if (!rs.next()) throw new RuntimeException("Não foi possível obter ID da venda.");
                    idVendaGerada = rs.getInt(1);
                }
            }

            
            ProdutoDAO produtoDAO = new ProdutoDAO();

            try (PreparedStatement pstItem = conn.prepareStatement(sqlItem)) {
                for (ItemVendaDTO item : itens) {

                    
                    produtoDAO.baixarEstoque(conn, item.getId_produto(), item.getQuantidade());

                    pstItem.setInt(1, idVendaGerada);
                    pstItem.setInt(2, item.getId_produto());
                    pstItem.setInt(3, item.getQuantidade());
                    pstItem.setDouble(4, item.getPreco_unitario());
                    pstItem.addBatch();
                }
                pstItem.executeBatch();
            }

            conn.commit();
            return idVendaGerada;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar venda: " + e.getMessage(), e);
        }
    }
}

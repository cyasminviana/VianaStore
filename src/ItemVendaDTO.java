public class ItemVendaDTO {
    private int id_itemvenda;
    private int id_venda;
    private int id_produto;
    private int quantidade;
    private double preco_unitario;

    public int getId_itemvenda() { return id_itemvenda; }
    public void setId_itemvenda(int id_itemvenda) { this.id_itemvenda = id_itemvenda; }

    public int getId_venda() { return id_venda; }
    public void setId_venda(int id_venda) { this.id_venda = id_venda; }

    public int getId_produto() { return id_produto; }
    public void setId_produto(int id_produto) { this.id_produto = id_produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco_unitario() { return preco_unitario; }
    public void setPreco_unitario(double preco_unitario) { this.preco_unitario = preco_unitario; }
}

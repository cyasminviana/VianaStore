public class VendaDTO {
    private int id_venda;
    private int id_cliente;
    private int id_usuario;
    private String data_venda; 
    private double total;

    public int getId_venda() { return id_venda; }
    public void setId_venda(int id_venda) { this.id_venda = id_venda; }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public String getData_venda() { return data_venda; }
    public void setData_venda(String data_venda) { this.data_venda = data_venda; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}

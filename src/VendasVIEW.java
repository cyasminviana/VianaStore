import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendasVIEW extends javax.swing.JFrame {

    // Carrinho definitivo (fica só na tela enquanto a venda está sendo montada)
    private final ArrayList<ItemVendaDTO> carrinho = new ArrayList<>();

    // Listas auxiliares para mapear ComboBox (que está como String)
    private ArrayList<ProdutoDTO> produtosCarregados = new ArrayList<>();
    private ArrayList<ClienteDTO> clientesCarregados = new ArrayList<>();

    public VendasVIEW() {
        initComponents();
        carregarClientes();
        carregarProdutos();
        limparCarrinhoUI();
        
    }

   private void carregarClientes() {
    cbCliente.removeAllItems();
    cbCliente.addItem("Selecionar");

    ClienteDAO dao = new ClienteDAO();
    clientesCarregados = dao.listar();

    for (ClienteDTO c : clientesCarregados) {
        cbCliente.addItem(c.getNome());
    }
}

private void carregarProdutos() {
    cbProduto.removeAllItems();
    cbProduto.addItem("Selecionar");

    ProdutoDAO dao = new ProdutoDAO();
    produtosCarregados = dao.listar();

    for (ProdutoDTO p : produtosCarregados) {
        cbProduto.addItem(p.getId_produto() + " - " + p.getNome());
    }
}

private ProdutoDTO getProdutoSelecionado() {
    int idx = cbProduto.getSelectedIndex();
    if (idx <= 0) return null;

    return produtosCarregados.get(idx - 1);
}

private ClienteDTO getClienteSelecionado() {
    int idx = cbCliente.getSelectedIndex();
    if (idx <= 0) return null;
    return clientesCarregados.get(idx - 1);
}

private void atualizarTabelaCarrinho() {
    DefaultTableModel model = (DefaultTableModel) tblItens.getModel();
    model.setRowCount(0);

    double total = 0;

    for (ItemVendaDTO item : carrinho) {
        double subtotal = item.getQuantidade() * item.getPreco_unitario();
        total += subtotal;

       
        String nomeProduto = "ID " + item.getId_produto();
        for (ProdutoDTO p : produtosCarregados) {
            if (p.getId_produto() == item.getId_produto()) {
                nomeProduto = p.getNome();
                break;
            }
        }

        model.addRow(new Object[]{
            nomeProduto,
            item.getQuantidade(),
            String.format("R$ %.2f", item.getPreco_unitario()),
            String.format("R$ %.2f", subtotal)
        });
    }

    lblTotal.setText("TOTAL DA VENDA: R$ " + String.format("%.2f", total));
}

private void limparCarrinhoUI() {
    carrinho.clear();
    txtQuantidade.setText("");
    cbCliente.setSelectedIndex(0);
    cbProduto.setSelectedIndex(0);

    DefaultTableModel model = (DefaultTableModel) tblItens.getModel();
    model.setRowCount(0);

    lblTotal.setText("TOTAL DA VENDA: R$ 0,00");
}

private boolean existeItemNoCarrinho(int idProduto) {
    for (ItemVendaDTO item : carrinho) {
        if (item.getId_produto() == idProduto) return true;
    }
    return false;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        cbProduto = new javax.swing.JComboBox<>();
        txtQuantidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItens = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnCanelar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        jLabel2.setText("VS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("REGISTRO DE VENDAS");

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Cliente 1", "Cliente 2", "Cliente 3", " " }));
        cbCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar" }));
        cbProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtQuantidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });

        jLabel3.setText("CLIENTE: ");

        jLabel4.setText("PRODUTO:");

        jLabel5.setText("QUANTIDADE :");

        tblItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PRODUTO", "QUANTIDADE", "PREÇO UNITÁRIO", "SUBTOTAL"
            }
        ));
        jScrollPane1.setViewportView(tblItens);

        btnAdicionar.setText("ADICIONAR ITEM");
        btnAdicionar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnFinalizar.setText(" FINALIZAR VENDA");
        btnFinalizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnCanelar.setText("CANCELAR");
        btnCanelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanelarActionPerformed(evt);
            }
        });

        lblTotal.setText("TOTAL: R$ 0,00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCanelar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTotal)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnCanelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
  if (cbCliente.getSelectedIndex() == 0) {
    JOptionPane.showMessageDialog(this, "Selecione um cliente.");
    return;
}

ProdutoDTO produto = getProdutoSelecionado();
if (produto == null) {
    JOptionPane.showMessageDialog(this, "Selecione um produto.");
    return;
}

int qtd;
try {
    qtd = Integer.parseInt(txtQuantidade.getText().trim());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Informe uma quantidade válida.");
    return;
}

if (qtd <= 0) {
    JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.");
    return;
}

if (qtd > produto.getEstoque()) {
    JOptionPane.showMessageDialog(this, "Estoque insuficiente! Estoque atual: " + produto.getEstoque());
    return;
}

for (ItemVendaDTO item : carrinho) {
    if (item.getId_produto() == produto.getId_produto()) {
        int novaQtd = item.getQuantidade() + qtd;

        if (novaQtd > produto.getEstoque()) {
            JOptionPane.showMessageDialog(this, "Somando, a quantidade ultrapassa o estoque.");
            return;
        }

        item.setQuantidade(novaQtd);
        atualizarTabelaCarrinho();
        txtQuantidade.setText("");
        return;
    }
}

ItemVendaDTO item = new ItemVendaDTO();
item.setId_produto(produto.getId_produto());
item.setQuantidade(qtd);
item.setPreco_unitario(produto.getPreco());

carrinho.add(item);

atualizarTabelaCarrinho();
txtQuantidade.setText("");

    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos um item na venda.");
            return;
        }

        ClienteDTO cliente = getClienteSelecionado();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        // Recarrega produtos do banco para validar estoque atualizado
        produtosCarregados = new ProdutoDAO().listar();

        // Validação final de estoque (antes de registrar)
        for (ItemVendaDTO item : carrinho) {
            ProdutoDTO p = null;
            for (ProdutoDTO prod : produtosCarregados) {
                if (prod.getId_produto() == item.getId_produto()) {
                    p = prod;
                    break;
                }
            }
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado (ID " + item.getId_produto() + ").");
                return;
            }
            if (item.getQuantidade() > p.getEstoque()) {
                JOptionPane.showMessageDialog(this, "Estoque insuficiente para o produto: " + p.getNome() + " (estoque: " + p.getEstoque() + ")");
                return;
            }
        }

        // Calcula total
        double total = 0;
        for (ItemVendaDTO item : carrinho) {
            total += item.getQuantidade() * item.getPreco_unitario();
        }

        VendaDTO venda = new VendaDTO();
        venda.setId_cliente(cliente.getId_cliente());
        // Se você ainda não tem login, deixa fixo 1 (pode ajustar depois)
        venda.setId_usuario(1);
        venda.setTotal(total);

        try {
            int idVenda = new VendaDAO().registrarVenda(venda, carrinho);
            JOptionPane.showMessageDialog(this, "Venda registrada com sucesso! ID: " + idVenda);
            limparCarrinhoUI();
            new ListagemVIEW().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar venda: " + e.getMessage());
        }

    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanelarActionPerformed
 int resp = JOptionPane.showConfirmDialog(
        this,
        "Deseja cancelar a venda?",
        "Confirmação",
        JOptionPane.YES_NO_OPTION
);

if (resp == JOptionPane.YES_OPTION) {
    limparCarrinhoUI();
    new ListagemVIEW().setVisible(true);
    this.dispose();
}

    }//GEN-LAST:event_btnCanelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendasVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCanelar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblItens;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}

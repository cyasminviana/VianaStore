public class UsuarioDTO {
    private int id_usuario;
    private String nome;
    private String login;
    private String tipo;   
    private String senha;

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

package horizonleap.gerenciamento.clientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType; 
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")

public class ClienteModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String endereco;

    @Column
    private String infoContato;

    @Column
    private String cpf;

    @Enumerated(EnumType.STRING) 
    @Column(name = "endereco_uf")
    private EnderecoUF enderecoUF;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String endereco, String infoContato, String cpf, EnderecoUF enderecoUF) {
        this.nome = nome;
        this.endereco = endereco;
        this.infoContato = infoContato;
        this.cpf = cpf;
        this.enderecoUF = enderecoUF;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getInfoContato() {
        return infoContato;
    }

    public void setInfoContato(String infoContato) {
        this.infoContato = infoContato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoUF getEnderecoUF() {
        return enderecoUF;
    }

    public void setEnderecoUF(EnderecoUF enderecoUF) {
        this.enderecoUF = enderecoUF;
    }
}

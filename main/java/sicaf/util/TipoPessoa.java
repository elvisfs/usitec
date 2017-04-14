package sicaf.util;

public enum TipoPessoa {
FISICA ("F"),
JURIDICA ("J");

private String descricao;

private TipoPessoa(String descricao) {
	this.descricao = descricao;
}
public String getDescricao() {
	return descricao;
}
public void setTipo(String descricao) {
	this.descricao = descricao;
}


}

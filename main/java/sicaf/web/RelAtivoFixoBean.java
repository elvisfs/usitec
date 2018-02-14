package sicaf.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import sicaf.centroCusto.CentroCusto;
import sicaf.centroCusto.CentroCustoRN;
import sicaf.util.RNException;
import sicaf.util.UtilException;
import sicaf.web.util.RelatorioUtil;

@ManagedBean(name = "relAtivoFixoBean")
public class RelAtivoFixoBean {
	private CentroCusto centroCusto;
	private List<CentroCusto> centrosCusto;
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;

	@PostConstruct
	public void init() {
		CentroCustoRN centroCustoRN = new CentroCustoRN();
		try {
			this.setCentrosCusto(centroCustoRN.listar());
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public List<CentroCusto> getCentrosCusto() {
		return centrosCusto;
	}

	public void setCentrosCusto(List<CentroCusto> centrosCusto) {
		this.centrosCusto = centrosCusto;
	}

	public void gerarRelatorio() {

	}

	public StreamedContent getArquivoRetorno() {
		FacesContext context = FacesContext.getCurrentInstance();
		String nomeRelatorioJasper = "relAtivoFixo";
		String nomeRelatorioSaida = "ativosfixosrel";
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap<String, String> parametrosRelatorio = new HashMap<String, String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select concat(centro.codigo,ativo.codigo) codigo, ativo.descricao ativofixo,");
		sql.append(" ativo.identificador, centro.descricao centrocusto");
		sql.append(" from ativofixo ativo inner join centrocusto centro on centro.id = ativo.centrocusto_id");
		if(this.centroCusto !=null){
			sql.append(" where centro.id = ").append(this.centroCusto.getId().toString());
		}
		
		parametrosRelatorio.put("sql", sql.toString());
		try {
			this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return this.arquivoRetorno;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
}

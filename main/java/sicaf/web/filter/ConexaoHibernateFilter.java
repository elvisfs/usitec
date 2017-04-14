package sicaf.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import sicaf.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter{
	private SessionFactory sf;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		try{
			this.sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			this.sf.getCurrentSession().getTransaction().commit();
		}catch (Throwable ex){
			try{
				if(this.sf.getCurrentSession().getTransaction().isActive()){
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			}catch(Throwable t){
				t.printStackTrace();
			}
			throw new ServletException(ex);
		}finally {
			this.sf.getCurrentSession().close();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
		
	}

}

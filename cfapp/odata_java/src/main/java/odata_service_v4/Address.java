package odata_service_v4;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import odata_service_v4.AddressEdmProvider;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.edmx.EdmxReference;



/**
 * This class represents a standard HttpServlet implementation. It is used as
 * main entry point for the web application that carries the OData service. The
 * implementation of this HttpServlet simply delegates the user requests to the
 * ODataHttpHandler
 */

public class Address extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource ds;
	private DataProvider dp;

	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// create odata handler and configure it with EdmProvider and
			// Processor
			OData odata = OData.newInstance();
			ServiceMetadata edm = odata.createServiceMetadata(new AddressEdmProvider(), new ArrayList<EdmxReference>());
			ODataHttpHandler handler = odata.createHandler(edm);
			dp = new DataProvider();
			dp.setDataSourceInstance(ds);
			handler.register(dp);
			// let the handler do the work
			handler.process(req, resp);
		} catch (RuntimeException e) {
			throw new ServletException(e);
		}
	}

}
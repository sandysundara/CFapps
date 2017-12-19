package odata_service_v2;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;

public class AddressServiceFactory extends ODataServiceFactory {

	@Override
	public ODataService createService(ODataContext ctx) throws ODataException {

		EdmProvider edmProvider = new AddressEdmProvider();

		ODataSingleProcessor addressDataProcessor = new AddressDataProvider();

		return createODataSingleProcessorService(edmProvider, addressDataProcessor);

	}

}

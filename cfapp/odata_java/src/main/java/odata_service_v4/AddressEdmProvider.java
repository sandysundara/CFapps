package odata_service_v4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;

public class AddressEdmProvider extends CsdlAbstractEdmProvider {

	// Service Namespace
	public static final String NAMESPACE = "odata_java";

	// EDM Container
	public static final String ENTITY_CONTAINER_NAME = "odata_java_container";
	public static final FullQualifiedName ENTITY_CONTAINER_FQN = new FullQualifiedName(NAMESPACE,
			ENTITY_CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_ADDRESS_NAME = "Address";
	public static final FullQualifiedName ET_ADDRESS_FQN = new FullQualifiedName(NAMESPACE, ET_ADDRESS_NAME);

	// Entity Set Names
	public static final String ES_ADDRESS_NAME = "Addresses";

	@Override
	public List<CsdlSchema> getSchemas() throws ODataException {
		// create Schema
		CsdlSchema schema = new CsdlSchema();
		schema.setNamespace(NAMESPACE);

		// add EntityTypes
		List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		entityTypes.add(getEntityType(ET_ADDRESS_FQN));
		schema.setEntityTypes(entityTypes);

		// add EntityContainer
		schema.setEntityContainer(getEntityContainer());

		// finally
		List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		schemas.add(schema);

		return schemas;
	}

	@Override
	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
		// This method is invoked when displaying the Service Document at e.g.
		// http://localhost:8080/DemoService/DemoService.svc
		if (entityContainerName == null || entityContainerName.equals(ENTITY_CONTAINER_FQN)) {
			CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
			entityContainerInfo.setContainerName(ENTITY_CONTAINER_FQN);
			return entityContainerInfo;
		}

		return null;
	}

	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {
		// this method is called for one of the EntityTypes that are configured
		// in the Schema
		if (entityTypeName.equals(ET_ADDRESS_FQN)) {

			// create EntityType properties
			CsdlProperty id = new CsdlProperty().setName("id")
					.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
			CsdlProperty first_name = new CsdlProperty().setName("first_name")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty last_name = new CsdlProperty().setName("last_name")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty address = new CsdlProperty().setName("address")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty city = new CsdlProperty().setName("city")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty country = new CsdlProperty().setName("country")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty zip = new CsdlProperty().setName("zip")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty phone = new CsdlProperty().setName("phone")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty email = new CsdlProperty().setName("email")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
			CsdlProperty web = new CsdlProperty().setName("web")
					.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

			// create CsdlPropertyRef for Key element
			CsdlPropertyRef propertyRef = new CsdlPropertyRef();
			propertyRef.setName("id");

			// configure EntityType
			CsdlEntityType entityType = new CsdlEntityType();
			entityType.setName(ET_ADDRESS_NAME);
			entityType.setProperties(
					Arrays.asList(id, first_name, last_name, address, city, country, zip, phone, email, web));
			entityType.setKey(Collections.singletonList(propertyRef));

			return entityType;
		}

		return null;
	}

	@Override
	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {
		if (entityContainer.equals(ENTITY_CONTAINER_FQN)) {
			if (entitySetName.equals(ES_ADDRESS_NAME)) {
				CsdlEntitySet entitySet = new CsdlEntitySet();
				entitySet.setName(ES_ADDRESS_NAME);
				entitySet.setType(ET_ADDRESS_FQN);

				return entitySet;
			}
		}
		return null;
	}

	@Override
	public CsdlEntityContainer getEntityContainer() throws ODataException {
		// create EntitySets
		List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		entitySets.add(getEntitySet(ENTITY_CONTAINER_FQN, ES_ADDRESS_NAME));

		// create EntityContainer
		CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		entityContainer.setName(ENTITY_CONTAINER_NAME);
		entityContainer.setEntitySets(entitySets);

		return entityContainer;
	}

}

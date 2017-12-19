package odata_service_v2;

import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.edm.provider.EntityContainer;
import org.apache.olingo.odata2.api.edm.provider.EntityContainerInfo;
import org.apache.olingo.odata2.api.edm.provider.EntitySet;
import org.apache.olingo.odata2.api.edm.provider.EntityType;
import org.apache.olingo.odata2.api.edm.provider.Facets;
import org.apache.olingo.odata2.api.edm.provider.Key;
import org.apache.olingo.odata2.api.edm.provider.Property;
import org.apache.olingo.odata2.api.edm.provider.PropertyRef;
import org.apache.olingo.odata2.api.edm.provider.Schema;
import org.apache.olingo.odata2.api.edm.provider.SimpleProperty;
import org.apache.olingo.odata2.api.exception.ODataException;

public class AddressEdmProvider extends EdmProvider {

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
	public List<Schema> getSchemas() throws ODataException {
		List<Schema> schemas = new ArrayList<Schema>();

		Schema schema = new Schema();
		schema.setNamespace(NAMESPACE);

		List<EntityType> entityTypes = new ArrayList<EntityType>();
		entityTypes.add(getEntityType(ET_ADDRESS_FQN));
		schema.setEntityTypes(entityTypes);

		List<EntityContainer> entityContainers = new ArrayList<EntityContainer>();
		EntityContainer entityContainer = new EntityContainer();
		entityContainer.setName(ENTITY_CONTAINER_NAME).setDefaultEntityContainer(true);

		List<EntitySet> entitySets = new ArrayList<EntitySet>();
		entitySets.add(getEntitySet(ENTITY_CONTAINER_NAME, ET_ADDRESS_NAME));
		entityContainer.setEntitySets(entitySets);

		entityContainers.add(entityContainer);
		schema.setEntityContainers(entityContainers);

		schemas.add(schema);

		return schemas;
	}

	@Override
	public EntityType getEntityType(FullQualifiedName edmFQName) throws ODataException {
		if (NAMESPACE.equals(edmFQName.getNamespace())) {

			if (ET_ADDRESS_FQN.getName().equals(edmFQName.getName())) {

				// Properties
				List<Property> properties = new ArrayList<Property>();
				properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32)
						.setFacets(new Facets().setNullable(false)));
				properties.add(new SimpleProperty().setName("first_name").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("last_name").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("address").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("city").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("country").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("zip").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("phone").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("email").setType(EdmSimpleTypeKind.String));
				properties.add(new SimpleProperty().setName("web").setType(EdmSimpleTypeKind.String));

				// Key
				List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
				keyProperties.add(new PropertyRef().setName("Id"));
				Key key = new Key().setKeys(keyProperties);

				return new EntityType().setName(ET_ADDRESS_FQN.getName()).setProperties(properties).setKey(key);

			}
		}

		return null;
	}

	@Override
	public EntitySet getEntitySet(String entityContainer, String name) throws ODataException {
		if (ENTITY_CONTAINER_NAME.equals(entityContainer)) {
			if (ES_ADDRESS_NAME.equals(name)) {
				return new EntitySet().setName(name).setEntityType(ET_ADDRESS_FQN);
			}
		}
		return null;
	}

	@Override
	public EntityContainerInfo getEntityContainerInfo(String name) throws ODataException {
		if (name == null || ENTITY_CONTAINER_NAME.equals(name)) {
			return new EntityContainerInfo().setName(ENTITY_CONTAINER_NAME).setDefaultEntityContainer(true);
		}

		return null;
	}
}

package es.ua.eps.cursohibernate.multitenancy;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

import java.util.Properties;


import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;


public class MapMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {


        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Map<String, ConnectionProvider> connectionProviderMap = new HashMap<String, ConnectionProvider>();

        

        public MapMultiTenantConnectionProvider() throws IOException {

                //Databases added (they could be read from a configuration file)

                initConnectionProviderForTenant("default");

                initConnectionProviderForTenant("P501user1");

                initConnectionProviderForTenant("P501user2");

        }


        private void initConnectionProviderForTenant(String tenantId) throws IOException {

                Properties properties = new Properties();

                properties.load(getClass().getResourceAsStream(

                                String.format("/multitenancy-%s.properties", tenantId)));

                DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();

                connectionProvider.configure(properties);

                this.connectionProviderMap.put(tenantId, connectionProvider);

        }

        

        @Override

        protected ConnectionProvider getAnyConnectionProvider() {

                return connectionProviderMap.get("default");

        }


        @Override

        protected ConnectionProvider selectConnectionProvider(

                String tenantIdentifier) {

                return connectionProviderMap.get(tenantIdentifier);

        }

}
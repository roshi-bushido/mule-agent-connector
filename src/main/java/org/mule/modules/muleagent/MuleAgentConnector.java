package org.mule.modules.muleagent;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.modules.muleagent.config.ConnectorConfig;
import org.mule.modules.muleagent.config.DefaultResponseHandler;

@Connector(name="mule-agent", friendlyName="Mule Agent")
public class MuleAgentConnector {

    @Config
    ConnectorConfig config;

    /**
     * Lists all the available components on the Mule Agent
     *
     * {@sample.xml ../../../doc/mule-agent-connector.xml.sample mule-agent:get-components}
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Processor
    public JSONArray getComponents() throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(this.config.getUrlFor("components"));
        request.addHeader("Content-Type", "application/json");
        String body = httpClient.execute(request, new DefaultResponseHandler());
        return new JSONArray(body);
    }

    /**
     * Enables a specific component on the Mule Agent
     *
     * {@sample.xml ../../../doc/mule-agent-connector.xml.sample mule-agent:enable-component}
     *
     * @param componentId string id for the mule component
     * @return {@link JSONObject} with the resulf of the operation
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Processor
    public JSONObject enableComponent(String componentId) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut request = new HttpPut(this.config.getUrlFor(String.format("%s/enable", componentId)));
        request.addHeader("Content-Type", "application/json");
        String body = httpClient.execute(request, new DefaultResponseHandler());
        return new JSONObject(body);
    }  
    
    /**
     * Disables a specific component on the Mule Agent
     *
     * {@sample.xml ../../../doc/mule-agent-connector.xml.sample mule-agent:disable-component}
     *
     * @param componentId string id for the mule component
     * @return {@link JSONObject} with the resulf of the operation
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    @Processor
    public JSONObject disableComponent(String componentId) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut request = new HttpPut(this.config.getUrlFor(String.format("%s/disable", componentId)));
        request.addHeader("Content-Type", "application/json");
        String body = httpClient.execute(request, new DefaultResponseHandler());
        return new JSONObject(body);
    }       

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}
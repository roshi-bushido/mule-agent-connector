package org.mule.modules.muleagent.config;

import org.apache.commons.lang.StringUtils;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.param.Default;

@Configuration(friendlyName = "Mule Agent Configuration")
public class ConnectorConfig {

    @Configurable
    @Default("http://localhost:9997")
    private String url;
    
    private String baseAgentPath = "/mule/agent";
    
    public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}

	public String getUrlFor(String path) {
    	if (!StringUtils.isEmpty(path)) {
    		if (path.startsWith("/")) {
    			return this.url + this.baseAgentPath + path;
    		} else {
    			return this.url + this.baseAgentPath + "/" + path;
    		}
    	} else {
    		return this.url + this.baseAgentPath;
    	}
    }

}
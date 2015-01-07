package org.codingpedia.demo.rest.resource.version;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VersionResponse {

//	Implementation-Title: DemoRestWS
//	Implementation-Version: 0.0.1-SNAPSHOT
//	Implementation-Vendor-Id: org.codingpedia
	@XmlElement(name = "implementationTitle")	
	String implementationTitle;

	@XmlElement(name = "implementationVersion")	
	String implementationVersion;
	
	@XmlElement(name = "implementationVendorId")	
	String implementationVendorId;

	public String getImplementationTitle() {
		return implementationTitle;
	}

	public void setImplementationTitle(String implementationTitle) {
		this.implementationTitle = implementationTitle;
	}

	public String getImplementationVersion() {
		return implementationVersion;
	}

	public void setImplementationVersion(String implementationVersion) {
		this.implementationVersion = implementationVersion;
	}

	public String getImplementationVendorId() {
		return implementationVendorId;
	}

	public void setImplementationVendorId(String implementationVendorId) {
		this.implementationVendorId = implementationVendorId;
	}
		
}

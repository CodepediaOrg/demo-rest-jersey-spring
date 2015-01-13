package org.codingpedia.demo.rest.resource.manifest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImplementationDetails {

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

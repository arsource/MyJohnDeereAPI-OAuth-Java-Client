
package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;

public class ContributionProduct
    extends Resource
{
    protected String marketPlaceName;
    protected String marketPlaceDescription;
    protected String marketPlaceLogo;
    protected String defaultLocale;
    protected String currentStatus;
    protected String authenticationCallback;
    protected String activationCallback;
    protected List<String> previewImages;
    protected List<String> supportedLocales;
    protected List<String> supportedRegions;
    protected List<String> supportedOperationCenters;

    /**
     * Gets the value of the marketPlaceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketPlaceName() {
        return marketPlaceName;
    }

    /**
     * Sets the value of the marketPlaceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketPlaceName(String value) {
        this.marketPlaceName = value;
    }

    /**
     * Gets the value of the marketPlaceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketPlaceDescription() {
        return marketPlaceDescription;
    }

    /**
     * Sets the value of the marketPlaceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketPlaceDescription(String value) {
        this.marketPlaceDescription = value;
    }

    /**
     * Gets the value of the marketPlaceLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketPlaceLogo() {
        return marketPlaceLogo;
    }

    /**
     * Sets the value of the marketPlaceLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketPlaceLogo(String value) {
        this.marketPlaceLogo = value;
    }

    /**
     * Gets the value of the defaultLocale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Sets the value of the defaultLocale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultLocale(String value) {
        this.defaultLocale = value;
    }

    /**
     * Gets the value of the currentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Sets the value of the currentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentStatus(String value) {
        this.currentStatus = value;
    }

    /**
     * Gets the value of the authenticationCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationCallback() {
        return authenticationCallback;
    }

    /**
     * Sets the value of the authenticationCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationCallback(String value) {
        this.authenticationCallback = value;
    }

    /**
     * Gets the value of the activationCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationCallback() {
        return activationCallback;
    }

    /**
     * Sets the value of the activationCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationCallback(String value) {
        this.activationCallback = value;
    }

    /**
     * Gets the value of the previewImages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previewImages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviewImages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPreviewImages() {
        if (previewImages == null) {
            previewImages = new ArrayList<String>();
        }
        return this.previewImages;
    }

    /**
     * Gets the value of the supportedLocales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedLocales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedLocales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedLocales() {
        if (supportedLocales == null) {
            supportedLocales = new ArrayList<String>();
        }
        return this.supportedLocales;
    }

    /**
     * Gets the value of the supportedRegions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedRegions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedRegions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedRegions() {
        if (supportedRegions == null) {
            supportedRegions = new ArrayList<String>();
        }
        return this.supportedRegions;
    }

    /**
     * Gets the value of the supportedOperationCenters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedOperationCenters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedOperationCenters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedOperationCenters() {
        if (supportedOperationCenters == null) {
            supportedOperationCenters = new ArrayList<String>();
        }
        return this.supportedOperationCenters;
    }

}

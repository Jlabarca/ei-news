
package com.jlabarca.einews.pojo;

import com.google.gson.annotations.Expose;

public class GoogleNews {

    @Expose
    private ResponseData responseData;
    @Expose
    private Object responseDetails;
    @Expose
    private Integer responseStatus;

    /**
     * 
     * @return
     *     The responseData
     */
    public ResponseData getResponseData() {
        return responseData;
    }

    /**
     * 
     * @param responseData
     *     The responseData
     */
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    /**
     * 
     * @return
     *     The responseDetails
     */
    public Object getResponseDetails() {
        return responseDetails;
    }

    /**
     * 
     * @param responseDetails
     *     The responseDetails
     */
    public void setResponseDetails(Object responseDetails) {
        this.responseDetails = responseDetails;
    }

    /**
     * 
     * @return
     *     The responseStatus
     */
    public Integer getResponseStatus() {
        return responseStatus;
    }

    /**
     * 
     * @param responseStatus
     *     The responseStatus
     */
    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

}

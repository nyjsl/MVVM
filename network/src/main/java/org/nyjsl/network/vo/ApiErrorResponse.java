
package org.nyjsl.network.vo;

public class ApiErrorResponse<T> extends ApiResponse<T> {
    private String errorMessage;

    public ApiErrorResponse(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

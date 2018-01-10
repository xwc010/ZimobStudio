package cc.yujie.dataplugs.aliyunsdk;

/**
 * Created by xwc on 2017/9/29.
 */

public class LogException extends Exception {
    private static final long serialVersionUID = -3451945810203597732L;
    private String errorCode;
    private String requestId;

    public LogException(String code, String message, String requestId) {
        super(message);
        this.errorCode = code;
        this.requestId = requestId;
    }

    public LogException(String code, String message, Throwable cause, String requestId) {
        super(message, cause);
        this.errorCode = code;
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return super.getMessage();
    }

    public String getRequestId() {
        return this.requestId;
    }
}

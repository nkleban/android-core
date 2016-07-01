package net.intellectsoft.core.mvp.domain.exception;

public class DomainException extends Exception {

    public static final int UNKNOWN_ERROR = 0;

    protected final int errorCode;

    public DomainException(Throwable t) {
        super(t);
        errorCode = UNKNOWN_ERROR;
    }

    public DomainException(Throwable t, int code) {
        super(t);
        errorCode = code;
    }

    public int getCode() {
        return errorCode;
    }

}

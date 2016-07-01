package net.intellectsoft.core.mvp.domain.exception;

public class NetworkException extends DomainException {

    public static final int NETWORK_ERROR = -1;

    public NetworkException(Throwable t) {
        super(t);
    }

    public NetworkException(Throwable t, int code) {
        super(t, code);
    }

}
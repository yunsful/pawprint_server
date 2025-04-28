package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

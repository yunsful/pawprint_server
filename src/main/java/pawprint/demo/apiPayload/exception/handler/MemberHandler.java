package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

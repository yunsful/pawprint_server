package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
    
}

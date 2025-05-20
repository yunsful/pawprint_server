package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class MemoryHandler extends GeneralException {
    
    public MemoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

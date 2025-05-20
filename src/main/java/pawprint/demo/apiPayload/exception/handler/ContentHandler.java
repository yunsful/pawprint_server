package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class ContentHandler extends GeneralException {
    
    public ContentHandler(BaseErrorCode code) {
        super(code);
    }
}

package pawprint.demo.apiPayload.exception.handler;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.exception.GeneralException;

public class PlanHandler extends GeneralException {
    
    public PlanHandler(BaseErrorCode code) {
        super(code);
    }
}

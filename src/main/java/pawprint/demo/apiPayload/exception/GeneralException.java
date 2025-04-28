package pawprint.demo.apiPayload.exception;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    
    private BaseErrorCode code;
    
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    
    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
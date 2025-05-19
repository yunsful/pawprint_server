package pawprint.demo.apiPayload.code.status;

import pawprint.demo.apiPayload.BaseCode;
import pawprint.demo.apiPayload.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    MISSION_COMPLETE(HttpStatus.OK, "MISSION201", "미션 완료 처리 후 생성된 추억 id를 반환합니다.")
    ;
    
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    
    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }
    
    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}

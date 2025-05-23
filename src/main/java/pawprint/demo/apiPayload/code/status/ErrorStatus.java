package pawprint.demo.apiPayload.code.status;

import pawprint.demo.apiPayload.BaseErrorCode;
import pawprint.demo.apiPayload.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "권한이 없습니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    
    // 유저 관련 에러
    Member_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "유저를 찾을 수 없습니다."),
    Member_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "이미 존재하는 유저입니다."),
    LOGGED_OUT_USER(HttpStatus.NOT_FOUND, "USER4003", "로그아웃한 유저입니다."),
    NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER4004", "비밀번호다 다릅니다."),

    // 토큰 관련 에러
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN4001", "유효하지 않은 토큰입니다."),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED, "TOKEN4002", "잘못된 JWT 서명입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN4003", "액세스 토큰이 만료되었습니다."),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN4004", "지원되지 않는 JWT 토큰입니다."),
    ACCESS_TOKEN_NOT_EXPIRED(HttpStatus.BAD_REQUEST, "TOKEN4005", "액세스 토큰이 만료되지 않았습니다."),
    INVALID_KAKAO_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4006", "카카오 액세스 토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "TOKEN4007", "리프레시 토큰이 만료되었습니다. 다시 로그인해주세요."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.BAD_REQUEST, "TOKEN4008", "저장된 리프레시 토큰과 일치하지 않습니다."),

    // S3 이미지 업로드 관련
    FILE_NOT_UPLOADED(HttpStatus.BAD_REQUEST, "S34001", "이미지를 업로드 할 수 없습니다."),
    FILE_IS_EMPTY(HttpStatus.BAD_REQUEST, "S34002", "파일이 비어있습니다."),
    FILE_NOT_IMAGE(HttpStatus.BAD_REQUEST, "S34003", "이미지 파일만 업로드 가능합니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE(HttpStatus.BAD_REQUEST, "S34004", "삭제 중 에러가 발생했습니다."),
    INVALID_URL(HttpStatus.BAD_REQUEST, "S34005", "유효하지 않은 url입니다."),
    
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션을 찾을 수 없습니다."),
    
    MEMORY_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMORY4001", "추억을 찾을 수 없습니다."),
    
    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "CONTENT4001", "게시글을 찾을 수 없습니다."),
    
    PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAN4001", "일정을 찾을 수 없습니다.")
    
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}

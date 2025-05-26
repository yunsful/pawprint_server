package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawprint.demo.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
@Tag(name = "홈", description = "홈 화면 관련 API")
@Slf4j
public class HomeController {

    @GetMapping("/{id}")
    public ApiResponse<String> getBannerInfo(
            @PathVariable Long id
    ) {
        
        
        return ApiResponse.onSuccess("banner id : " + id);
    }
}

package shoppingmall.domain.defaultDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultResultDto {

    private long idx;
    private String message;
    private boolean success;

    @Builder
    public DefaultResultDto(long idx,String message, boolean success) {
        this.idx = idx;
        this.message = message;
        this.success = success;
    }
}

package shoppingmall.global.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * 날짜 처리와 관련된 Utility Class
 */
@Slf4j
@UtilityClass
public class DateUtil {

    /**
     * 오늘 날짜를 YYYY-MM-DD 형태로 return
     * @return 오늘 날짜(YYYY-MM-DD)
     */
    public String getDate_YYYY_MM_DD() {
        LocalDate now = LocalDate.now();
        return now.toString();
    }

    /**
     * 오늘 날짜를 YYYYMMDD 형태로 return
     * @return 오늘 날짜(YYYYMMDD)
     */
    public String getDate_YYYYMMDD() {
        LocalDate now = LocalDate.now();
        return now.toString().replaceAll("-","");
    }

}

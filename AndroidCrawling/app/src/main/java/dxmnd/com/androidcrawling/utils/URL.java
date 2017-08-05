package dxmnd.com.androidcrawling.utils;

/**
 * Created by HunJin on 2017-08-05.
 */

public class URL {
    // 메가박스
    public static final String MEGABOX_THEADER_URL = "http://image2.megabox.co.kr/mop/base/footer_theater.html"; // 영화관 리스트 크롤링
    public static final String MEGABOX_THEADER_TIME_URL = "http://www.megabox.co.kr/?menuId=timetable-movie"; // 영화 정보 크롤링

    public static final String MEAGBOX_THEADER_MOVIE_INFO = "http://www.megabox.co.kr/pages/theater/Theater_Schedule.jsp?";

    public static final String MEGABOX_SELECT_THEADER = ".footer_theater .wrap .clearfix dd a";
    public static final String MEGABOX_SELECT_MOVIE_INFO = ".timetable_container .movie_time_table.v2 tbody .lineheight_80";

    // CGV
    public static final String CGV_THEADER_URL = "http://www.cgv.co.kr/reserve/show-times/";  // 시간 정보
    public static final String CGV_TEHEADER_TIME_URL = "http://www.cgv.co.kr";  // 영화관 정보

    public static final String CGV_SELECT_TIME = ".showtimes-wrap .sect-showtimes ul li";  // 시간 정보
}

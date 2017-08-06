package dxmnd.com.androidcrawling.items;

/**
 * Created by HunJin on 2017-08-05.
 */

public class RegionListItem {
    private String code;
    private String region;
    private String divison;
    private String detaildivision;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDivision() {
        return divison;
    }

    public void setDivision(String div) {
        this.divison = div;
    }

    public String getDetailDivision()
    {
        return detaildivision;
    }

    public void setDetaildivision(String div)
    {
        this.detaildivision = div;
    }
}

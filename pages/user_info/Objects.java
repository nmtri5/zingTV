package pages.user_info;


import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class Objects {
    @AndroidFindBy(id = "tv_login")
    public AndroidElement loginBtn;

    @AndroidFindBy(id = "ll_login_zalo")
    public AndroidElement loginzaloBtn;

    @AndroidFindBy(id = "img_avatar")
    public AndroidElement user_avarta;

    @AndroidFindBy(id = "tv_program_name")
    public AndroidElement recentlyitems;

    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[@index='0']")
    public AndroidElement trangchu;

    @AndroidFindBy(id = "tv_history_more")
    public AndroidElement xemthem;

//    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[@index='1']")
//    public AndroidElement khampha;

    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[@index='1']")
    public AndroidElement canhan;

    @AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[@index='2']")
    public AndroidElement them;
}

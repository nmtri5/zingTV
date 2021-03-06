package pages.homepage;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import supports.AndroidCommonFunctions;
import io.appium.java_client.TouchAction;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class Operations extends Objects {

        // todo: input search text,
        public Operations(){
            PageFactory.initElements(new AppiumFieldDecorator(AndroidCommonFunctions.getApp()), this);
}



        public static String deAccent(String str) {
            String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();

        }

    public void swipeVertical(double startPercentage, double finalPercentage, double anchorPercentage) {

        Dimension size = AndroidCommonFunctions.getApp().manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * finalPercentage);

        new TouchAction(AndroidCommonFunctions.getApp())
                .press(anchor, startPoint)
                .moveTo(-50, -50)
                .release().perform();
    }


        public boolean verify_content_suggestion(String s){
            List<AndroidElement> list_element = AndroidCommonFunctions.getElements("id", "tv_title");
            if (list_element == null) {

                return AndroidCommonFunctions.isExisted("id", "tv_error");
            } else {

                for (AndroidElement items : list_element) {

                    return deAccent(items.getText().toLowerCase()).contains(s);

                }
            }
            return list_element.size() > 0;
        }

        public void verify_content_homepage(){
            boolean reach_bottom = false;
            while (reach_bottom == false) {
                swipeVertical(0.2,0.8,0.5);
                AndroidCommonFunctions.waitForScreenToLoad(AndroidCommonFunctions.getApp(), AndroidCommonFunctions.getElement("id", "tv_title"), 1);
                List<AndroidElement> a = AndroidCommonFunctions.getElements("id", "tv_title");
                for (AndroidElement items : a)
                      {

                    AndroidCommonFunctions.click("text",items.getText());

                    //Check element exist to know if it is now playing page
                          if (AndroidCommonFunctions.isExisted("id", "tv_program_name") == true){
                              Assert.assertEquals(items.getText(), AndroidCommonFunctions.getText("id", "tv_program_name"));
                          } else if (AndroidCommonFunctions.isExisted("id", "sdk_texture_view") == true) {
                              Assert.assertTrue(AndroidCommonFunctions.isExisted("id", "video_info_view"));
                          } else {
                              Assert.assertTrue(AndroidCommonFunctions.isExisted("id", "tv_error"));
                          }
                    AndroidCommonFunctions.back();

                }
                reach_bottom = reachBottom();
            }
        }

        public void perform_search_action(String keyword) {
            searchField.click();
            AndroidCommonFunctions.fill("id", "et_search_bar", keyword);
        }

//        public void searchExist(String keyword) {
//            perform_search_action(keyword);
//            allResult.click();
//            String compare = AndroidCommonFunctions.getElement("id", "tv_title").getText();
//            switchSearchTab("nổi bật tab");
//            Assert.assertEquals(deAccent(keyword).toLowerCase(), deAccent(compare).toLowerCase());
//            switchSearchTab("chương trình tab");
//            Assert.assertEquals(deAccent(keyword).toLowerCase(), deAccent(compare).toLowerCase());
//            switchSearchTab("video tab");
//            Assert.assertEquals(deAccent(keyword).toLowerCase(), deAccent(compare).toLowerCase());
//        }

        public void searchNotExist (String keyword) {
            perform_search_action(keyword);
            switchSearchTab("nổi bật tab");
            AndroidCommonFunctions.isExisted("id", "tv_error");
            switchSearchTab("chương trình tab");
            AndroidCommonFunctions.isExisted("id", "tv_error");
            switchSearchTab("video tab");
            AndroidCommonFunctions.isExisted("id", "tv_error");
            switchSearchTab("nghệ sĩ tab");
            AndroidCommonFunctions.isExisted("id", "tv_error");
        }

        public boolean reachBottom () {
            List <AndroidElement> list_element = AndroidCommonFunctions.getElements("id", "tv_title");
            if (AndroidCommonFunctions.isExisted("text", "Hoạt Hình") && AndroidCommonFunctions.isExisted("text", "Xem thêm") && list_element.size() == 4){
                return true;
            }
                return false;
    }

    public void switchSearchTab (String tab){

        if (tab =="nổi bật tab") {
            HighlightTab.click();
        } else if (tab == "chương trình tab") {
            ProgramTab.click();
        } else if (tab == "video tab") {
            VideoTab.click();
        } else if (tab == "nghệ sĩ tab"){
            ArtistTab.click();
        }
    }



    }




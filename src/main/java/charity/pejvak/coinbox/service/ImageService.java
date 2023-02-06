package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    public String getDownloadPath(Image image) {
        return "";
    }

    public Image addNewImage(MultipartFile multipartFile) {
        return null;
    }

    public void deleteImage(Long id) {
    }


    /*
    یک عدد آبجکت File.io تحویل میگیرد
    در مسیر مشخص شده ذخیره کند
    آبجکت image را بدهد

    آدرس برای دانلود عکس را بدهد
     */
    // add
    // update
    // delete
    // get all by coinBoxType
    // get with id
    // get All
}

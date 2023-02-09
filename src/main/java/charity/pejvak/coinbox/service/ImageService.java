package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.model.Image;
import charity.pejvak.coinbox.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;

@Service
public class ImageService {

    private final ImageRepository repository;

    @Value("${image.download-dir}")
    private String savePath;

    @Autowired
    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }


    public String getDownloadPath(Image image) {
        try {
            Path path = Paths.get(repository.getReferenceById(image.getId()).getPath());
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource.getFile().getAbsolutePath();
            }else {
                throw new NoSuchFileException("No such file as resource");
            }
        } catch (Exception e) {
            //TODO Handle Exception
        }
        return "";
    }

    public Image addNewImage(MultipartFile file) {
        //TODO fix this 2
        String fileName = file.getOriginalFilename();
        File savedFile = new File(savePath + "\\" + fileName);
        try {
            file.transferTo(savedFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return repository.save(new Image(null, savedFile.getPath(), null));
    }

    public void deleteImage(Long id) {
        repository.deleteById(id);
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

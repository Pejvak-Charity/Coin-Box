package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.model.utility.Image;
import charity.pejvak.coinbox.repository.ImageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    @Value("${image.download-dir}")
    private String savePath;

    @PostConstruct
    private void init() throws NoSuchFileException {
        File file = new File(savePath);
        if (!file.exists() || !file.isDirectory())
            if (file.mkdir())
                throw new NoSuchFileException(savePath, "couldn't create directory", "maybe it haven't required permission");
    }


    public String getDownloadPath(Image image) {
        try {
            Path path = Paths.get(repository.getReferenceById(image.getId()).getPath());
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource.getURL().toString();
//                .getFile().getAbsolutePath();
            } else {
                throw new NoSuchFileException("No such file as resource");
            }
        } catch (Exception e) {
            //TODO Handle Exception
        }
        return "";
    }

    public File addNewImageFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File savedFile = new File(getBaseFile().getAbsolutePath() + "\\" + fileName);
        try {
            file.transferTo(savedFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return savedFile;
    }

    public void deleteImage(Long id) {
        repository.deleteById(id);
    }



    private File getBaseFile() {
        return new File(savePath);
    }

    public void updateImage(Image image) {
        repository.saveAndFlush(image);
    }

    public Image addImage(Image image) {
        return repository.saveAndFlush(image);
    }

    public Image setDefaultImage(Image image) {
        List<Image> similarImages = repository.findAllByCoinBoxType_Id(image.getCoinBoxType().getId());
        similarImages.stream().filter(image1 -> !Objects.equals(image1.getId(), image.getId())).forEach(image1 -> image1.setDefault(false));
        image.setDefault(true);
        repository.saveAll(similarImages);
        return repository.saveAndFlush(image);
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
